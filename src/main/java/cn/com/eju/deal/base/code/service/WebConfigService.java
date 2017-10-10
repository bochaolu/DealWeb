package cn.com.eju.deal.base.code.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.dto.code.WebConfigDto;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.SystemCfg;

/**   
* Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("webConfigService")
public class WebConfigService extends BaseService<WebConfigDto>
{
    
    private final static String REST_SERVICE = SystemCfg.getString("webConfigRestServer");
    
    /** 
    * 查询所有配置表
    * @return
    * @throws Exception
    */
    public ResultData<?> getAll()
        throws Exception
    {
        
        //调用 接口
        String url = REST_SERVICE;
        
        ResultData<?> backResult = get(url);
        
        return backResult;
    }

    
}
