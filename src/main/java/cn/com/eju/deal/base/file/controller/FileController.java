package cn.com.eju.deal.base.file.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.file.service.FileHelpService;
import cn.com.eju.deal.base.file.service.FileHelpNewService;
import cn.com.eju.deal.base.file.service.FilesService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.support.SystemCfg;

/**   
* 文件上传、预览 主入口
* @author li_xiaodong
* @date 2016年2月2日 下午9:25:30
*/
@RestController
@RequestMapping(value = "files")
public class FileController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "filesService")
    private FilesService filesService;
    
    @Resource(name = "fileHelpService")
    private FileHelpService fileHelpService;
    
    @Resource(name = "fileHelpNewService")
    private FileHelpNewService fileHelpNewService;
    
    /** 
     * 上传
     * 1、调用FileChannel接口，获取应用系统渠道配置信息config以及拿到一个存在渠道系统的fileNo，；
     * 2、根据配置信息，将文件传往CRIC或weiphoto，拿到fileCode；
     * 3、将fileCode更新到渠道系统的文件表；
     * @param request--
     * @param modelMap
     * @return     
     */
    @RequestMapping(value = "/upload/ess", method = RequestMethod.POST)
    public ReturnView<?, ?> uploadFileEss(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //上传
        try
        {
            rspMap = (Map<String, Object>)fileHelpNewService.upload(request);
        }
        catch (Exception e)
        {
            logger.error("file",
                "FileController",
                "uploadFileEss",
                "input params ",
                0,
                "",
                "上传文件记录信息异常",
                e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "文件上传异常");
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
     * 上传
     * 1、调用FileChannel接口，获取应用系统渠道配置信息config以及拿到一个存在渠道系统的fileNo，；
     * 2、根据配置信息，将文件传往CRIC或weiphoto，拿到fileCode；
     * 3、将fileCode更新到渠道系统的文件表；
     * @param request--
     * @param modelMap
     * @return     
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ReturnView<?, ?> uploadFile(HttpServletRequest request, ModelMap modelMap)
    {
        //返回map
        Map<?, ?> rspMap = new HashMap<String, Object>();
        
        //获取请求参数
        //Map<String, Object> reqMap = bindParamToMap(request);
        
        //旧fileNo
        //String oldFileNo = (String)reqMap.get("fileNo");
        
        //系统code
        String systemCode = SystemCfg.getString("systemCode");
        
        //上传
        rspMap = fileHelpService.upload(request, systemCode);
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * 文件预览
    * @param fileNo
    * @return     
    */
    @RequestMapping(value = "/view/{fileNo}", method = RequestMethod.GET)
    public ReturnView<?, ?> getFilePath(@PathVariable("fileNo") String fileNo)
    {
        
        //返回map
        Map<String, Object> rspMap = null;
        
        try
        {
            rspMap = (Map<String, Object>)fileHelpService.getFilePath(fileNo);
        }
        catch (Exception e)
        {
            logger.error("file",
                "FileController",
                "getFilePath",
                "input params fileNo=" + fileNo,
                0,
                "",
                "获取文件记录信息异常",
                e);
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "文件预览异常");
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
    }
    
}
