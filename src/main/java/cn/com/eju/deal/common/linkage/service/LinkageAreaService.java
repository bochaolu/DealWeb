package cn.com.eju.deal.common.linkage.service;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.linkage.dto.AreaDto;
import cn.com.eju.deal.core.support.ResultData;

/**   
* 板块Service层
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("linkageAreaService")
public class LinkageAreaService extends BaseService<AreaDto>
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
    public ResultData<?> getAreaListByDistrictNo(String districtNo)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "/linkages/area/{districtNo}";
        
        //调用 接口
        String url = absoluteUrl(relativeUrl);
        
        ResultData<?> backResult = get(url, districtNo);
        
        return backResult;
    }
    
    /** 
    * 查询
    * @param id
    * @return
    * @throws Exception
    */
    public ResultData<?> getAreaByDistrictNo(String areaNo)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "/linkages/area/q/{areaNo}";
        
        //调用 接口
        String url = absoluteUrl(relativeUrl);
        
        ResultData<?> backResult = get(url, areaNo);
        
        return backResult;
    }
    
    /** 
    * 创建
    * @param studentDto
    * @return
    * @throws Exception
    */
    public ResultData<?> create(AreaDto dto)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "/linkages/area";
        
        //调用 接口
        String url = absoluteUrl(relativeUrl);
        
        ResultData<?> backResult = post(url, dto);
        
        return backResult;
    }
    
    /** 
    * 更新
    * @param studentDto
    * @return
    * @throws Exception
    */
    public void update(AreaDto dto)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "/linkages/area";
        
        //调用 接口
        String url = absoluteUrl(relativeUrl);
        
        put(url, dto);
        
    }
    
    /** 
    * 删除
    * @param id
    * @param updateId
    * @return
    * @throws Exception
    */
    public void delete(String areaNo)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "/linkages/area/{areaNo}";
        
        //调用 接口
        String url = absoluteUrl(relativeUrl);
        
        delete(url, areaNo);
        
    }
    
}
