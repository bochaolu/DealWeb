package cn.com.eju.deal.common.linkage.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.linkage.dto.DistrictDto;
import cn.com.eju.deal.core.support.ResultData;

/**   
* 行政区Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("linkageDistrictService")
public class LinkageDistrictService extends BaseService<DistrictDto>
{
    /** 
     * (根据相对路径生成绝对路径) 
     * @param relativeUrl
     * @return
     * @throws Exception     
     */
    public String absoluteUrl(String relativeUrl)
        throws Exception
    {
        //接口绝对地址
        String absoluteUrl = SystemParam.getWebConfigValue("RestServer") + relativeUrl;
        
        return absoluteUrl;
    }
    
    /** 
    * 查询
    * @param id
    * @return
    * @throws Exception
    */
    public ResultData<?> getDistrictListByCityNo(String cityNo)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "linkages/district/{cityNo}";
        
        //调用 接口
        String url = absoluteUrl(relativeUrl);
        
        ResultData<?> backResult = get(url, cityNo);
        
        return backResult;
    }
    
    /** 
    * 获取行政区byDistrictNo
    * @param id
    * @return
    * @throws Exception
    */
    public ResultData<?> getDistrictByDistrictNo(String districtNo)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "linkages/district/q/{districtNo}";
        
        //调用 接口
        String url = absoluteUrl(relativeUrl);
        
        ResultData<?> backResult = get(url, districtNo);
        
        return backResult;
    }
    
}
