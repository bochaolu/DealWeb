package cn.com.eju.deal.user.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.user.LoginDto;

/**   
* Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("loginService")
public class LoginService extends BaseService<LoginDto>
{
    /** 
    * 登录
    * @param dto
    * @return
    * @throws Exception
    */
    public ResultData<?> login(LoginDto dto)
        throws Exception
    {
        //调用 接口
        String url = SystemParam.getWebConfigValue("RestServer") + "login";
        
        ResultData<?> backResult = post(url, dto);
        
        return backResult;
        
    }
    
}
