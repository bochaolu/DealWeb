package cn.com.eju.deal.base.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 岗位信息
 * @author yujun
 *
 */
public class Post implements Serializable
{
    
    /**
    * TODO(用一句话描述这个变量表示什么)
    */ 
    private static final long serialVersionUID = 1L;

    private Integer postId;
    
    private String postName;
    
    private String postDesc;
    
    private Integer checked;
    
    private Integer groupId;
    
    /**
     * 类型ID
     */
    private Integer typeId;
    
    /**
    * 岗位类型编码
    */
    private String typeCode;
    
    /**
     * 岗位类型名称（用于查询显示）
     */
    private String typeName;
    
    /**
     * 岗位层级
     */
    private Integer postLevel;
    
    /**
     * 岗位层级（显示值，临时变量）
     */
    private String postLevelValue;
    
    /**
     * 岗位审批优先级
     */
    private Integer approveIdx;
    
    private Date dateCreate;
    
    private Integer userIdCreate;
    
    private Integer userApproveIdx;
    
    private String delFlag;
    
    /*
     * 岗位对应的权限信息
     */
    private List<Auth> authList;
    
    private String cityName;
    
    public String getCityName()
    {
        return cityName;
    }
    
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
    
    public Integer getPostId()
    {
        return postId;
    }
    
    public void setPostId(Integer postId)
    {
        this.postId = postId;
    }
    
    public String getPostName()
    {
        return postName;
    }
    
    public void setPostName(String postName)
    {
        this.postName = postName;
    }
    
    public String getPostDesc()
    {
        return postDesc;
    }
    
    public void setPostDesc(String postDesc)
    {
        this.postDesc = postDesc;
    }
    
    public Integer getGroupId()
    {
        return groupId;
    }
    
    public void setGroupId(Integer groupId)
    {
        this.groupId = groupId;
    }
    
    public Date getDateCreate()
    {
        return dateCreate;
    }
    
    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }
    
    public Integer getUserIdCreate()
    {
        return userIdCreate;
    }
    
    public void setUserIdCreate(Integer userIdCreate)
    {
        this.userIdCreate = userIdCreate;
    }
    
    public String getDelFlag()
    {
        return delFlag;
    }
    
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }
    
    public Integer getApproveIdx()
    {
        return approveIdx;
    }
    
    public void setApproveIdx(Integer approveIdx)
    {
        this.approveIdx = approveIdx;
    }
    
    public Integer getTypeId()
    {
        return typeId;
    }
    
    public void setTypeId(Integer typeId)
    {
        this.typeId = typeId;
    }
    
    public Integer getPostLevel()
    {
        return postLevel;
    }
    
    public void setPostLevel(Integer postLevel)
    {
        this.postLevel = postLevel;
    }
    
    public Integer getChecked()
    {
        return checked;
    }
    
    public void setChecked(Integer checked)
    {
        this.checked = checked;
    }
    
    public List<Auth> getAuthList()
    {
        return authList;
    }
    
    public void setAuthList(List<Auth> authList)
    {
        this.authList = authList;
    }
    
    public Integer getUserApproveIdx()
    {
        return userApproveIdx;
    }
    
    public void setUserApproveIdx(Integer userApproveIdx)
    {
        this.userApproveIdx = userApproveIdx;
    }
    
    public String getTypeName()
    {
        return typeName;
    }
    
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
    
    public String getPostLevelValue()
    {
        return postLevelValue;
    }
    
    public void setPostLevelValue(String postLevelValue)
    {
        this.postLevelValue = postLevelValue;
    }
    
    public String getTypeCode()
    {
        return typeCode;
    }
    
    public void setTypeCode(String typeCode)
    {
        this.typeCode = typeCode;
    }
    
}
