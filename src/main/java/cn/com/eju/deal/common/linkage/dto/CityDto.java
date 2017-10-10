package cn.com.eju.deal.common.linkage.dto;

import java.io.Serializable;

/**   
* 城市Model层
* @author mimi.sun
* @date 2016年10月13日 下午5:09:13
*/
public class CityDto implements Serializable
{
    /**
    * 序列化
    */
    private static final long serialVersionUID = -3326664045303196856L;
    
    /**
    * 省份名称
    */
    private String provinceName;
    
    /**
    * 省份编号
    */
    private String provinceNo;
    
    /**
    * 城市名称
    */
    private String cityName;
    
    /**
    * 城市编号
    */
    private String cityNo;
    
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
    
    /** 
    * 获取城市名称
    * @return cityName 城市名称
    */
    public String getCityName()
    {
        return cityName;
    }
    
    /** 
    * 设置城市名称
    * @param cityName 城市名称
    */
    public void setCityName(String cityName)
    {
        this.cityName = cityName == null ? null : cityName.trim();
    }
    
    /** 
    * 获取城市编号
    * @return cityNo 城市编号
    */
    public String getCityNo()
    {
        return cityNo;
    }
    
    /** 
    * 设置城市编号
    * @param cityNo 城市编号
    */
    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo == null ? null : cityNo.trim();
    }
    
}