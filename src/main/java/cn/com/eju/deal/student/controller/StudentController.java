package cn.com.eju.deal.student.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.UserInfoHolder;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.core.util.WebUtil;
import cn.com.eju.deal.dto.student.StudentDto;
import cn.com.eju.deal.student.service.StudentService;

/**   
* 学生管理Controller层
* @author li_xiaodong
* @date 2016年2月2日 下午9:25:30
*/
@RestController
@RequestMapping(value = "students")
public class StudentController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "studentService")
    private StudentService studentService;
    
    /** 
    * 查询列表-初始化
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list()
    {
        //构建ModelAndView实例，并设置跳转页面
        ModelAndView mv = new ModelAndView("student/studentList");
        
        return mv;
    }
    
    /** 
    * 查询列表-内容
    * @param request
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "q", method = RequestMethod.GET)
    public ModelAndView listCtx(HttpServletRequest request)
    {
        //构建ModelAndView实例，并设置跳转页面
        ModelAndView mv = new ModelAndView("student/studentListCtx");
        
        //获取页面请求参数
        Map<String, Object> reqMap = bindParamToMap(request);
        
        //接口返回数据
        List<?> contentlist = null;
        
        try
        {
            //获取页面显示数据
            ResultData<?> reback = studentService.index(reqMap, getPageInfo(request));
            
            //页面数据
            contentlist = (List<?>)reback.getReturnData();
            
        }
        catch (Exception e)
        {
            logger.error("Student",
                "StudentController",
                "index",
                "input param: reqMap=" + reqMap.toString(),
                UserInfoHolder.getUserId(),
                WebUtil.getIPAddress(request),
                "查询列表内容异常",
                e);
        }
        
        //存放到ModelAndView中
        mv.addObject("contentlist", contentlist);
        
        return mv;
    }
    
    /** 
    * 创建--初始化
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "c", method = RequestMethod.GET)
    public ModelAndView toAdd()
    {
        //构建ModelAndView实例，并设置跳转页面
        ModelAndView mv = new ModelAndView("student/studentAdd");
        
        return mv;
    }
    
    /**
     *  创建
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ReturnView<?, ?> create(HttpServletRequest request)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //请求map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        StudentDto studentDto = new StudentDto();
        
        try
        {
            setDto(reqMap, studentDto);
        }
        catch (Exception e1)
        {
            logger.error("Student",
                "StudentController",
                "create",
                "input param: studentDto=" + reqMap.toString(),
                UserInfoHolder.getUserId(),
                WebUtil.getIPAddress(request),
                "新增异常-组装Dto失败",
                e1);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        try
        {
            ResultData<?> resultData = studentService.create(studentDto);
            rspMap.put(Constant.RETURN_CODE_KEY, resultData.getReturnCode());
            rspMap.put(Constant.RETURN_MSG_KEY, resultData.getReturnMsg());
        }
        catch (Exception e)
        {
            logger.error("Student",
                "StudentController",
                "create",
                "input param: studentDto=" + studentDto.toString(),
                UserInfoHolder.getUserId(),
                WebUtil.getIPAddress(request),
                "新增异常",
                e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * 修改--初始化
    * @param id
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/u/{id}", method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable("id") int id)
    {
        
        //构建ModelAndView实例，并设置跳转页面
        ModelAndView mv = new ModelAndView("student/studentEdit");
        
        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();
        
        try
        {
            resultData = studentService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("Student",
                "StudentController",
                "toEdit",
                "input param: id=" + id,
                UserInfoHolder.getUserId(),
                "",
                "修改异常-初始化查询失败",
                e);
        }
        
        //存放到ModelAndView中
        mv.addObject("studentInfo", resultData.getReturnData());
        
        return mv;
    }
    
    /**
     *  修改 
     *  @param request
     * @throws Exception 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ReturnView<?, ?> update(HttpServletRequest request,
        @RequestParam(value = "isform", required = false) String isform, @PathVariable("id") String id)
    {
        
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        //获取map
        Map<String, Object> reqMap = bindParamToMap(request);
        
        reqMap.remove("_method");
        
        StudentDto studentDto = new StudentDto();
        
        try
        {
            studentDto.setId(Integer.valueOf(id));
            
            setDto(reqMap, studentDto);
        }
        catch (Exception e1)
        {
            logger.error("Student",
                "StudentController",
                "create",
                "input param: studentDto=" + reqMap.toString(),
                UserInfoHolder.getUserId(),
                WebUtil.getIPAddress(request),
                "修改异常-组装Dto失败",
                e1);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        try
        {
            //更新
            studentService.update(studentDto);
        }
        catch (Exception e)
        {
            
            logger.error("Student",
                "StudentController",
                "update",
                "input param: studentDto=" + studentDto.toString(),
                UserInfoHolder.getUserId(),
                WebUtil.getIPAddress(request),
                "修改异常",
                e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
    }
    
    /** 
    * 查看
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable("id") Integer id)
    {
        //构建ModelAndView实例，并设置跳转页面
        ModelAndView mv = new ModelAndView("student/studentDetail");
        
        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();
        
        try
        {
            resultData = studentService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("Student",
                "StudentController",
                "show",
                "input param: id=" + id,
                UserInfoHolder.getUserId(),
                "",
                "查看异常-初始化查询失败",
                e);
        }
        
        //存放到ModelAndView中
        mv.addObject("studentInfo", resultData.getReturnData());
        
        return mv;
    }
    
    /** 
    * 删除
    * @param id
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ReturnView<?, ?> destroy(@PathVariable int id)
    {
        //返回map
        Map<String, Object> rspMap = new HashMap<String, Object>();
        
        try
        {
            //获取当前操作人usreId
            int updateId = UserInfoHolder.getUserId();
            
            //删除
            studentService.delete(id, updateId);
        }
        catch (Exception e)
        {
            logger.error("Student",
                "StudentController",
                "destroy",
                "input param: id=" + id,
                UserInfoHolder.getUserId(),
                "",
                "删除异常",
                e);
            
            rspMap.put(Constant.RETURN_CODE_KEY, ReturnCode.FAILURE);
            return getOperateJSONView(rspMap);
        }
        
        return getOperateJSONView(rspMap);
        
    }
    
    /** 
    * 弹出框--测试初始化
    * @param id
    * @param mop
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/t/{id}", method = RequestMethod.GET)
    public ModelAndView toView(@PathVariable("id") int id)
    {
        
        //构建ModelAndView实例，并设置跳转页面
        ModelAndView mv = new ModelAndView("student/studentPopup");
        
        //返回map
        ResultData<?> resultData = new ResultData<StudentDto>();
        
        try
        {
            resultData = studentService.getById(id);
        }
        catch (Exception e)
        {
            logger.error("Student",
                "StudentController",
                "toEdit",
                "input param: id=" + id,
                UserInfoHolder.getUserId(),
                "",
                "修改异常-初始化查询失败",
                e);
        }
        
        //存放到ModelAndView中
        mv.addObject("studentInfo", resultData.getReturnData());
        
        return mv;
    }
    
    /** 
    * 转换 map--Dto
    * @param reqMap
    * @param studentDto
    */
    private void setDto(Map<String, Object> reqMap, StudentDto studentDto)
        throws Exception
    {
        String stuName = (String)reqMap.get("stuName");
        studentDto.setStuName(stuName);
        
        String stuNoStr = (String)reqMap.get("stuNo");
        if (StringUtil.isNotEmpty(stuNoStr))
        {
            studentDto.setStuNo(Integer.valueOf(stuNoStr));
        }
        
        String stuAgeStr = (String)reqMap.get("stuAge");
        if (StringUtil.isNotEmpty(stuAgeStr))
        {
            studentDto.setStuAge(Integer.valueOf(stuAgeStr));
        }
        
    }
    
}
