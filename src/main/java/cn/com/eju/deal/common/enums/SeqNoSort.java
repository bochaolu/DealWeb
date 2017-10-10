package cn.com.eju.deal.common.enums;

/**   
* 生成自增长编号--分类
* @author (li_xiaodong)
* @date 2016年6月28日 上午11:19:32
*/
public enum SeqNoSort
{
    /**
     * 楼盘字典-物业公司编号
     */
    TYPE_PROPERTYCOMPANY("TYPE_PROPERTYCOMPANY", "楼盘字典-物业公司编号"),
    
    /**
    * 楼盘字典-开发商管理LOGO
    */
    TYPE_DEVELOPER("TYPE_DEVELOPER", "楼盘字典-开发商编号"),
    
    /**
    * 楼盘字典-楼盘编号
    */
    TYPE_ESTATE("TYPE_ESTATE", "楼盘字典-楼盘编号"),
    
    /**
    * 楼盘字典-户型编号
    */
    TYPE_FRAME("TYPE_FRAME", "楼盘字典-户型编号"),
    
    /**
    * 楼盘字典-楼幢编号
    */
    TYPE_BUILDING("TYPE_BUILDING", "楼盘字典-楼幢编号"),
    
    /**
     * 楼盘字典-楼盘图片编号
     */
    TYPE_ESTATEIMAGE("TYPE_ESTATEIMAGE", "楼盘字典-楼盘编号"),
    
    /**
     * 楼盘字典-单元编号
     */
    TYPE_UNIT("TYPE_UNIT", "楼盘字典-单元编号"),
    
    /**
     * 楼盘字典-楼层编号
     */
    TYPE_FLOOR("TYPE_FLOOR", "楼盘字典-楼层编号"),
    
    /**
     * 楼盘字典-房屋编号
     */
    TYPE_HOUSE("TYPE_HOUSE", "楼盘字典-房屋编号"),
    
    /**
     * 楼盘字典-规则编号
     */
    TYPE_RULE("TYPE_RULE", "楼盘字典-规则编号"),
    
    /**
     * 房源运营-房源编号
     */
    TYPE_HO_HOUSING("TYPE_HO_HOUSING", "房源运营-房源编号"),
    
    /**
     * 房源运营-推送单编号
     */
    TYPE_HO_PUSH("TYPE_HO_PUSH", "房源运营-推送单编号");
    
    /**
     * 枚举Code
     */
    private String code;
    
    /**
     * 枚举内容
     */
    private String name;
    
    private SeqNoSort(String code, String name)
    {
        this.code = code;
        this.name = name;
        
    }
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
}
