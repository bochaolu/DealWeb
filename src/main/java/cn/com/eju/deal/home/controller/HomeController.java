package cn.com.eju.deal.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.eju.deal.base.controller.BaseController;

/**   
* 首页-Controller
* @author (li_xiaodong)
* @date 2016年3月9日 下午5:30:36
*/
@Controller
@RequestMapping(value = "homes")
public class HomeController extends BaseController
{
    
    /** 
    * 初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap mop)
    {
        return "home/home";
    }
    
}
