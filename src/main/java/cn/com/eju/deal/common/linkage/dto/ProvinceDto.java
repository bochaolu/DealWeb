package cn.com.eju.deal.common.linkage.dto;

import java.io.Serializable;

/**   
* 省份Model层
* @author mimi.sun
* @date 2016年10月13日 下午5:08:41
*/
public class ProvinceDto implements Serializable
{
    /**
    * 序列化
    */
    private static final long serialVersionUID = -1895600471430694612L;
    
    /**
    * 省份名称
    */
    private String provinceName;
    
    /**
    * 省份编号
    */
    private String provinceNo;
    
    /** 
    * 获取省份名称
    * @return provinceName 省份名称
    */
    public String getProvinceName()
    {
        return provinceName;
    }
    
    /** 
    * 设置省份名称
    * @param provinceName 省份名称
    */
    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }
    
    /** 
    * 获取省份编号
    * @return provinceNo 省份编号
    */
    public String getProvinceNo()
    {
        return provinceNo;
    }
    
    /** 
    * 设置省份编号
    * @param provinceNo 省份编号
    */
    public void setProvinceNo(String provinceNo)
    {
        this.provinceNo = provinceNo == null ? null : provinceNo.trim();
    }
    
}