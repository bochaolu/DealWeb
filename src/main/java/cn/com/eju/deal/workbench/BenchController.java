package cn.com.eju.deal.workbench;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.com.eju.deal.base.controller.BaseController;

/**   
* 首页-Controller
* @author (li_xiaodong)
* @date 2016年3月9日 下午5:30:36
*/
@RestController
@RequestMapping(value = "bench")
public class BenchController extends BaseController
{
    
    /** 
    * 初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, ModelMap mop)
    {
        //构建ModelAndView实例，并设置跳转页面
        ModelAndView mv = new ModelAndView("bench/bench");
        
        return mv;
    }
    
}
