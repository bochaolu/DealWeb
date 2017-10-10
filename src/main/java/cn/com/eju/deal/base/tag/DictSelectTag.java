package cn.com.eju.deal.base.tag;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.dom4j.DocumentException;
//import org.springframework.beans.factory.annotation.Autowired;

import cn.com.eju.deal.base.dto.code.CommonCodeDto;
//import cn.com.eju.deal.base.lookup.model.LookUpInfo;
//import cn.com.eju.deal.base.lookup.service.ILookUpService;
//import cn.com.eju.deal.base.support.BeanFactory;
//import cn.com.eju.deal.base.util.LookUpForUtils;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.util.StringUtil;

/**   
* 选择下拉框（暂时只支持从字典表中获取）
* @author (li_xiaodong)
* @date 2016年3月30日 上午9:49:27
*/
public class DictSelectTag extends TagSupport
{
    
    private static final long serialVersionUID = 1;
    
    private String field; // 选择表单的Name EAMPLE:<select name="selectName" id = ""
                          // />
    
    private String id; // 选择表单ID EAMPLE:<select name="selectName" id = "" />
    
    private String defaultVal; // 默认值
    
    private boolean hasLabel = true; // 是否显示label
    
    private String type;// 控件类型select|radio|checkbox
    
    private String xmlkey;//xml文件设定的key值
    
    private String extendParams;// 扩展参数
    
    private String nullOption;
    
    private String changeEvent;// change事件
    
    private boolean multiple = false;
    
    private boolean disable = false;//是否禁用下拉 false:不禁用 true:禁用
    
    private String dbparam;
    
    private String txHeight;
    
    private String txWidth;
    
    private String classvalue;
    
    private boolean required = false;
    
    private boolean editable = false;
    
    private String panelHeight;
    
    public boolean isDisable()
    {
        return disable;
    }
    
    public void setDisable(boolean disable)
    {
        this.disable = disable;
    }
    
    //    @Autowired
    //    private static ILookUpService lookUpListService;
    
    public int doStartTag()
        throws JspTagException
    {
        return EVAL_PAGE;
    }
    
    public int doEndTag()
        throws JspTagException
    {
        try
        {
            JspWriter out = this.pageContext.getOut();
            out.print(end().toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
    
    public StringBuffer end()
    {
        StringBuffer sb = new StringBuffer();
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try
        {
            list = queryDic(xmlkey);
        }
        catch (DocumentException e)
        {
            
        }
        catch (URISyntaxException e)
        {
            
        }
        if ("radio".equals(type))
        {
            for (Map<String, Object> map : list)
            {
                radio(map.get("text").toString(), map.get("field").toString(), sb);
            }
        }
        else if ("checkbox".equals(type))
        {
            for (Map<String, Object> map : list)
            {
                checkbox(map.get("text").toString(), map.get("field").toString(), sb);
            }
        }
        else if ("text".equals(type))
        {
            for (Map<String, Object> map : list)
            {
                text(map.get("text").toString(), map.get("field").toString(), sb);
            }
        }
        else
        {
            if (disable)
            {
                sb.append("<select name=\"" + field + "\" disabled='disabled' ");
            }
            else
            {
                sb.append("<select name=\"" + field + "\" ");
            }
            if (StringUtil.isNotEmpty(this.classvalue))
            {
                sb.append(" class=\"form-control w300").append(this.classvalue).append("\"");
            }
            else
            {
                sb.append(" class=\"form-control w300\"");
            }
            
            if (StringUtil.isNotEmpty(this.txHeight) || StringUtil.isNotEmpty(this.txWidth))
            {
                sb.append(" style= \"");
                if (StringUtil.isNotEmpty(this.txHeight))
                {
                    sb.append(" Height:" + txHeight + ";");
                }
                if (StringUtil.isNotEmpty(this.txWidth))
                {
                    sb.append(" Width:" + txWidth + ";");
                }
                sb.append(" \"");
            }
            
            /*	// 增加扩展属性
            	if (!StringUtil.isEmpty(this.extendParams)) {
            	    Gson gson = new Gson();
            	    Map<String, String> mp = gson.fromJson(extendParams, Map.class);
            		for (Map.Entry<String, String> entry : mp.entrySet()) {
            			sb.append(entry.getKey() + "=\"" + entry.getValue() + "\"");
            		}
            	}*/
            if (multiple)
            {
                sb.append(" multiple=\"" + "true" + "\"");
            }
            if (StringUtil.isEmpty(panelHeight))
            {
                sb.append(" panelHeight=\"auto \" ");
            }
            else
            {
                sb.append(" panelHeight=\"" + panelHeight + "\" ");
            }
            String idvalue = "";
            if (!StringUtil.isEmpty(this.id))
            {
                sb.append(" id=\"" + id + "\"");
                idvalue = id;
                
            }
            else
            {
                sb.append(" id=\"" + field + "\"");
                idvalue = field;
            }
            
            if (required)
            {
                sb.append(" notnull=\"true\" ");
            }
            if (!editable)
            {
                sb.append(" editable=\"false\" ");
            }
            else
            {
                sb.append(" editable=\"true\" ");
            }
            
            if (!StringUtil.isEmpty(this.changeEvent))
            {
                sb.append(" onchange=\"" + changeEvent + "()" + "\"");
            }
            sb.append(">");
            if (StringUtil.isNotEmpty(nullOption))
            {
                sb.append(" <option value=\"\">");
                sb.append(nullOption);
                sb.append(" </option>");
            }
            else
            {
                if (nullOption != null && nullOption.length() > 0)
                {
                    sb.append(" <option value=\"\">");
                    sb.append(nullOption);
                    sb.append(" </option>");
                }
            }
            for (Map<String, Object> map : list)
            {
                select(map.get("text").toString(), map.get("field").toString(), sb);
            }
            sb.append("</select>");
        }
        return sb;
    }
    
    /**
     * 文本框方法
     * 
     * @param name
     * @param code
     * @param sb
     */
    private void text(String name, String code, StringBuffer sb)
    {
        if (code.equals(this.defaultVal))
        {
            sb.append("<input class='inputxt' name='" + field + "'" + " id='" + id + "' value='" + name
                + "' readOnly = 'readOnly' />");
        }
        else
        {
        }
    }
    
    /**
     * 单选框方法
     * 
     * @作者：Alexander
     * 
     * @param name
     * @param code
     * @param sb
     */
    private void radio(String name, String code, StringBuffer sb)
    {
        if (code.equals(this.defaultVal))
        {
            sb.append("<input type=\"radio\" name=\"" + field + "\" checked=\"checked\" value=\"" + code + "\"");
            if (!StringUtil.isEmpty(this.id))
            {
                sb.append(" id=\"" + id + "\"");
            }
            if (StringUtil.isNotEmpty(this.classvalue))
            {
                sb.append(" class=\"").append(this.classvalue).append("\"");
            }
            sb.append(" />");
        }
        else
        {
            sb.append("<input type=\"radio\" name=\"" + field + "\" value=\"" + code + "\"");
            if (!StringUtil.isEmpty(this.id))
            {
                sb.append(" id=\"" + id + "\"");
            }
            if (StringUtil.isNotEmpty(this.classvalue))
            {
                sb.append(" class=\"").append(this.classvalue).append("\"");
            }
            sb.append(" />");
        }
        
        sb.append("<label  class=\"label pdr18\">" + name + "</label>");
    }
    
    /**
     * 复选框方法
     * 
     * @作者：Alexander
     * 
     * @param name
     * @param code
     * @param sb
     */
    private void checkbox(String name, String code, StringBuffer sb)
    {
        String[] values = this.defaultVal.split(",");
        Boolean checked = false;
        for (int i = 0; i < values.length; i++)
        {
            String value = values[i];
            if (code.equals(value))
            {
                checked = true;
                break;
            }
            checked = false;
        }
        if (checked)
        {
            sb.append("<input type=\"checkbox\" name=\"" + field + "\" checked=\"checked\" value=\"" + code + "\"");
            if (!StringUtil.isEmpty(this.id))
            {
                sb.append(" id=\"" + id + "\"");
            }
            sb.append(" />");
        }
        else
        {
            sb.append("<input type=\"checkbox\" name=\"" + field + "\" value=\"" + code + "\"");
            if (!StringUtil.isEmpty(this.id))
            {
                sb.append(" id=\"" + id + "\"");
            }
            sb.append(" />");
        }
        sb.append(name);
    }
    
    /**
     * 选择框方法
     * 
     * @作者：Alexander
     * 
     * @param name
     * @param code
     * @param sb
     */
    private void select(String name, String code, StringBuffer sb)
    {
        if (code.equals(this.defaultVal))
        {
            sb.append(" <option value=\"" + code + "\" selected=\"selected\">");
        }
        else
        {
            sb.append(" <option value=\"" + code + "\">");
        }
        sb.append(name);
        sb.append(" </option>");
    }
    
    /**
     * 查询自定义数据字典
     * @throws DocumentException 
     * 
     * @作者：Alexander
     */
    private List<Map<String, Object>> queryDic(String key)
        throws DocumentException, URISyntaxException
    {
        
        //读取xml配置信息
        //LookUpForUtils d = LookUpForUtils.getInstance();
        
        //根据jsp设定的key值找到执行的sql文
        // String sql = d.loadLookfor(key, dbparam);
        
        //格式转换
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if ("LOOKUP_Dic".endsWith(key))
        {
            Map<String, Object> paramMap = StringUtil.splitToMap(dbparam);
            list = getMemoryDate((String)paramMap.get("P1"));
        }
        //        if (list == null || list.size() == 0)
        //        {
        //            //获取共通的service
        //            lookUpListService = (ILookUpService)BeanFactory.getBean("lookUpService");
        //            //参数Map
        //            Map<String, Object> map = new HashMap<String, Object>();
        //            map.put("paramSQL", sql);
        //            //调用查询方法，获取数据库的值
        //            List<LookUpInfo> dblist = lookUpListService.queryList(map);
        //            if (dblist != null && dblist.size() > 0)
        //            {
        //                LookUpInfo o = null;
        //                Map<String, Object> tempmap = null;
        //                for (int i = 0; i < dblist.size(); i++)
        //                {
        //                    o = new LookUpInfo();
        //                    tempmap = new HashMap<String, Object>();
        //                    o = dblist.get(i);
        //                    tempmap.put("field", o.getField());
        //                    tempmap.put("text", o.getText());
        //                    list.add(tempmap);
        //                }
        //            }
        //        }
        return list;
    }
    
    private List<Map<String, Object>> getMemoryDate(String typeCode)
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        List<CommonCodeDto> dataList = SystemParam.getCodeListByKey(typeCode);
        if (dataList != null && dataList.size() > 0)
        {
            Map<String, Object> tempmap = null;
            for (CommonCodeDto o : dataList)
            {
                tempmap = new HashMap<String, Object>();
                
                tempmap.put("field", o.getDicCode());
                tempmap.put("text", o.getDicValue());
                list.add(tempmap);
            }
        }
        return list;
        
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getDefaultVal()
    {
        return defaultVal;
    }
    
    public void setDefaultVal(String defaultVal)
    {
        this.defaultVal = defaultVal;
    }
    
    public String getField()
    {
        return field;
    }
    
    public void setField(String field)
    {
        this.field = field;
    }
    
    public boolean isHasLabel()
    {
        return hasLabel;
    }
    
    public void setHasLabel(boolean hasLabel)
    {
        this.hasLabel = hasLabel;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getExtendParams()
    {
        return extendParams;
    }
    
    public void setExtendParams(String extendParams)
    {
        this.extendParams = extendParams;
    }
    
    public String getNullOption()
    {
        return nullOption;
    }
    
    public void setNullOption(String nullOption)
    {
        this.nullOption = nullOption;
    }
    
    public String getChangeEvent()
    {
        return changeEvent;
    }
    
    public void setChangeEvent(String changeEvent)
    {
        this.changeEvent = changeEvent;
    }
    
    public String getXmlkey()
    {
        return xmlkey;
    }
    
    public void setXmlkey(String xmlkey)
    {
        this.xmlkey = xmlkey;
    }
    
    public boolean isMultiple()
    {
        return multiple;
    }
    
    public void setMultiple(boolean multiple)
    {
        this.multiple = multiple;
    }
    
    public String getDbparam()
    {
        return dbparam;
    }
    
    public void setDbparam(String dbparam)
    {
        this.dbparam = dbparam;
    }
    
    /**
     * @return the txHeight
     */
    public String getTxHeight()
    {
        return txHeight;
    }
    
    /**
     * @param txHeight the txHeight to set
     */
    public void setTxHeight(String txHeight)
    {
        this.txHeight = txHeight;
    }
    
    public String getTxWidth()
    {
        return txWidth;
    }
    
    public void setTxWidth(String txWidth)
    {
        this.txWidth = txWidth;
    }
    
    public boolean isRequired()
    {
        return required;
    }
    
    public void setRequired(boolean required)
    {
        this.required = required;
    }
    
    public String getClassvalue()
    {
        return classvalue;
    }
    
    public void setClassvalue(String classvalue)
    {
        this.classvalue = classvalue;
    }
    
    public boolean isEditable()
    {
        return editable;
    }
    
    public void setEditable(boolean editable)
    {
        this.editable = editable;
    }
    
    public String getPanelHeight()
    {
        return panelHeight;
    }
    
    public void setPanelHeight(String panelHeight)
    {
        this.panelHeight = panelHeight;
    }
    
}
