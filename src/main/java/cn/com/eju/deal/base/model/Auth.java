package cn.com.eju.deal.base.model;

import cn.com.eju.deal.core.model.BaseModel;

/**   
* 权限模块表
* @author xulong
* @date 2015年11月2日 下午5:41:42
*/
public class Auth extends BaseModel
{
    /**
    * 
    */ 
    private static final long serialVersionUID = -4176623041421816728L;

    private Integer authId;
    
    private String authName; //权限名称
    
    private String authCode; //权限编码
    
    private String authDesc; //权限描述
    
    private String type; //权限分类(M:模块;P:页面;B:按钮)
    
    private String url; //页面路径
    
    private Integer parentId; //父Id
    
    private Integer sortNo; //排序数
    
    private String treeIdStr; //层次ID   
    /**
     * 所属系统Id 对应usr_system表
     */
    private Integer systemId;
    /**
     * 所属系统名称 （临时变量，用于列表显示）
     */
    private String systemName;
    
    
    public Integer getAuthId()
    {
        return authId == null ? 0 : authId;
    }
    
    public void setAuthId(Integer authId)
    {
        this.authId = authId;
    }
    
    public String getAuthName()
    {
        return authName == null ? "" : authName;
    }
    
    public void setAuthName(String authName)
    {
        this.authName = authName == null ? null : authName.trim();
    }
    
    public String getAuthCode()
    {
        return authCode == null ? "" : authCode;
    }
    
    public void setAuthCode(String authCode)
    {
        this.authCode = authCode == null ? null : authCode.trim();
    }
    
    public String getAuthDesc()
    {
        return authDesc == null ? "" : authDesc;
    }
    
    public void setAuthDesc(String authDesc)
    {
        this.authDesc = authDesc == null ? null : authDesc.trim();
    }
    
    public String getType()
    {
        return type == null ? "" : type;
    }
    
    public void setType(String type)
    {
        this.type = type == null ? null : type.trim();
    }
    
    public String getUrl()
    {
        return url == null ? "" : url;
    }
    
    public void setUrl(String url)
    {
        this.url = url == null ? null : url.trim();
    }
    
    public String getTreeIdStr()
    {
        return treeIdStr;
    }
    
    public void setTreeIdStr(String treeIdStr)
    {
        this.treeIdStr = treeIdStr;
    }
    
    public Integer getParentId()
    {
        return parentId == null ? 0 : parentId;
    }
    
    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }
    
    public Integer getSortNo()
    {
        return sortNo;
    }
    
    public void setSortNo(Integer sortNo)
    {
        this.sortNo = sortNo;
    }

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
    
}