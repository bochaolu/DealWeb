package cn.com.eju.deal.base.file.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.file.dto.FileDto;
import cn.com.eju.deal.base.file.dto.ReqUploadDto;
import cn.com.eju.deal.base.file.helper.FileHelper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;

/**   
* 上传、预览的业务处理 部分
* @author li_xiaodong
* @date 2016年2月2日 下午7:57:09
*/
@Service("fileHelpService")
public class FileHelpService extends BaseService<Object>
{
    
    @Resource(name = "filesService")
    private FilesService filesService;
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    /** 
    * 上传--默认
    * @param request
    * @param systemCode
    * @return
    * @throws Exception
    */
    public Map<?, ?> upload(HttpServletRequest request, String systemCode)
    {
        
        return upload(request, null, null, systemCode);
    }
    
    /** 
    * 上传-- 流
    * @param inputStream
    * @param fileName
    * @param systemCode
    * @return
    * @throws Exception
    */
    public Map<?, ?> upload(InputStream inputStream, String fileName, String systemCode)
    {
        
        return upload(null, inputStream, fileName, systemCode);
    }
    
    /** 
     * 文件预览--默认使用方法-取原图
     * @param fileNo
     * @return     
     */
    public Map<String, Object> getFilePath(String fileNo)
        throws Exception
    {
        return getFilePath(fileNo, null, null, null, null, null);
    }
    
    /** 
    * 文件预览--weiphoto
    * @param fileNo
    * @param weiphotoPicSize
    * @return
    * @throws Exception
    */
    public Map<String, Object> getFilePath(String fileNo, String weiphotoPicSize)
        throws Exception
    {
        return getFilePath(fileNo, weiphotoPicSize, null, null, null, null);
    }
    
    /** 
    * 文件预览--CRIC
    * 1、获取渠道配置信息
    * 2、获取文件记录
    * 3、获取文件
    * @param fileNo
    * @param isCricHandle
    * @param cricHandleMap
    * @return
    * @throws Exception
    */
    public Map<String, Object> getFilePath(String fileNo, String isCricHandle, Map<String, Object> cricHandleMap)
        throws Exception
    {
        return getFilePath(fileNo, null, null, null, isCricHandle, cricHandleMap);
    }
    
    /** 
    * 文件预览--ESS
    * 1、获取渠道配置信息
    * 2、获取文件记录
    * 3、获取文件
    * @param fileNo
    * @param weiphotoPicSize
    * @return
    * @throws Exception
    */
    public Map<String, Object> getFilePath(String fileNo, String essWidth, String essHeight)
        throws Exception
    {
        return getFilePath(fileNo, null, essWidth, essHeight, null, null);
    }
    
    /** 
     * 文件预览--最大参数集
     * @param fileNo
     * @param weiphotoPicSize
     * @return
     * @throws Exception
     */
    private Map<String, Object> getFilePath(String fileNo, String weiphotoPicSize, String essWidth, String essHeight,
        String isCricHandle, Map<String, Object> cricHandleMap)
        throws Exception
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //请求参数
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("fileNo", fileNo);
        
        if (StringUtil.isNotEmpty(weiphotoPicSize))
        {
            reqMap.put("weiphotoPicSize", weiphotoPicSize);
        }
        if (StringUtil.isNotEmpty(essWidth) && StringUtil.isNotEmpty(essHeight))
        {
            reqMap.put("essWidth", essWidth);
            reqMap.put("essHeight", essHeight);
        }
        if (StringUtil.isNotEmpty(isCricHandle))
        {
            reqMap.put("isCricHandle", isCricHandle);
        }
        if (Constant.FILE_SYSTEM_CRIC_IS_HANDLE_YES.equals(isCricHandle))
        {
            reqMap.put("cricHandleMap", cricHandleMap);
        }
        
        String filePath = getFilePath(reqMap);
        
        //返回文件path
        rspMap.put("fileUrl", filePath);
        
        return rspMap;
    }
    
    /** 
     * 获取文件路径
     * @return filePath
     * @throws Exception
     */
    private String getFilePath(Map<String, Object> reqMap)
        throws Exception
    {
        
        //文件路径
        String filePath = null;
        try
        {
            ResultData<?> reback = filesService.getFilePath(reqMap);
            
            //文件路径
            filePath = (String)reback.getReturnData();
            
        }
        catch (Exception e)
        {
            logger.error("file",
                "FileHelpService",
                "getFilePath",
                "input params reqMap= " + reqMap.toString(),
                0,
                "",
                "获取获取文件路径异常",
                e);
            
        }
        
        return filePath;
    }
    
    /** 
    * 文件预览--最大参数集
    * 1、获取渠道配置信息
    * 2、获取文件记录
    * 3、获取文件
    * @param fileNo
    * @param weiphotoPicSize
    * @return
    * @throws Exception
    */
    //    @Deprecated
    //    public Map<String, Object> getFilePath1(String fileNo, String weiphotoPicSize, String essWidth, String essHeight,
    //        String isCricHandle, Map<String, Object> cricHandleMap)
    //        throws Exception
    //    {
    //        
    //        //返回map
    //        Map<String, Object> rspMap = new HashMap<String, Object>();
    //        
    //        //1、获取渠道配置信息
    //        Map<?, ?> mapTemp = getFileConfig();
    //        if (!ReturnCode.SUCCESS.equals(mapTemp.get(Constant.RETURN_CODE_KEY)))
    //        {
    //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    //            rspMap.put(Constant.RETURN_MSG_KEY, "获取渠道配置异常");
    //            return rspMap;
    //        }
    //        
    //        //2、获取文件记录
    //        FileDto fileDto = getFileByFileNo(fileNo);
    //        if (null == fileDto)
    //        {
    //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    //            rspMap.put(Constant.RETURN_MSG_KEY, "文件不存在");
    //            return rspMap;
    //        }
    //        
    //        //fileCode
    //        String fileCode = fileDto.getFileCode();
    //        //渠道Code
    //        String channelCode = fileDto.getChannelCode();
    //        //文件类型
    //        String fileType = fileDto.getFileType();
    //        
    //        //3、获取文件
    //        rspMap = FileHelper.getFilePath(channelCode,
    //            fileCode,
    //            mapTemp,
    //            fileType,
    //            weiphotoPicSize,
    //            isCricHandle,
    //            cricHandleMap,
    //            essWidth,
    //            essHeight);
    //        
    //        return rspMap;
    //    }
    
    /** 
    * 向渠道系统发起请求 ，  获取文件记录fileDto
    * @param fileNo
    * @return
    * @throws Exception
    */
    //    private FileDto getFileByFileNo(String fileNo)
    //        throws Exception
    //    {
    //        
    //        FileDto fileDto = null;
    //        
    //        //调用渠道系统，获取文件记录信息
    //        ResultData<?> reback = null;
    //        try
    //        {
    //            reback = filesService.getByFileNo(fileNo);
    //        }
    //        catch (Exception e)
    //        {
    //            logger.error("file",
    //                "FileHelpService",
    //                "getFilePath getByFileNo",
    //                "input params = ",
    //                0,
    //                "",
    //                "获取文件记录信息异常",
    //                e);
    //        }
    //        
    //        Map<?, ?> mapTemppp = (Map<?, ?>)reback.getReturnData();
    //        if (null != mapTemppp)
    //        {
    //            
    //            fileDto = MapToEntityConvertUtil.convert(mapTemppp, FileDto.class, "");
    //            
    //        }
    //        
    //        return fileDto;
    //    }
    
    /** 
     * 向渠道系统发起请求 ，  获取文件渠道（CRIC/weiphoto）的相关配置
     * @param fileNo
     * @return     
     */
    //    @SuppressWarnings("unchecked")
    //    public Map<?, ?> getFileConfig()
    //        throws Exception
    //    {
    //        
    //        //返回map
    //        Map<String, Object> rspMap = new HashMap<String, Object>();
    //        
    //        //2、获取渠道配置信息
    //        Map<?, ?> mapTemp = null;
    //        try
    //        {
    //            ResultData<?> reback = filesService.getFileConfig(null);
    //            
    //            //页面数据
    //            mapTemp = (Map<?, ?>)reback.getReturnData();
    //            if (null == mapTemp)
    //            {
    //                logger.error("file",
    //                    "FileHelpService",
    //                    "getFilePath getFileConfig",
    //                    "input params = ",
    //                    0,
    //                    "",
    //                    "获取渠道配置为空",
    //                    null);
    //                
    //                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    //                rspMap.put(Constant.RETURN_MSG_KEY, "获取渠道配置为空");
    //                return rspMap;
    //            }
    //            
    //            rspMap = (Map<String, Object>)mapTemp;
    //            
    //        }
    //        catch (Exception e)
    //        {
    //            logger.error("file",
    //                "FileHelpService",
    //                "getFilePath getFileConfig",
    //                "input params = ",
    //                0,
    //                "",
    //                "获取渠道配置异常",
    //                e);
    //            
    //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    //            rspMap.put(Constant.RETURN_MSG_KEY, "获取渠道配置异常");
    //            return rspMap;
    //        }
    //        
    //        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.SUCCESS);
    //        
    //        return rspMap;
    //    }
    
    /** 
    * 向渠道系统发起上传请求，拿到（channelCode、fileId、fileNo）
    * @param systemCode
    * @return
    * @throws Exception
    */
    //    @Deprecated
    //    public UploadInfoDto getUploadInfoDto(String systemCode)
    //        throws Exception
    //    {
    //        
    //        //1、获取渠道信息
    //        UploadInfoDto uploadInfoDto = null;
    //        
    //        //请求Map
    //        Map<String, Object> reqMp = new HashMap<String, Object>();
    //        reqMp.put("systemCode", systemCode);
    //        
    //        try
    //        {
    //            ResultData<?> reback = filesService.getChannelInfo(reqMp);
    //            
    //            //页面数据
    //            Map<?, ?> mapTemp = (Map<?, ?>)reback.getReturnData();
    //            if (null != mapTemp)
    //            {
    //                uploadInfoDto = MapToEntityConvertUtil.convert(mapTemp, UploadInfoDto.class, "");
    //            }
    //            else
    //            {
    //                logger.error("file",
    //                    "FileHelpService",
    //                    "uploadFile",
    //                    "input params = " + reqMp.toString(),
    //                    UserInfoHolder.getUserId(),
    //                    "",
    //                    "获取上传渠道为空",
    //                    null);
    //            }
    //            
    //        }
    //        catch (Exception e)
    //        {
    //            logger.error("file",
    //                "FileController",
    //                "uploadFile",
    //                "input params = " + reqMp.toString(),
    //                UserInfoHolder.getUserId(),
    //                "",
    //                "获取上传渠道异常",
    //                e);
    //            
    //        }
    //        
    //        return uploadInfoDto;
    //    }
    
    /** 
    * 1、根据systemCode获取渠道信息；2、获取渠道配置信息；3、上传到指定文件系统；4、更新文件渠道表记录;
    * @param request
    * @param systemCode 系统Code
    * @param oldFileNo 
    */
    //    @Deprecated
    //    private Map<String, Object> upload1(HttpServletRequest request, InputStream inputStream, String fileName,
    //        String systemCode, String oldFileNo)
    //    {
    //        //返回map
    //        Map<String, Object> rspMap = new HashMap<String, Object>();
    //        
    //        //1、获取渠道信息
    //        UploadInfoDto uploadInfoDto = null;
    //        try
    //        {
    //            uploadInfoDto = getUploadInfoDto(systemCode);
    //            if (null == uploadInfoDto)
    //            {
    //                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    //                rspMap.put(Constant.RETURN_MSG_KEY, "获取上传渠道信息为空");
    //                return rspMap;
    //            }
    //        }
    //        catch (Exception e2)
    //        {
    //            logger.error("file",
    //                "FileHelpService",
    //                "uploadFile",
    //                "input params = systemCode=" + systemCode,
    //                0,
    //                WebUtil.getRealIpAddress(request),
    //                "获取上传渠道异常",
    //                e2);
    //            
    //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    //            rspMap.put(Constant.RETURN_MSG_KEY, "获取上传渠道信息异常");
    //            return rspMap;
    //        }
    //        
    //        //2、获取渠道配置信息
    //        Map<?, ?> mapTemp;
    //        try
    //        {
    //            mapTemp = getFileConfig();
    //        }
    //        catch (Exception e1)
    //        {
    //            logger.error("file",
    //                "FileHelpService",
    //                "uploadFile",
    //                "input params = ",
    //                0,
    //                WebUtil.getRealIpAddress(request),
    //                "获取渠道配置异常",
    //                e1);
    //            
    //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    //            rspMap.put(Constant.RETURN_MSG_KEY, "获取渠道配置异常");
    //            return rspMap;
    //        }
    //        
    //        //接口Code
    //        String channelCode = uploadInfoDto.getChannelCode();
    //        
    //        //文件Id
    //        Integer fileId = uploadInfoDto.getFileId();
    //        
    //        //文件No
    //        String fileNo = uploadInfoDto.getFileNo();
    //        
    //        //3、上传到指定文件系统
    //        Map<String, Object> upMap = new HashMap<String, Object>();
    //        try
    //        {
    //            upMap = FileHelper.upload(request, fileNo, channelCode, mapTemp);
    //        }
    //        catch (Exception e1)
    //        {
    //            logger.error("file",
    //                "FileHelpService",
    //                "uploadFile",
    //                "input params = ",
    //                0,
    //                WebUtil.getRealIpAddress(request),
    //                "FileHelper.upload 调用异常",
    //                e1);
    //            
    //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    //            rspMap.put(Constant.RETURN_MSG_KEY, "上传文件失败");
    //            return rspMap;
    //        }
    //        
    //        @SuppressWarnings("unchecked")
    //        List<Map<String, Object>> rspList = (List<Map<String, Object>>)upMap.get("data");
    //        
    //        if (null != rspList && !rspList.isEmpty())
    //        {
    //            for (Map<String, Object> map : rspList)
    //            {
    //                String returnCode = (String)map.get("returnCode");
    //                
    //                //4、更新文件渠道表记录;
    //                if (returnCode == ReturnCode.SUCCESS)
    //                {
    //                    
    //                    String fileCode = (String)map.get("fileCode");
    //                    
    //                    //文件类型
    //                    String fileType = (String)map.get("fileType");
    //                    
    //                    //更新文件渠道表
    //                    FileDto fileDto = new FileDto();
    //                    //设置文件主键
    //                    fileDto.setFileId(fileId);
    //                    //设置文件code
    //                    fileDto.setFileCode(fileCode);
    //                    //设置文件状态
    //                    fileDto.setFileState(10002);
    //                    //设置上传时间
    //                    fileDto.setUploadTime(new Date());
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
    //                    
    //                    //返回fileUrl
    //                    Map<String, Object> rspTmpMap = new HashMap<String, Object>();
    //                    
    //                    try
    //                    {
    //                        rspTmpMap = (Map<String, Object>)getFilePath(fileNo);
    //                    }
    //                    catch (Exception e)
    //                    {
    //                        logger.error("file",
    //                            "FileHelpService",
    //                            "getFilePath",
    //                            "input params fileNo=" + fileNo,
    //                            0,
    //                            "",
    //                            "获取文件记录信息异常",
    //                            e);
    //                    }
    //                    
    //                    //设置返回文件fileUrl
    //                    map.put("fileUrl", rspTmpMap.get("fileUrl"));
    //                    
    //                    //设置返回文件No
    //                    map.put("fileNo", fileNo);
    //                    
    //                    rspMap.put(Constant.RETURN_DATA_KEY, map);
    //                    
    //                }
    //                else
    //                {
    //                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    //                    return rspMap;
    //                }
    //            }
    //            
    //            //5、如果是文件重编辑，需要删除原文件
    //            if (StringUtil.isNotEmpty(oldFileNo))
    //            {
    //                
    //                //5、删除文件渠道系统的原文件记录
    //                try
    //                {
    //                    filesService.deleteByFileNo(oldFileNo, UserInfoHolder.getUserId());
    //                }
    //                catch (Exception e)
    //                {
    //                    logger.error("File",
    //                        "FileHelpService",
    //                        "uploadFile filesService.deleteByFileNo",
    //                        "input params  oldFileNo=" + oldFileNo,
    //                        UserInfoHolder.getUserId(),
    //                        WebUtil.getRealIpAddress(request),
    //                        "删除文件渠道系统的原文件记录 失败",
    //                        e);
    //                    
    //                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    //                    rspMap.put(Constant.RETURN_MSG_KEY, "上传删除文件渠道系统的原文件记录失败");
    //                    return rspMap;
    //                }
    //                
    //            }
    //            
    //        }
    //        else
    //        {
    //            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
    //            rspMap.put(Constant.RETURN_MSG_KEY, "上传文件失败");
    //            return rspMap;
    //        }
    //        
    //        return rspMap;
    //    }
    
    /** 
    * 1、根据systemCode请求上传；3、上传到指定文件系统；4、更新文件渠道表记录;
    * @param request
    * @param systemCode 系统Code
    * @param oldFileNo 
    */
    private Map<String, Object> upload(HttpServletRequest request, InputStream inputStream, String fileName,
        String systemCode)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //1、获取渠道信息
        ReqUploadDto reqUploadDto = null;
        try
        {
            ResultData<?> resultData = filesService.reqUpload(systemCode);
            if (null == resultData)
            {
                rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                rspMap.put(Constant.RETURN_MSG_KEY, "请求上传异常");
                return rspMap;
            }
            else
            {
                Map<?, ?> mapTemppp = (Map<?, ?>)resultData.getReturnData();
                if (null != mapTemppp)
                {
                    reqUploadDto = MapToEntityConvertUtil.convert(mapTemppp, ReqUploadDto.class, "");
                }
            }
        }
        catch (Exception e2)
        {
            logger.error("file",
                "FileHelpService",
                "upload",
                "input params = systemCode=" + systemCode,
                0,
                WebUtil.getRealIpAddress(request),
                "请求上传异常",
                e2);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "请求上传异常");
            return rspMap;
        }
        
        //接口Code
        String channelCode = reqUploadDto.getChannelCode();
        
        //文件Id
        Integer fileId = reqUploadDto.getFileId();
        
        //文件No
        String fileNo = reqUploadDto.getFileNo();
        
        //文件系统配置
        Map<String, Object> mapTemp = reqUploadDto.getFileCfgInfo();
        
        //3、上传到指定文件系统
        Map<String, Object> upMap = new HashMap<String, Object>();
        try
        {
            upMap = FileHelper.upload(request, fileNo, channelCode, mapTemp);
        }
        catch (Exception e1)
        {
            logger.error("file",
                "FileHelpService",
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
                    FileDto fileDto = new FileDto();
                    //设置文件主键
                    fileDto.setFileId(fileId);
                    //设置文件code
                    fileDto.setFileCode(fileCode);
                    //设置文件类型
                    fileDto.setFileType(fileType);
                    
                    try
                    {
                        filesService.update(fileDto);
                    }
                    catch (Exception e)
                    {
                        
                        logger.error("File",
                            "FileHelpService",
                            "uploadFile",
                            "input params = " + fileDto.toString(),
                            0,
                            WebUtil.getRealIpAddress(request),
                            "更新文件渠道表记录异常",
                            e);
                        
                        rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                        rspMap.put(Constant.RETURN_MSG_KEY, "更新文件渠道表记录异常");
                        return rspMap;
                    }
                    
                    try
                    {
                        //请求参数
                        Map<String, Object> reqMap = new HashMap<String, Object>();
                        reqMap.put("fileNo", fileNo);
                        
                        String filePath = getFilePath(reqMap);
                        
                        //设置返回文件fileUrl
                        map.put("fileUrl", filePath);
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
                    map.put("fileNo", fileNo);
                    
                    map.remove("fileCode");
                    
                    rspMap.put(Constant.RETURN_DATA_KEY, map);
                    
                }
                else
                {
                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
                    return rspMap;
                }
            }
            
            //5、如果是文件重编辑，需要删除原文件
            //            if (StringUtil.isNotEmpty(oldFileNo))
            //            {
            //                
            //                //5、删除文件渠道系统的原文件记录
            //                try
            //                {
            //                    filesService.deleteByFileNo(oldFileNo, UserInfoHolder.getUserId());
            //                }
            //                catch (Exception e)
            //                {
            //                    logger.error("File",
            //                        "FileHelpService",
            //                        "uploadFile filesService.deleteByFileNo",
            //                        "input params  oldFileNo=" + oldFileNo,
            //                        UserInfoHolder.getUserId(),
            //                        WebUtil.getRealIpAddress(request),
            //                        "删除文件渠道系统的原文件记录 失败",
            //                        e);
            //                    
            //                    rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            //                    rspMap.put(Constant.RETURN_MSG_KEY, "上传删除文件渠道系统的原文件记录失败");
            //                    return rspMap;
            //                }
            //                
            //            }
            
        }
        else
        {
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            rspMap.put(Constant.RETURN_MSG_KEY, "上传文件失败");
            return rspMap;
        }
        
        return rspMap;
    }
}
