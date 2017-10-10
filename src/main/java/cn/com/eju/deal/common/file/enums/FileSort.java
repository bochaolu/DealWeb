package cn.com.eju.deal.common.file.enums;

/**   
* 模块文件与文件code 关系--文件分类
* @author (li_xiaodong)
* @date 2016年6月28日 上午11:19:32
*/
public enum FileSort
{
    /**
     * 广告管理-内容封面照片
     */
    OP_AD_NR_FM("OP_AD_NR_FM", "广告管理-内容封面照片"),
    
    /**
    * 楼盘字典-物业公司管理LOGO
    */
    OP_ED_ENTITY_PC("OP_ED_ENTITY_PC", "楼盘字典-物业公司管理LOGO"),
    
    /**
    * 楼盘字典-开发商管理LOGO
    */
    OP_ED_ENTITY_D("OP_ED_ENTITY_D", "楼盘字典-开发商管理LOGO"),
    
    /**
    * 楼盘字典-楼盘图片
    */
    OP_ED_PICTURE_EI("OP_ED_PICTURE_EI", "楼盘字典-楼盘图片"),
    
    /**
    * 楼盘字典-户型图
    */
    OP_ED_FRAME_HFP("OP_ED_FRAME_HFP", "楼盘字典-户型图"),
    
    /**
    * 房源运营-房源图片
    */
    OP_HO_HOUSING("OP_HO_HOUSING", "房源运营-房源图片");
    
    /**
     * 枚举Code
     */
    private String code;
    
    /**
     * 枚举内容
     */
    private String name;
    
    private FileSort(String code, String name)
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
