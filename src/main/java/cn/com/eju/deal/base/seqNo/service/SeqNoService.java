package cn.com.eju.deal.base.seqNo.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

/**   
* Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("seqNoService")
public class SeqNoService extends BaseService<Object>
{
    
    /** 
    * 查询
    * @param id
    * @return
    * @throws Exception
    */
    public ResultData<?> getSeqNo(String typeCode)
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "seqnos" + "/{typeCode}";
        
        ResultData<?> backResult = get(url, typeCode);
        
        return backResult;
    }
    
}
