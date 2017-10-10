package cn.com.eju.deal.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.Post;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.support.SystemCfg;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.user.LoginDto;
import cn.com.eju.deal.user.service.LoginService;

/**   
* 
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@RestController
public class LoginController extends BaseController
{
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "loginService")
    private LoginService loginService;
    
    /** 
     * 登录 
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ReturnView<?, ?> login(HttpServletRequest request)
    {
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        String username = (String)reqMap.get("username");
        String password = (String)reqMap.get("password");
        //TODO 后边需要加密
        //password = EncryptUtil.encrypt(password, EncryptUtil.EncryptType.AES);
        
        // 验证码校验
        String randCode = (String)reqMap.get("randCode");
        if (!StringUtil.isEmpty(randCode))
        {
            String loginRandCode = (String)WebUtil.getValueFromSession(request, "LOGIN_RAND"); // session储存的校验码
            if ((StringUtil.isEmpty(randCode) || !randCode.equals(loginRandCode)))
            {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "验证码校验失败");
                return getOperateJSONView(rspMap);
            }
        }
        
        //判空
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password))
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "用户名或密码不正确");
            return getOperateJSONView(rspMap);
        }
        
        //
        ResultData<?> resultData = new ResultData<UserInfo>();
        
        LoginDto loginDto = new LoginDto();
        loginDto.setLoginName(username);
        loginDto.setPassword(password);
        loginDto.setSystemCode(SystemCfg.getString("systemCode"));
        
        try
        {
            
            resultData = loginService.login(loginDto);
            
        }
        catch (Exception e)
        {
            
            logger.error("Login",
                "LoginController",
                "login",
                "input param:" + loginDto.toString(),
                UserInfoHolder.getUserId(),
                WebUtil.getRealIpAddress(request),
                "登录异常",
                e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            
            rspMap.put(Constant.RETURN_MSG_KEY, "登录异常");
            
            return getSearchJSONView(rspMap);
            
        }
        
        UserInfo userInfo = new UserInfo();
        
        //        String passwordMd5 = null;
        if (ReturnCode.SUCCESS.equals(resultData.getReturnCode()))
        {
            //            passwordMd5 = EncryptUtil.encrypt(password, EncryptType.AES);
            
            //shiro登录认证
            //SecurityUtils.getSubject().login(new UsernamePasswordToken(username, passwordMd5));
            
            Map<?, ?> mapTemp = (Map<?, ?>)resultData.getReturnData();
            
            if (null != mapTemp)
            {
                userInfo = MapToEntityConvertUtil.convert(mapTemp, UserInfo.class, "");
                Integer selectPostId = userInfo.getSelectpostId();
                if (null == userInfo.getSelectPost())
                {
                    List<Post> postList = userInfo.getPostList();
                    if (null != postList && !postList.isEmpty())
                    {
                        for (int i = 0; i < postList.size(); i++)
                        {
                            Map<?, ?> map = (Map<?, ?>)postList.get(i);
                            Integer postId = (Integer)map.get("postId");
                            if (postId.equals(Integer.valueOf(selectPostId)))
                            {
                                Post post = MapToEntityConvertUtil.convert(map, Post.class, "");
                                userInfo.setSelectPost(post);
                            }
                        }
                    }
                }
            }
            
        }
        else
        {
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
            return getSearchJSONView(rspMap);
            
        }
        
        //        UsernamePasswordToken token = new UsernamePasswordToken(username, passwordMd5);
        //        token.setRememberMe(true);
        //        
        //        logger.info("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        //        
        //        //获取当前的Subject  
        //        Subject currentUser = SecurityUtils.getSubject();
        //        
        //        try
        //        {
        //            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
        //            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
        //            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
        //            logger.info("对用户[" + username + "]进行登录验证..验证开始");
        //            
        //            logger.info("username=" + username + ";passwordMd5=" + passwordMd5 + ";token=" + token.toString());
        //            
        //            currentUser.login(token);
        //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        //            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        //        }
        //        catch (UnknownAccountException uae)
        //        {
        //            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
        //            
        //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        //            rspMap.put(Constant.RETURN_MSG_KEY, "未知账户");
        //        }
        //        catch (IncorrectCredentialsException ice)
        //        {
        //            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
        //            
        //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        //            rspMap.put(Constant.RETURN_MSG_KEY, "密码不正确");
        //        }
        //        catch (LockedAccountException lae)
        //        {
        //            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
        //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        //            rspMap.put(Constant.RETURN_MSG_KEY, "账户已锁定");
        //            
        //        }
        //        catch (ExcessiveAttemptsException eae)
        //        {
        //            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
        //            
        //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        //            rspMap.put(Constant.RETURN_MSG_KEY, "用户名或密码错误次数过多");
        //            
        //        }
        //        catch (AuthenticationException ae)
        //        {
        //            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
        //            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
        //            ae.printStackTrace();
        //            
        //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        //            rspMap.put(Constant.RETURN_MSG_KEY, "用户名或密码不正确");
        //            
        //        }
        //        //验证是否登录成功  
        //        if (currentUser.isAuthenticated())
        //        {
        //            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
        //        }
        //        else
        //        {
        //            token.clear();
        //        }
        
        //是否有系统权限
        //        Boolean isHaveAuth = false;
        //        try
        //        {
        //            isHaveAuth = isHaveAuth(userInfo, request, LOGIN_TYPE);
        //        }
        //        catch (Exception e1)
        //        {
        //            logger.error("Login",
        //                "LoginController",
        //                "isHaveAuth",
        //                "",
        //                UserInfoHolder.getUserId(),
        //                "",
        //                "判断是否有系统权限异常",
        //                e1);
        //            
        //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        //            rspMap.put(Constant.RETURN_MSG_KEY, "判断是否有系统权限异常");
        //            return getOperateJSONView(rspMap);
        //        }
        //        
        //        if (!isHaveAuth)
        //        {
        //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        //            rspMap.put(Constant.RETURN_MSG_KEY, "无系统权限");
        //            return getOperateJSONView(rspMap);
        //        }
        
        //存放userInfo
        UserInfoHolder.set(userInfo);
        
        //用户信息放session
        WebUtil.addSession(request, Constant.SESSION_KEY_USERINFO, userInfo);
        
        //        ResultData<?> auth = new ResultData<List<Auth>>();
        //        try
        //        {
        //            auth = userService.getAuth(SYSTEM_NAME);
        //        }
        //        catch (Exception e)
        //        {
        //            logger.error("Login", "LoginController", "getAuth", "", UserInfoHolder.getUserId(), "", "获取Auth权限异常", e);
        //            
        //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        //            rspMap.put(Constant.RETURN_MSG_KEY, "获取Auth权限异常");
        //            return getOperateJSONView(rspMap);
        //        }
        //        
        //        if (ReturnCode.SUCCESS.equals(auth.getReturnCode()))
        //        {
        //            WebUtil.addSession(request, Constant.SESSION_KEY_AUTH_URL, auth.getReturnData());
        //        }
        
        //        rspMap.put(Constant.RETURN_DATA_KEY, WebUtil.getValueFromSession(request, "headUrl"));
        
        return getOperateJSONView(rspMap);
    }
    
    /**
     * 退出登录页面
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request)
    {
        
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        //        SecurityUtils.getSubject().logout();
        
        request.getSession().invalidate();
        
        //构建ModelAndView实例，并设置跳转页面
        ModelAndView mv = new ModelAndView("redirect:/index.jsp");
        
        return mv;
        
        //        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
    }
    
    
}
