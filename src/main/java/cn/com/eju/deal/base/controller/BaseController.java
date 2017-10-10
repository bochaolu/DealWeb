package cn.com.eju.deal.base.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;

/**   
* Controller 基类
* @author (li_xiaodong)
* @date 2016年2月17日 下午9:33:44
*/
public abstract class BaseController
{
    public PageInfo pageInfo;
    
    public PageInfo getPageInfo(HttpServletRequest request)
    {
        pageInfo = new PageInfo(request);
        return pageInfo;
    }
    
    public void setPageInfo(PageInfo pageInfo)
    {
        this.pageInfo = pageInfo;
    }
    
    public ReturnView<?, ?> getMapView(Map<?, ?> map)
    {
        ReturnView<?, ?> jsonView = new ReturnView<String, Object>();
        if (map != null)
        {
            
            jsonView.putAll(map);
        }
        return jsonView;
    }
    
    /** 
    * (绑定参数到request Attrbute)
    * @param request
    */
    public void bindParamToAttrbute(HttpServletRequest request)
    {
        Enumeration<?> enumer = request.getParameterNames();
        while (enumer.hasMoreElements())
        {
            String key = (String)enumer.nextElement();
            String[] values = request.getParameterValues(key);
            if (values.length > 1)
            {
                request.setAttribute(key, values);
            }
            else
            {
                request.setAttribute(key, request.getParameter(key));
            }
        }
    }
    
    /** 
    * (绑定参数到Map)
    * @param request
    * @return
    */
    public Map<String, Object> bindParamToMap(HttpServletRequest request)
    {
        Enumeration<?> enumer = request.getParameterNames();
        Map<String, Object> map = new HashMap<String, Object>();
        while (enumer.hasMoreElements())
        {
            String key = (String)enumer.nextElement();
            String val = request.getParameter(key);
            if (!"randomId".equals(key))
            {
                if ("orderBy".equals(key))
                {
                    if (!StringUtil.isEmpty(val))
                    {
                        Object orderByList = JsonUtil.parseToObject(val, List.class);
                        map.put(key, orderByList);
                    }
                    continue;
                }
                map.put(key, val);
            }
        }
        return map;
    }
    
    /** 
    * (返回查询list的jsonView)
    * @param list
    * @return
    */
    public ReturnView<?, ?> getSearchJSONView(List<?> list)
    {
        ReturnView<?, ?> jsonView = new ReturnView<String, Object>();
        jsonView.setSearchReturnType();
        if (list != null)
        {
            jsonView.setReturnValue(list);
        }
        // pageinfo
        //      jsonView.setPageInfo(pageInfo);
        return jsonView;
    }
    
    /** 
    * (返回查询list的jsonView)
    * @param map
    * @return
    */
    public ReturnView<?, ?> getSearchJSONView(Map<?, ?> map)
    {
        ReturnView<?, ?> jsonView = new ReturnView<String, Object>();
        jsonView.setSearchReturnType();
        if (map != null)
        {
            jsonView.putAll(map);
        }
        return jsonView;
    }
    
    /** 
    * (返回操作的jsonView)
    * @param result
    * @return
    */
    public ReturnView<?, ?> getOperateJSONView(Map<?, ?> result)
    {
        ReturnView<?, ?> jsonView = new ReturnView<String, Object>();
        jsonView.setOperateReturnType();
        if (result != null)
        {
            jsonView.putAll(result);
        }
        return jsonView;
    }
    
    /** 
    * TODO (这里用一句话描述这个方法的作用)
    * @return
    */
    public ReturnView<?, ?> succSeachView()
    {
        ReturnView<?, ?> view = new ReturnView<Object, Object>();
        view.setSuccess();
        view.setSearchReturnType();
        return view;
    }
    
    /** 
    * TODO (这里用一句话描述这个方法的作用)
    * @return
    */
    public ReturnView<?, ?> succOperateView()
    {
        ReturnView<?, ?> view = new ReturnView<Object, Object>();
        view.setSuccess();
        view.setOperateReturnType();
        return view;
    }
}
