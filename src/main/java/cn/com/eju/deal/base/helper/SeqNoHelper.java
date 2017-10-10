package cn.com.eju.deal.base.helper;

import cn.com.eju.deal.base.seqNo.service.SeqNoService;
import cn.com.eju.deal.core.helper.SpringConfigHelper;
import cn.com.eju.deal.core.support.ResultData;

/**   
* 获取编号的共通
* @author (li_xiaodong)
* @date 2015年10月23日 下午1:40:33
*/
public class SeqNoHelper
{
    public static SeqNoHelper instance = null;
    
    /** 
    * 实现单例
    * @return
    */
    public static synchronized SeqNoHelper getInstance()
    {
        
        if (null == instance)
        {
            instance = new SeqNoHelper();
        }
        
        return instance;
        
    }
    
    /**
    * 
    * 获取最新的编号
    * @param operateUserId
    * @param typecode
    * @return
     */
    public String getSeqNoByTypeCode(String typecode)
    {
        
        final SeqNoService seqNoService = (SeqNoService)SpringConfigHelper.getBean("seqNoService");
        
        ResultData<?> result = new ResultData<String>();
        try
        {
            result = seqNoService.getSeqNo(typecode);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        String seqNo = (String)result.getReturnData();
        
        return seqNo;
    };
    
}
