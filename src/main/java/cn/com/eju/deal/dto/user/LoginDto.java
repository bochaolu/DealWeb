package cn.com.eju.deal.dto.user;

import java.io.Serializable;

/**   
* 登录loginDto
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
public class LoginDto implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 9088383198250784647L;
    
    private String loginName;
    
    private String password;
    
    private String systemCode;
    
    public String getLoginName()
    {
        return loginName;
    }
    
    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getSystemCode()
    {
        return systemCode;
    }
    
    public void setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
    }
    
    @Override
    public String toString()
    {
        return "LoginDto [loginName=" + loginName + ", password=" + password + ", systemCode=" + systemCode + "]";
    }
    
}