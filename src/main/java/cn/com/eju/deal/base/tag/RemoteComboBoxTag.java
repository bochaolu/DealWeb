package cn.com.eju.deal.base.tag;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.com.eju.deal.core.util.StringUtil;


/**
 * 
 * 类描述：下拉选择框标签
 * 
 * @author:  guowei
 * @date： 日期：2015-10-21 时间：上午10:17:45
 * @version 1.0
 */
@SuppressWarnings("serial")
public class RemoteComboBoxTag extends TagSupport {
    protected String id;// ID
    protected String text;// 显示文本
    protected String url;//远程数据
    protected String name;//控件名称
    protected Integer width;//宽度
    protected Integer listWidth;//下拉框宽度
    protected Integer listHeight;//下拉框高度
    protected boolean multiple=false;//
    protected boolean editable;//定义是否可以直接到文本域中键入文本
    private String changeEvent;// change事件
    public int doStartTag() throws JspTagException {
        return EVAL_PAGE;
    }
    public int doEndTag() throws JspTagException {
        try {
            JspWriter out = this.pageContext.getOut();
            out.print(end().toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
    public StringBuffer end() {
        StringBuffer sb = new StringBuffer();
        String loder= this.name+"_loader";
        
        sb.append("<input class=\"easyui-combobox\" "
                +"multiple=\""+multiple+"\" panelHeight=\"auto\" name=\""+name+"\" id=\""+name+"\" "
                +" data-options=\""
                +" loader: "+loder+","
                +"mode: \'remote\',"
                +"valueField: \'id\',"
                +"textField: 'name'");
        if(width!=null&&width>0){
            sb.append(" style=\"width:" + width.toString()  + "px\"");
        }
        if (!StringUtil.isEmpty(this.changeEvent)) {
            sb.append(" onchange=\"" + changeEvent + "()" + "\"");
        }       
        sb.append("\">");
        
        sb.append("<script type=\"text/javascript\">"
                + " var "+loder+" = function(param,success,error){"
                + "var q = param.q || \'\';"
                + "if (q.length <= 2){return false}"
                + "$.ajax({"
                + "    url: \'"+url+"\',"
                + "    dataType: \'jsonp\',"
                + "    data: {"
                + "       q: q"
                + "   },"
                + "   success: function(data){"
                + "       var items = $.map(data, function(item,index){"
                + "           return {"
                + "                id: index,"
                + "               name: item"
                + "           };"
                + "       });"
                + "       success(items);"
                + "   },"
                + "   error: function(){"
                + "       error.apply(this, arguments);"
                + "   }"
                + "});"
                + "}"
                +"</script>");
        return sb;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getChangeEvent() {
        return changeEvent;
    }

    public void setChangeEvent(String changeEvent) {
        this.changeEvent = changeEvent;
    }
}
