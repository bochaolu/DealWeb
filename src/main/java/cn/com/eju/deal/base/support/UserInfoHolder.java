package cn.com.eju.deal.base.support;

import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.core.exception.DataException;

/**   
* (用户信息holder，用户后台程序使用)
* @author (li_xiaodong)
* @date 2015年10月14日 下午7:49:31
*/
public class UserInfoHolder
{
    
    public static ThreadLocal<UserInfo> userInfoLocal = new ThreadLocal<UserInfo>();
    
    public static void set(UserInfo userInfo)
    {
        userInfoLocal.set(userInfo);
    }
    
    /**
     * 获取用户信息，是否能为空
     * @param canNull
     * @return
     */
    public static UserInfo get(boolean canNull)
    {
        UserInfo userInfo = userInfoLocal.get();
        if (userInfo == null && !canNull)
        {
            throw new DataException("login userInfo is null");
        }
        return userInfo;
    }
    
    public static UserInfo get()
    {
        return get(false);
    }
    
    /**
     * 获取当前登录用户Id
     * @return
     */
    public static Integer getUserId()
    {
        UserInfo userInfo = userInfoLocal.get();
        if (userInfo == null)
        {
            return null;
        }
        return userInfo.getUserId();
    }
    
 
    
   
    
    public static void remove()
    {
        userInfoLocal.remove();
    }
    
}
