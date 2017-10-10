package cn.com.eju.deal.base.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.model.BaseModel;

public class UserInfo extends BaseModel implements Serializable
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -8700612489345408757L;
    
    private Integer userId;
    
    private String userCode;
    
    private String password;
    
    private String userName;
    
    private String sex;
    
    private String sexName;
    
    private String groupName;
    
    private String groupId;
    
    private String credentialsNum; //证件号码
    
    private String cellphone;
    
    private Integer credentials; //证件类型
    
    private Date entryTime;
    
    private String create;
    
    private Integer defpostId;
    
    private Integer selectpostId;
    
    private Post selectPost;
    
    private Integer exchangeCenterId;
    
    private List<Post> postList;
    
    private Integer selectCityId;
    
    //private String selectCityNo;
    
    /**
     * 在职状态代码
     */
    private Integer inStatus;
    
    /**
     * 在职状态显示值，临时变量
     */
    private String inStatusValue;
    
    /**
     * 入职时间
     */
    private Date inDate;
    
    private String inDateStr;
    
    /**
     * 离职时间
     */
    private Date outDate;
    
    /**
     * 邮箱
     */
    private String email;
    
    private String outDateStr;
    
    private String clientLoginFlag;
    
    private String cityNo;
    
    private String cityName;
    
    public String getInDateStr()
    {
        return inDateStr;
    }
    
    public void setInDateStr(String inDateStr)
    {
        this.inDateStr = inDateStr;
    }
    
    public String getOutDateStr()
    {
        return outDateStr;
    }
    
    public void setOutDateStr(String outDateStr)
    {
        this.outDateStr = outDateStr;
    }
    
    public String getCreate()
    {
        return create;
    }
    
    public void setCreate(String create)
    {
        this.create = create;
    }
    
    public Integer getUserId()
    {
        return userId;
    }
    
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
    
    public String getUserCode()
    {
        return userCode;
    }
    
    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getSex()
    {
        return sex;
    }
    
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    
    public String getGroupName()
    {
        return groupName;
    }
    
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }
    
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
    
    @Override
    public String toString()
    {
        return "UserInfo [userId=" + userId + ", userCode=" + userCode + ", password=" + password + ", userName=" + userName + ", sex=" + sex
            + ", groupName=" + groupName + "]";
    }
    
    public String getGroupId()
    {
        return groupId;
    }
    
    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }
    
    public String getCredentialsNum()
    {
        return credentialsNum;
    }
    
    public void setCredentialsNum(String credentialsNum)
    {
        this.credentialsNum = credentialsNum;
    }
    
    public String getCellphone()
    {
        return cellphone;
    }
    
    public void setCellphone(String cellphone)
    {
        this.cellphone = cellphone;
    }
    
    public Integer getCredentials()
    {
        return credentials;
    }
    
    public void setCredentials(Integer credentials)
    {
        this.credentials = credentials;
    }
    
    public List<Post> getPostList()
    {
        return postList;
    }
    
    public void setPostList(List<Post> postList)
    {
        this.postList = postList;
    }
    
    public Date getEntryTime()
    {
        return entryTime;
    }
    
    public void setEntryTime(Date entryTime)
    {
        this.entryTime = entryTime;
    }
    
    public Integer getInStatus()
    {
        return inStatus;
    }
    
    public void setInStatus(Integer inStatus)
    {
        this.inStatus = inStatus;
    }
    
    public Date getInDate()
    {
        return inDate;
    }
    
    public void setInDate(Date inDate)
    {
        this.inDate = inDate;
    }
    
    public Date getOutDate()
    {
        return outDate;
    }
    
    public void setOutDate(Date outDate)
    {
        this.outDate = outDate;
    }
    
    public String getInStatusValue()
    {
        return inStatusValue;
    }
    
    public void setInStatusValue(String inStatusValue)
    {
        this.inStatusValue = inStatusValue;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public Integer getDefpostId()
    {
        return defpostId;
    }
    
    public void setDefpostId(Integer defpostId)
    {
        this.defpostId = defpostId;
    }
    
    public Integer getSelectpostId()
    {
        return selectpostId;
    }
    
    public void setSelectpostId(Integer selectpostId)
    {
        this.selectpostId = selectpostId;
    }
    
    public Post getSelectPost()
    {
        return selectPost;
    }
    
    public void setSelectPost(Post selectPost)
    {
        this.selectPost = selectPost;
    }
    
    public Integer getSelectCityId()
    {
        return selectCityId;
    }
    
    public void setSelectCityId(Integer selectCityId)
    {
        this.selectCityId = selectCityId;
    }
    
    /*  public String getSelectCityNo()
      {
          return selectCityNo;
      }

      public void setSelectCityNo(String selectCityNo)
      {
          this.selectCityNo = selectCityNo;
      }*/
    
    public Integer getExchangeCenterId()
    {
        return exchangeCenterId;
    }
    
    public void setExchangeCenterId(Integer exchangeCenterId)
    {
        this.exchangeCenterId = exchangeCenterId;
    }
    
    public String getClientLoginFlag()
    {
        return clientLoginFlag;
    }
    
    public void setClientLoginFlag(String clientLoginFlag)
    {
        this.clientLoginFlag = clientLoginFlag;
    }
    
    public String getCityNo()
    {
        return cityNo;
    }
    
    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo;
    }
    
    public String getCityName()
    {
        return cityName;
    }
    
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
    
    public String getSexName()
    {
        return SystemParam.getDicValueByDicCode(this.sex);
    }
    
    public void setSexName(String sexName)
    {
        this.sexName = sexName;
    }
}
