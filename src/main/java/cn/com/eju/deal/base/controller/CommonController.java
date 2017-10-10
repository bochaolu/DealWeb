package cn.com.eju.deal.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.WebUtil;

/**   
* 公用controller
* @author (li_xiaodong)
* @date 2015年11月29日 下午5:15:09
*/
@RestController
@RequestMapping("/commons")
public class CommonController extends BaseController
{
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    /** 
     * 刷新缓存
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ReturnView<?, ?> refresh(HttpServletRequest request, ModelMap mop)
    {
        //响应应Map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        logger.info("外部接口调入,刷新配置");
        
        try
        {
            SystemParam.refreshCodeMap();
        }
        catch (Exception e)
        {
            logger.error("Common",
                "CommonController",
                "refresh",
                "",
                UserInfoHolder.getUserId(),
                WebUtil.getIPAddress(request),
                "刷新webconfig配置表失败",
                e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
        }
        
        
        return getOperateJSONView(rspMap);
        
    }
   
}
