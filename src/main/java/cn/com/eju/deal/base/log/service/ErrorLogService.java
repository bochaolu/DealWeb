package cn.com.eju.deal.base.log.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.dto.log.ErrorLogDto;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

/**   
* Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("errorLogService")
public class ErrorLogService extends BaseService<ErrorLogDto>
{
    
    
    /** 
    * 创建
    * @param studentDto
    * @return
    * @throws Exception
    */
    public ResultData<?> create(ErrorLogDto errorLogDto)
        throws Exception
    {
        
        String url = SystemParam.getWebConfigValue("RestServer") + "errorlogs" + "";
        
        ResultData<?> backResult = post(url, errorLogDto);
        
        return backResult;
    }
    

    
}
