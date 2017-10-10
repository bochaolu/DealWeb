package cn.com.eju.deal.common.util;

public class DatePattern {
	
	public static final String[] ALL = new String[6];
	public static final String Y_M_D="yyyy-MM-dd";
	public static final String Y_M_D_HM="yyyy-MM-dd HH:mm";
	public static final String Y_M_D_HMS="yyyy-MM-dd HH:mm:ss";
    
	public static final String YMD="yyyyMMdd";
	public static final String YMDHM="yyyyMMdd HH:mm";
	public static final String YMDHMS="yyyyMMdd HH:mm:ss";
	public static final String YYYY = "yyyy";
    
//    String ="yyyy/MM/dd";
//    String ="yyyy/MM/dd HH:mm";
//    String ="yyyy/MM/dd HH:mm:ss";
	
	static{
		ALL[0]=Y_M_D;
		ALL[1]=Y_M_D_HM;
		ALL[2]=Y_M_D_HMS;
		ALL[3]=YMD;
		ALL[4]=YMDHM;
		ALL[5]=YMDHMS;
	}
}
