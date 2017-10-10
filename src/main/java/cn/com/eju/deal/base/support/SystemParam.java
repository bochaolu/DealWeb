package cn.com.eju.deal.base.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.eju.deal.base.code.service.CommonCodeService;
import cn.com.eju.deal.base.code.service.WebConfigService;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.dto.code.WebConfigDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.helper.SpringConfigHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;

/**   
* 系统参数缓存，包括了系统通用参数，行权限参数等各种参数，都是以单例的形式存在
* @author (li_xiaodong)
* @date 2015年11月9日 下午3:25:12
*/
public class SystemParam
{
    
    /**
     * 码表对象List，所有的码表对象都会缓存在这个对象中
     */
    private static Map<String, List<CommonCodeDto>> codeListMap;
    
    /**
     * 码表对象，所有的码表对象都会缓存在这个对象中
     */
    private static Map<String, CommonCodeDto> codeMap;
    
    /**
     * 数据配置map对象
     */
    private static Map<String, String> webConfigMap = new HashMap<String, String>();
    
    /** 日志 */
    private static final Logger log = LoggerFactory.getLogger(SystemParam.class);
    
    /**
     * 日志
     */
    private static final LogHelper logger = LogHelper.getLogger(SystemParam.class);
    
    /**
     * 根据码表的key获取码表对象
     * @param mapKey 码表的key
     * @return List<CodeDto>
     */
    public static String getDicValueByDicCode(String dicCode)
    {
        if (codeMap == null)
        {
            initCodeMap();
        }
        
        String dicValue = "";
        
        if (StringUtil.isNotEmpty(dicCode))
        {
            
            CommonCodeDto CodeDto = codeMap.get(dicCode);
            
            if (null != CodeDto)
            {
                dicValue = CodeDto.getDicValue();
            }
        }
        
        return dicValue;
    }
    
    /**
     * 根据码表的key获取码表对象
     * @param mapKey 码表的key
     * @return List<CodeDto>
     */
    public static CommonCodeDto getCodeMapByKey(String dicCode)
    {
        if (codeMap == null)
        {
            initCodeMap();
        }
        
        return codeMap.get(dicCode);
    }
    
    /**
     * 根据码表的key和代码获取码值内容
     * @param mapKey 码表的key
     * @param dicCode 码值
     * @return 码值内容（名称）
     */
    public static String getCodeNameByMapKeyAndCode(String mapKey, String dicCode)
    {
        
        List<CommonCodeDto> codeMap = getCodeListByKey(mapKey);
        if (codeMap != null && codeMap.size() > 0)
        {
            for (CommonCodeDto CodeDto : codeMap)
            {
                if (null != dicCode && dicCode.equals(CodeDto.getDicCode()))
                {
                    return CodeDto.getDicValue();
                }
            }
        }
        return "";
    }
    
    /** 
    * 根据webConfigKey获取配置值 value 
    * @param key
    * @return
     * @throws Exception 
    */
    public static String getWebConfigValue(String webConfigKey) throws Exception
    {
        
        if (webConfigMap.isEmpty())
        {
            //初始化配置表信息
            initWebConfigPram();
        }
        
        String value = null;
        if (webConfigMap.containsKey(webConfigKey))
        {
            value = webConfigMap.get(webConfigKey);
            if (log.isDebugEnabled())
            {
                log.debug("获取配置信息成功:{}={}", webConfigKey, value);
            }
        }
        else
        {
            if (log.isWarnEnabled())
            {
                log.warn("获取配置信息失败:{}", webConfigKey);
            }
        }
        
        return value;
    }
    
    /**
     * 根据码表的key获取码表对象List
     * @param mapKey 码表的key
     * @return List<CodeDto>
     */
    public static List<CommonCodeDto> getCodeListByKey(String typeId)
    {
        if (codeListMap == null)
        {
            initCodeMap();
        }
        
        return codeListMap.get(typeId);
    }
    
    /** 
     * <p>初始化码表信息到内存中。</p>
     */
    private synchronized static void initCodeMap()
    {
        codeListMap = new HashMap<String, List<CommonCodeDto>>();
        
        codeMap = new HashMap<String, CommonCodeDto>();
        
        //查询出码表
        List<CommonCodeDto> codeMapList = loadCodeData();
        
        //        for (CommonCodeDto CodeDto : codeMapList)
        //        {
        //            //dicCode
        //            String dicCodeKey = "" + CodeDto.getDicCode();
        //            
        //            //typeId
        //            String typeIdKey = "" + CodeDto.getTypeId();
        //            
        //            //拼装codeMap
        //            if (!codeMap.containsKey(dicCodeKey))
        //            {
        //                codeMap.put(dicCodeKey, CodeDto);
        //            }
        //            
        //            //拼装codeListMap
        //            if (codeListMap.containsKey(typeIdKey))
        //            {
        //                codeListMap.get(typeIdKey).add(CodeDto);
        //            }
        //            else
        //            {
        //                List<CommonCodeDto> codeList = new ArrayList<CommonCodeDto>();
        //                codeList.add(CodeDto);
        //                codeListMap.put(typeIdKey, codeList);
        //            }
        //            
        //        }
        
        if (null != codeMapList && codeMapList.size() > 0)
        {
            CommonCodeDto codeDto;
            
            for (int i = 0; i < codeMapList.size(); i++)
            {
                
                Map<?, ?> mapTemp = (Map<?, ?>)codeMapList.get(i);
                
                //数据转换
                codeDto = MapToEntityConvertUtil.convert(mapTemp, CommonCodeDto.class, "");
                
                //dicCode
                String dicCodeKey = "" + codeDto.getDicCode();
                
                //typeId
                String typeIdKey = "" + codeDto.getTypeId();
                
                //拼装codeMap
                if (!codeMap.containsKey(dicCodeKey))
                {
                    codeMap.put(dicCodeKey, codeDto);
                }
                
                //拼装codeListMap
                if (codeListMap.containsKey(typeIdKey))
                {
                    codeListMap.get(typeIdKey).add(codeDto);
                }
                else
                {
                    List<CommonCodeDto> codeList = new ArrayList<CommonCodeDto>();
                    codeList.add(codeDto);
                    codeListMap.put(typeIdKey, codeList);
                }
                
            }
        }
        
    }
    
    /** 
    * 查询出码表
    * @return
    */
    public static List<CommonCodeDto> loadCodeData()
    {
        final CommonCodeService commonCodeService = (CommonCodeService)SpringConfigHelper.getBean("commonCodeService");
        
        ResultData<?> resultData = new ResultData<List<CommonCodeDto>>();
        try
        {
            resultData = commonCodeService.getAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        List<CommonCodeDto> codeMapList = (List<CommonCodeDto>)resultData.getReturnData();
        
        return codeMapList;
    }
    
    /**
    *
    * <p>初始化Web数据配置信息到内存中。</p>
     * @throws Exception 
    *
    */
    private synchronized static void initWebConfigPram() throws Exception
    {
        
        webConfigMap = new HashMap<String, String>();
        
        //取得系统表数据
        List<WebConfigDto> webConfigInfoList = loadWebConfigData();
        
        //        for (WebConfigDto WebConfigDto : webConfigInfoList)
        //        {
        //            String key = WebConfigDto.getWebConfigName();
        //            
        //            if (!webConfigMap.containsKey(key))
        //            {
        //                
        //                webConfigMap.put(key, WebConfigDto.getWebConfigValue());
        //            }
        //            
        //        }
        
        if (null != webConfigInfoList && webConfigInfoList.size() > 0)
        {
            WebConfigDto webConfigDto;
            
            for (int i = 0; i < webConfigInfoList.size(); i++)
            {
                
                Map<?, ?> mapTemp = (Map<?, ?>)webConfigInfoList.get(i);
                
                //数据转换
                webConfigDto = MapToEntityConvertUtil.convert(mapTemp, WebConfigDto.class, "");
                
                String key = webConfigDto.getWebConfigName();
                
                if (!webConfigMap.containsKey(key))
                {
                    
                    webConfigMap.put(key, webConfigDto.getWebConfigValue());
                }
            }
        }
        
    }
    
    /** 
     * 查询出数据配置表
     * @return
     */
    public static List<WebConfigDto> loadWebConfigData()
        throws Exception
    {
        
        final WebConfigService webConfigService = (WebConfigService)SpringConfigHelper.getBean("webConfigService");
        
        ResultData<?> resultData = new ResultData<List<CommonCodeDto>>();
        try
        {
            resultData = webConfigService.getAll();
        }
        catch (Exception e)
        {
            logger.error("获取配置信息失败", e);
            
            throw new Exception(e);
        }
        
        List<WebConfigDto> WebConfigList = (List<WebConfigDto>)resultData.getReturnData();
        
        return WebConfigList;
        
    }
    
    /**
     * 刷新字典表、配置表信息
     * @throws Exception 
     */
    public static void refreshCodeMap() throws Exception
    {
        //初始化字典表信息
        initCodeMap();
        
        //初始化配置表信息
        initWebConfigPram();
        
    }
    
}
