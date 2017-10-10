package cn.com.eju.deal.common.file.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.common.file.dto.FileRecordDto;
import cn.com.eju.deal.common.file.service.FileRecordService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;

/**   
* 模块文件与文件code 关系--Controller
* @author li_xiaodong
* @date 2016年2月2日 下午9:25:30
*/
@Controller
@RequestMapping(value = "fileRecord")
public class FileRecordController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "fileRecordService")
    private FileRecordService fileRecordService;
    
    
    /**
     *  创建
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ReturnView<?, ?> create(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //请求map
        
        FileRecordDto fileRecordDto = new FileRecordDto();
        
        try
        {
            ResultData<?> resultData = fileRecordService.create(fileRecordDto);
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("FileRecord", "FileRecordController", "create", "", 0, "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    /**
     *  修改 
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ReturnView<?, ?> update(HttpServletRequest request,
        @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)
    {
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        reqMap.remove("_method");
        
        FileRecordDto fileRecordDto = new FileRecordDto();
        
        try
        {
            //更新
            fileRecordService.update(fileRecordDto);
        }
        catch (Exception e)
        {
            
            logger.error("FileRecord", "FileRecordController", "update", "", 0, "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * 删除
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ReturnView<?, ?> destroy(@PathVariable int id, HttpServletResponse response)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        try
        {
            //删除
            fileRecordService.delete(id, UserInfoHolder.getUserId());
        }
        catch (Exception e)
        {
            logger.error("FileRecord", "FileRecordController", "destroy", "", 0, "", "", e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        
        return getOperateJSONView(rspMap);
        
    }

    /**
    * 删除
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "removeByFileNo/{fileNo}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnView<?, ?> destroy(@PathVariable String  fileNo, HttpServletResponse response)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();

        try
        {
            //删除
            fileRecordService.deleteByFileNo(fileNo, UserInfoHolder.getUserId());
        }
        catch (Exception e)
        {
            logger.error("removeByFileNo", "FileRecordController", "removeByFileNo", "", 0, "", "", e);

            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
        return getOperateJSONView(rspMap);

    }

}
