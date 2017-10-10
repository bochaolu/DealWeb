package cn.com.eju.deal.common.file.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.model.PageInfo;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.file.dto.FileRecordDto;
import cn.com.eju.deal.core.support.ResultData;

/**   
* 模块文件与文件code 关系--Service
* @author (li_xiaodong)
* @date 2016年2月2日 下午9:30:27
*/
@Service("fileRecordService")
public class FileRecordService extends BaseService<FileRecordDto>
{
    /** 
    * @Description: (根据相对路径生成绝对路径) 
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
    public ResultData<?> getById(int id)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "fileRecord/{id}";
        
        //调用 接口
        String url = absoluteUrl(relativeUrl);
        
        ResultData<?> backResult = get(url, id);
        
        return backResult;
    }
    
    /** 
     * 查询-根据relateId
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getByRelateId(String relateId)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "fileRecord/relateId/{relateId}";
        
        //调用 接口
        String url = absoluteUrl(relativeUrl);
        
        ResultData<?> backResult = get(url, relateId);
        
        return backResult;
    }
    
    /** 
    * 查询-list
    * @param reqMap
    * @return
    * @throws Exception
    */
    public ResultData<?> index(Map<String, Object> reqMap, PageInfo pageInfo)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "fileRecord/q/{param}";
        
        //调用 接口
        String url = absoluteUrl(relativeUrl);
        
        ResultData<?> reback = get(url, reqMap, pageInfo);
        
        return reback;
        
    }
    
    /** 
    * 创建
    * @param studentDto
    * @return
    * @throws Exception
    */
    public ResultData<?> create(FileRecordDto dto)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "fileRecord";
        
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
    public void update(FileRecordDto dto)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "fileRecord";
        
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
    public void delete(int id, int updateId)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "fileRecord/{id}/{updateId}";
        
        //调用 接口
        String url = absoluteUrl(relativeUrl);
        
        delete(url, id, updateId);
        
    }
    
    /** 
     * 删除-根据fileNo
     * @param id
     * @param updateId
     * @return
     * @throws Exception
     */
    public void deleteByFileNo(String fileNo, int operateId)
        throws Exception
    {
        
        //接口 相对url
        String relativeUrl = "fileRecord/fileNo/{fileNo}/{operateId}";
        
        //调用 接口
        String url = absoluteUrl(relativeUrl);
        
        delete(url, fileNo, operateId);
        
    }
    
}
