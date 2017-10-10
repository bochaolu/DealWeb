package cn.com.eju.deal.dto.user;

import java.util.Date;

public class ExchangeCenter {
	private Integer exchangeCenterId;
	
	private String exchangeCenterCode;
	
	private String exchangeCenterName;
	
	private Integer cityId;
	
	private Date dateCreate;

    private Integer userIdCreate;

    private String delFlag;

    private String address;
    /**
     * 经度
     */
    private Float lng;
    
    /**
     * 纬度
     */
    private Float lat;
	/**
	 * @return the lng
	 */
	public Float getLng() {
		return lng;
	}

	/**
	 * @param lng the lng to set
	 */
	public void setLng(Float lng) {
		this.lng = lng;
	}

	/**
	 * @return the lat
	 */
	public Float getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Integer getExchangeCenterId() {
		return exchangeCenterId;
	}

	public void setExchangeCenterId(Integer exchangeCenterId) {
		this.exchangeCenterId = exchangeCenterId;
	}

	public String getExchangeCenterCode() {
		return exchangeCenterCode;
	}

	public void setExchangeCenterCode(String exchangeCenterCode) {
		this.exchangeCenterCode = exchangeCenterCode;
	}

	public String getExchangeCenterName() {
		return exchangeCenterName;
	}

	public void setExchangeCenterName(String exchangeCenterName) {
		this.exchangeCenterName = exchangeCenterName;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Integer getUserIdCreate() {
		return userIdCreate;
	}

	public void setUserIdCreate(Integer userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    } 
 }
