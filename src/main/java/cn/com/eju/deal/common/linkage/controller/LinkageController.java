package cn.com.eju.deal.common.linkage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.linkage.service.LinkageAreaService;
import cn.com.eju.deal.common.linkage.service.LinkageCityService;
import cn.com.eju.deal.common.linkage.service.LinkageDistrictService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.dto.student.StudentDto;

/**   
* 城市、行政区、板块 联动Controller层
* @author (li_xiaodong)
* @date 2016年3月24日 上午10:19:23
*/
@Controller
@RequestMapping(value = "linkages")
public class LinkageController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "linkageCityService")
    private LinkageCityService linkageCityService;
    
    @Resource(name = "linkageDistrictService")
    private LinkageDistrictService linkageDistrictService;
    
    @Resource(name = "linkageAreaService")
    private LinkageAreaService linkageAreaService;
    
    /** 
    * 获取城市list
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/city", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getCityList(ModelMap mop)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();
        
        try
        {
            resultData = linkageCityService.getCityList();
        }
        catch (Exception e)
        {
            logger.error("base.linkage", "LinkageController", "getCityList", "", null, "", "根据行政区DistrictNo获取其板块List", e);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        
        return getSearchJSONView(rspMap);
    }
    
    /** 
    * 根据城市CityNo获取其行政区List
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/city/{cityNo}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getDistrictListByCityNo(@PathVariable("cityNo") String cityNo)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //返回map
        ResultData<?> resultData = new ResultData<>();
        
        try
        {
            resultData = linkageDistrictService.getDistrictListByCityNo(cityNo);
        }
        catch (Exception e)
        {
            logger.error("base.linkage", "LinkageController", "getDistrictListByCityNo", "", null, "", "根据行政区DistrictNo获取其板块List", e);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        
        return getSearchJSONView(rspMap);
    }
    
    /** 
    * 根据行政区DistrictNo获取其板块List
    * @param districtNo
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/area/{districtNo}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> getAreaListByDistrictNo(@PathVariable("districtNo") String districtNo)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //返回map
        ResultData<?> resultData = new ResultData<>();
        
        try
        {
            resultData = linkageAreaService.getAreaListByDistrictNo(districtNo);
        }
        catch (Exception e)
        {
            logger.error("base.linkage", "LinkageController", "getAreaListByDistrictNo", "", null, "", "根据行政区DistrictNo获取其板块List", e);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
        rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        rspMap.put(Constant.RETURN_DATA_KEY, resultData.getReturnData());
        
        return getSearchJSONView(rspMap);
    }
    
}
