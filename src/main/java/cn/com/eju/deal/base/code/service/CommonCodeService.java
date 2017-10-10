package cn.com.eju.deal.base.code.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;

/**   
* Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("commonCodeService")
public class CommonCodeService extends BaseService<CommonCodeDto>
{
    
    /** 
    * 查询所有字典表
    * @return
    * @throws Exception
    */
    public ResultData<?> getAll()
        throws Exception
    {
        
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "commoncodes" + "/";
        
        ResultData<?> backResult = get(url);
        
        return backResult;
    }

    
}
