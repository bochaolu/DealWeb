package cn.com.eju.deal.common.linkage.dto;

import java.io.Serializable;

/**   
* 板块Model层
* @author (li_xiaodong)
* @date 2016年3月30日 下午5:05:36
*/
public class AreaDto implements Serializable
{
    /**
    * 序列化
    */
    private static final long serialVersionUID = -5524596452028551274L;
    
    /**
    * 城市编号
    */
    private String cityNo;
    
    /**
    * 城市名称
    */
    private String cityName;
    
    /**
    * 行政区编号
    */
    private String districtNo;
    
    /**
    * 行政区名称
    */
    private String districtName;
    
    /**
    * 板块名称
    */
    private String areaName;
    
    /**
    * 板块编号
    */
    private String areaNo;
    
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
    * 获取行政区编号
    * @return districtNo 行政区编号
    */
    public String getDistrictNo()
    {
        return districtNo;
    }
    
    /** 
    * 设置行政区编号
    * @param districtNo 行政区编号
    */
    public void setDistrictNo(String districtNo)
    {
        this.districtNo = districtNo == null ? null : districtNo.trim();
    }
    
    /** 
    * 获取行政区名称
    * @return districtName 行政区名称
    */
    public String getDistrictName()
    {
        return districtName;
    }
    
    /** 
    * 设置行政区名称
    * @param districtName 行政区名称
    */
    public void setDistrictName(String districtName)
    {
        this.districtName = districtName == null ? null : districtName.trim();
    }
    
    /** 
    * 获取板块名称
    * @return
    */
    public String getAreaName()
    {
        return areaName;
    }
    
    /** 
    * 设置板块名称
    * @param areaName 板块名称
    */
    public void setAreaName(String areaName)
    {
        this.areaName = areaName == null ? null : areaName.trim();
    }
    
    /** 
    * 获取板块编号
    * @return areaNo 板块编号
    */
    public String getAreaNo()
    {
        return areaNo;
    }
    
    /** 
    * 设置板块编号
    * @param areaNo 板块编号
    */
    public void setAreaNo(String areaNo)
    {
        this.areaNo = areaNo == null ? null : areaNo.trim();
    }
    
}