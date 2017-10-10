package cn.com.eju.deal.base.file.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.file.ess.EssHelper;
import cn.com.eju.deal.base.file.helper.FileHelper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.WebUtil;

/**   
* 上传、预览的业务处理 部分（非渠道版）
* @author li_xiaodong
* @date 2016年2月2日 下午7:57:09
*/
@Service("fileHelpNewService")
public class FileHelpNewService extends BaseService<Object>
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    /** 
    * 1、根据systemCode请求上传；3、上传到指定文件系统；4、更新文件渠道表记录;
    * @param request
    * @param systemCode 系统Code
    * @param oldFileNo 
    */
    public Map<String, Object> upload(HttpServletRequest request)
        throws Exception
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //接口Code
        String channelCode = Constant.FILE_SYSTEM_ESS;
        
        // 生成fileNo
        String fileNo = UUID.randomUUID().toString();
        
        //文件系统配置  
        Map<String, Object> mapTemp = new HashMap<>();
        String uploadUrl = SystemParam.getWebConfigValue("ESS_upload_url");
        String viewUrl = SystemParam.getWebConfigValue("ESS_view_url");
        mapTemp.put("ESS_upload_url", uploadUrl);
        mapTemp.put("ESS_view_url", viewUrl);
        
        //3、上传到指定文件系统
        Map<String, Object> upMap = new HashMap<String, Object>();
        try
        {
            upMap = FileHelper.upload(request, fileNo, channelCode, mapTemp);
        }
        catch (Exception e1)
        {
            logger.error("file",
                "fileNewService",
                "uploadFile",
                "input params = ",
                0,
                WebUtil.getRealIpAddress(request),
                "FileHelper.upload 调用异常",
                e1);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "上传文件失败");
            return rspMap;
        }
        
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rspList = (List<Map<String, Object>>)upMap.get("data");
        
        if (null != rspList && !rspList.isEmpty())
        {
            for (Map<String, Object> map : rspList)
            {
                String returnCode = (String)map.get("returnCode");
                
                //4、更新文件渠道表记录;
                if (returnCode == ReturnCode.SUCCESS)
                {
                    
                    String fileCode = (String)map.get("fileCode");
                    
                    //文件类型
                    String fileType = (String)map.get("fileType");
                    
                    //更新文件渠道表
                    //                    FileDto fileDto = new FileDto();
                    //                    //设置文件主键
                    //                    fileDto.setFileId(fileId);
                    //                    //设置文件code
                    //                    fileDto.setFileCode(fileCode);
                    //                    //设置文件类型
                    //                    fileDto.setFileType(fileType);
                    //                    
                    //                    try
                    //                    {
                    //                        filesService.update(fileDto);
                    //                    }
                    //                    catch (Exception e)
                    //                    {
                    //                        
                    //                        logger.error("File",
                    //                            "FileHelpService",
                    //                            "uploadFile",
                    //                            "input params = " + fileDto.toString(),
                    //                            0,
                    //                            WebUtil.getRealIpAddress(request),
                    //                            "更新文件渠道表记录异常",
                    //                            e);
                    //                        
                    //                        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    //                        rspMap.put(Constant.RETURN_MSG_KEY, "更新文件渠道表记录异常");
                    //                        return rspMap;
                    //                    }
                    
                    try
                    {
                        
                        //设置返回文件fileUrl
                        map.put("fileUrl", getFileUrl(fileCode));
                    }
                    catch (Exception e)
                    {
                        logger.error("file",
                            "FileHelpService",
                            "getFilePath",
                            "input params fileNo=" + fileNo,
                            0,
                            "",
                            "获取文件记录信息异常",
                            e);
                    }
                    
                    //设置返回文件No
                    map.put("fileNo", fileCode);
                    
                    map.remove("fileCode");
                    
                    rspMap.put(Constant.RETURN_DATA_KEY, map);
                    
                }
                else
                {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    return rspMap;
                }
            }
            
        }
        else
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "上传文件失败");
            return rspMap;
        }
        
        return rspMap;
    }
    
    /** 
     * 获取文件路径
     * @return filePath
     * @throws Exception
     */
    private String getFileUrl(String fileCode)
        throws Exception
    {
        
        //文件路径
        String filePath = null;
        try
        {
            
            // 查询路径
            String queryUrl = SystemParam.getWebConfigValue("ESS_view_url");
            
            // 原文件路径
            filePath = EssHelper.getFilePath(queryUrl, fileCode);
            
        }
        catch (Exception e)
        {
            logger.error("file",
                "FileHelpService",
                "getFileUrl",
                "input params fileCode= " + fileCode,
                0,
                "",
                "获取获取文件路径异常",
                e);
            
        }
        
        return filePath;
    }
}
