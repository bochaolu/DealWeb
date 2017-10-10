package cn.com.eju.deal.common.file.dto;

import java.io.Serializable;
import java.util.Date;

/**   
* 模块文件与文件code 关系表 Dto
* @author (li_xiaodong)
* @date 2016年6月29日 下午9:20:37
*/
public class FileRecordDto implements Serializable
{
    /**
    * 
    */ 
    private static final long serialVersionUID = -6583518697361468344L;

    private Integer id;
    
    private String fileNo;
    
    private String fileName;
    
    private String relateId;
    
    private String fileTypeCode;
    
    private String remark;
    
    private Date dateCreate;
    
    private Integer userCreate;
    
    private Boolean delFlag;
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getFileNo()
    {
        return fileNo;
    }
    
    public void setFileNo(String fileNo)
    {
        this.fileNo = fileNo == null ? null : fileNo.trim();
    }
    
    public String getFileName()
    {
        return fileName;
    }
    
    public void setFileName(String fileName)
    {
        this.fileName = fileName == null ? null : fileName.trim();
    }
    
    public String getRelateId()
    {
        return relateId;
    }
    
    public void setRelateId(String relateId)
    {
        this.relateId = relateId;
    }
    
    public String getFileTypeCode()
    {
        return fileTypeCode;
    }
    
    public void setFileTypeCode(String fileTypeCode)
    {
        this.fileTypeCode = fileTypeCode == null ? null : fileTypeCode.trim();
    }
    
    public String getRemark()
    {
        return remark;
    }
    
    public void setRemark(String remark)
    {
        this.remark = remark == null ? null : remark.trim();
    }
    
    public Date getDateCreate()
    {
        return dateCreate;
    }
    
    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }
    
    public Integer getUserCreate()
    {
        return userCreate;
    }
    
    public void setUserCreate(Integer userCreate)
    {
        this.userCreate = userCreate;
    }
    
    public Boolean getDelFlag()
    {
        return delFlag;
    }
    
    public void setDelFlag(Boolean delFlag)
    {
        this.delFlag = delFlag;
    }
}