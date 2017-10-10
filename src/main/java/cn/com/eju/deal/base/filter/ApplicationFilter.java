package cn.com.eju.deal.base.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.SystemCfg;
import cn.com.eju.deal.core.util.WebUtil;

/**   
* (过滤器，拦截所有request请求)
* @author (li_xiaodong)
* @date 2016年02月13日 下午12:00:54
*/
public class ApplicationFilter implements Filter
{
    //过滤url
    private List<String> excludePathList;
    
    //过滤后缀
    private static String staticFileSuffix;
    
    /**
    * 日志
    */
    private static LogHelper logger = LogHelper.getLogger(ApplicationFilter.class);
    
    @Override
    public void init(FilterConfig config)
        throws ServletException
    {
        
        logger.debug("Init ApplicationFilter");
        String url = config.getInitParameter("session_exclude_url");
        if (url != null)
        {
            excludePathList = Arrays.asList(url.split(","));
            logger.info("excludePathList=" + excludePathList);
        }
        
        config.getServletContext().setAttribute("ctx", SystemCfg.getString("ctx"));
        
        config.getServletContext().setAttribute("sysConfig", SystemCfg.getAllConfig());
        
        logger.info("ctx=" + SystemCfg.getString("ctx"));
        logger.info("sysConfig=" + SystemCfg.getAllConfig());
        
        staticFileSuffix = SystemCfg.getString("staticFileSuffix");
        logger.info("init staticFileSuffix:" + staticFileSuffix);
        
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
        throws IOException, ServletException
    {
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpServletRequest request = (HttpServletRequest)req;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String requestUrl = request.getRequestURI();
        requestUrl =
            requestUrl.substring(requestUrl.indexOf(request.getContextPath()) + request.getContextPath().length());
        String suffix = requestUrl.substring(requestUrl.lastIndexOf(".") + 1);
        
      //记录请求信息
        logger.info("ApplicationFilter[requestUrl=" + requestUrl + ";]");
        
        UserInfo userInfo = (UserInfo)WebUtil.getValueFromSession(request, Constant.SESSION_KEY_USERINFO);
        
        //验证不允许过去，跳到登陆页面 (1:userInfo==null && 2、未被排除 && 3、未被资源后缀排除  && 4、非ws)
        if (userInfo == null && !excludePathList.contains(requestUrl) && !staticFileSuffix.contains(suffix))
        {
            logger.info("ApplicationFilter[requestUrl=" + requestUrl + ";session userInfo is empty]");
            
            errorDeal(response, request, "900", "/index.jsp");
            return;
        }
        
        UserInfoHolder.set(userInfo);
        
        if (userInfo != null)
        {
            MDC.put("userId", userInfo.getUserId() == null ? "" : userInfo.getUserId());
            if (!staticFileSuffix.contains(suffix))
            {
                //日志统计访问
                logger.info("===>>client request:userId:" + userInfo.getUserId() + ",url:" + requestUrl);
            }
        }
        else
        {
            MDC.put("ip", WebUtil.getRealIpAddress(request));
        }
        
        try
        {
            fc.doFilter(request, resp);
        }
        catch (IOException e)
        {
            
            logger.error("", e.getMessage(), e, null);
            
            throw e;
        }
        catch (ServletException e)
        {
            logger.error("", e.getMessage(), e, null);
            throw e;
        }
        finally
        {
            UserInfoHolder.remove();
            MDC.remove("ip");
            if (userInfo != null)
            {
                MDC.remove("userName");
            }
        }
    }
    
    /** 
    * (当filter处理不能继续进入controller时错误处理)
    * @param response
    * @param request
    * @param errorCode
    * @param redirectUrl 重定向的url(如果是ajax请求则返回状态码)
    * @throws IOException
    */
    public void errorDeal(HttpServletResponse response, HttpServletRequest request, String errorCode, String redirectUrl)
        throws IOException
    {
        String ajaxHeader = request.getHeader("X-Requested-With");
        boolean isAjaxReq = ajaxHeader != null && ajaxHeader.equals("XMLHttpRequest") ? true : false;
        if (isAjaxReq)
        {
            logger.info("ajax request userInfo is null");
            response.setHeader("status", errorCode);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        else
        {
            response.sendRedirect(SystemCfg.getString("ctx") + redirectUrl);
        }
    }
    
    @Override
    public void destroy()
    {
        logger.debug("Destroy ApplicationFilter");
    }
    
}
