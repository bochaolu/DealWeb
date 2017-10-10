package cn.com.eju.deal.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.StringUtil;

public class DisplayUtil {
   private static Log logger=LogFactory.getLog(DisplayUtil.class);
   private static Map<String, Map<Integer, String>> mpTypeMap=new HashMap<String, Map<Integer, String>>();
   
   static{
	   Map<Integer, String> repairFundMap=new HashMap<Integer, String>();
	   repairFundMap.put(1, "包含在总价内");
	   repairFundMap.put(0, "不包含在总价内");
	   mpTypeMap.put("repairFund", repairFundMap);
	   
	   Map<Integer, String> chnMap=new HashMap<Integer, String>();
	   chnMap.put(1, "一");
	   chnMap.put(2, "二");
	   chnMap.put(3, "三");
	   chnMap.put(4, "四");
	   chnMap.put(5, "五");
	   chnMap.put(6, "六");
	   chnMap.put(7, "七");
	   chnMap.put(8, "八");
	   chnMap.put(9, "九");
	   chnMap.put(10, "十");
	   
	   mpTypeMap.put("chn", chnMap);
	   
	   Map<Integer, String> delStandardMap=new HashMap<Integer, String>();
	   delStandardMap.put(1, "签订房屋交接书");
	   delStandardMap.put(0, "其他");
	   
	   mpTypeMap.put("delStandard", delStandardMap);
   }
	public static String getCheck(Integer sourceValue,Integer compareValue){
		if(sourceValue==null||compareValue==null){
			logger.error("getCheck为空");
			return "";
		}
		if(sourceValue.intValue()==compareValue.intValue()){
			return "checked=\"checked\"";
		}
		return "";
	}
	public static String getIfContainCheck(String sourceValue,Integer compareValue){
		if(sourceValue==null||compareValue==null){
			logger.error("getCheck为空");
			return "";
		}
		if(sourceValue.contains(compareValue.toString())){
			return "checked=\"checked\"";
		}
		return "";
	}
	
	/**
	 * 
	 * @param sourceValue
	 * @param compareValue
	 * @return
	 */
	public static String getDisplayVal(Integer sourceValue,Integer compareValue){
		if(sourceValue==null||compareValue==null){
			logger.error("getDisplayStyle参数为空");
			return "display:none";
		}
		if(sourceValue.intValue()==compareValue.intValue()){
			return "";
		}
		return "display:none";
	}
	
	
	/**
	 * 
	 * @param sourceValue
	 * @param compareValue
	 * @return
	 */
	public static String containsDisplay(String sourceValue,String val){
		if(sourceValue==null||val==null){
			logger.error("containsDisplay参数为空");
			return "display:none";
		}
		if(sourceValue.contains(val)){
			return "";
		}
		return "display:none";
	}
	
	
	public static String getSelected(String sourceValue,String compareValue){
		if(sourceValue==null||compareValue==null){
			logger.error("getSelected为空");
			return "";
		}
		if(sourceValue.equalsIgnoreCase(compareValue)){
			return "selected=\"selected\"";
		}
		return "";
	}
	
	public static String ymd(Date date){
		if(date !=null){
			return DateUtil.fmtDate(date, DatePattern.Y_M_D);
		}
		return null;
	}
	
	public static String year(Date date){
		if(date !=null){
			return DateUtil.fmtDate(date, DatePattern.YYYY);
		}
		return null;
	}
	
	public static String ymdhm(Date date){
		if(date !=null){
			return DateUtil.fmtDate(date, DatePattern.Y_M_D_HM);
		}
		return null;
	}
	public static String ymdhms(Date date){
		if(date !=null){
			return DateUtil.fmtDate(date, DatePattern.Y_M_D_HMS);
		}
		return null;
	}
	
	public static String ymdhmsfs(String date){
	   /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date d = sdf.parse(date);*/
        if(date !=null){
            return DateUtil.fmtDate(DateUtil.getDate(date), DatePattern.Y_M_D_HMS);
        }
        return null;
    }
	
	public static String ymd2(String date){
	        if(date !=null && !"".equals(date)){
	            if (ymdhmsfs(date) != null) {
	            	return date.substring(0, 10);
	            }
	        }
	        return null;
	    }
	
	public static String ymd3(String date){
        if(date !=null && !"".equals(date)){
            if (ymdhmsfs(date) != null) {
            	return date.substring(0, 7);
            }
        }
        return null;
    }
	
	public static String moneyFormate(BigDecimal money){
		if(money==null){
			logger.error("金额格式化参数为空");
			return null;
		}
		if(money.compareTo(BigDecimal.ZERO)==0){
		    return "￥0.00";
		}
		 String moneyStr = "";
		 if(money.compareTo(BigDecimal.ZERO) ==1){
		     if(money.compareTo(BigDecimal.ONE) == -1){
		         moneyStr =  String.valueOf(money.doubleValue());
		     }else{
		         DecimalFormat myformat = new DecimalFormat();
//		       myformat.applyPattern("##,###");
		         myformat.applyPattern("##,##0.00");    
		          moneyStr = myformat.format(money.doubleValue());
		     }
	        }
		 else if (money.compareTo(BigDecimal.ZERO)==-1) {
            Double mon=money.doubleValue();
            if(mon>-1){
                moneyStr =  String.valueOf(mon);
            }
            else{
                DecimalFormat myformat = new DecimalFormat();
                  myformat.applyPattern("##,##0.00");    
                   moneyStr = myformat.format(mon);
            }
        }
		 return "￥"+moneyStr;
	}
	
	public static String moneyFormate2(BigDecimal money){
        if(money==null){
            logger.error("金额格式化参数为空");
            return null;
        }
        if(money.compareTo(BigDecimal.ZERO)==0){
            return "0.00";
        }
        DecimalFormat myformat = new DecimalFormat();
//      myformat.applyPattern("##,###");
        myformat.applyPattern("##,##0.00");
        String moneyStr = myformat.format(money.doubleValue());
        return moneyStr;
    }
	public static String moneyFormate3(BigDecimal money){
	    if(money==null){
	        logger.error("金额格式化参数为空");
	        return null;
	    }
	    if(money.compareTo(BigDecimal.ZERO)==0){
	        return "0";
	    }
	    DecimalFormat myformat = new DecimalFormat();
//      myformat.applyPattern("##,###");
	    myformat.applyPattern("##,###");
	    String moneyStr = myformat.format(money.doubleValue());
	    return moneyStr;
	}
	public static String moneyFormate4(BigDecimal money){
        if(money==null){
            logger.error("金额格式化参数为空");
            return null;
        }
        if(money.compareTo(BigDecimal.ZERO)==0){
            return "￥0.00";
        }
        DecimalFormat myformat = new DecimalFormat();
//      myformat.applyPattern("##,###");
        myformat.applyPattern("##,##0.00");
        String moneyStr = myformat.format(money.doubleValue());
        return "￥"+ moneyStr;
    }
	
	/**
	 * 是否键值映射
	 * @param code
	 * @return
	 */
	public static String mpyn(Integer code){
		if(code==null){
			logger.error("mpyn方法参数为空");
			return null;
		}
		if(code.intValue()==1){
			return "是";
		}
		return "否";
	}
	/**
	 * 有无键值映射
	 * @param code
	 * @return
	 */
	public static String mphn(Integer code){
		if(code==null){
			logger.error("mphn方法参数为空");
			return null;
		}
		
		if(code.intValue()==1){
			return "有";
		}
		return "无";
	}
	/**
	 * 男女键值映射
	 * @param code
	 * @return
	 */
	public static String mpmw(Integer code){
		if(code==null){
			logger.error("mpmw方法参数为空");
			return null;
		}
		if(code.intValue()==1){
			return "男";
		}
		return "女";
	}

	public static String join(List<String> list,String separator){
			if(list==null){
				return null;
			}
		return StringUtils.join(list.toArray(), separator);
	}
	/**
	 *不同类型的键值映射
	 * @param type
	 * @param code
	 * @return
	 */
	public static String mp(String type,Integer code){
		if(code==null){
			logger.error("mp方法参数为空");
			return null;
		}
		Map<Integer, String> map = mpTypeMap.get(type);
		if(map==null){
			return null;
		}
		return map.get(code);
	}
	
	/**
	 *根据当前登录的城市ID获取城区
	 * @return
	 */
//	public static List<District> getCurrentDistrictList(){
//		UserInfo userInfo = UserInfoHolder.get();
//		Integer cityId=null;
//		if(userInfo!=null){
//			cityId=userInfo.getSelectCityId();
//		}else{
//			logger.error("当前登录的城市编号为空"+cityId);
//		}
//		 List<District> disList = houseAPI.getDistrictByCityId(cityId+"").getReturnData();
//		return disList;
//	}
	
	/**
	 * 根据 城区编号获取城区名称
	 * @param districtNo
	 * @return
	 */
//	public static String getDistrictName(String districtNo){
//		//TODO caimingqin
//		if(StringUtil.isEmpty(districtNo)){
//			return null;
//		}
//		District district = houseAPI.getDistrictByDistrictNo(districtNo).getReturnData();
//		if(district !=null){
//			return district.getDistrictName();
//		}
//		return null;
//		
//	}
	
	/**
	 * 获取当前登录人交易中心
	 * @return
	 */
//	public static List<ExchangeCenter>  getExchangeCenterList(){
//		UserInfo userInfo = UserInfoHolder.get();
//		if(userInfo!=null){
//			BIResult<List<ExchangeCenter>> exchangeCenter = exchangeCenterAPI.getExchangeCenterByCityId(userInfo.getSelectCityId());
//			if(exchangeCenter!=null){
//				return exchangeCenter.getReturnData();
//			}
//		}
//		return null;
//	}
	
	
	/**
	 * 根据交易中心ID获取交易中心
	 * @param exchangeCenterId
	 * @return
	 */
//	public static ExchangeCenter  getExchangeCenter(Integer exchangeCenterId ){
//		BIResult<ExchangeCenter> exchangeCenter = exchangeCenterAPI.getExchangeCenterByExchangeCenterId(exchangeCenterId);
//		if(exchangeCenter!=null){
//			return exchangeCenter.getReturnData();
//		}
//		return null;
//	}
	/**
	 * 根据交易中心ID获取交易中心名称
	 * @param exchangeCenterId
	 * @return
	 */
//	public static String  getExchangeCenterName(Integer exchangeCenterId ){
//		ExchangeCenter exchangeCenter = getExchangeCenter(exchangeCenterId);
//		if(exchangeCenter!=null){
//			return exchangeCenter.getExchangeCenterName();
//		}
//		return null;
//	}
	
	/**
	 * 如果值为空，显示默认值
	 * @return
	 */
	public static String or1(String value,String defValue){
		if(StringUtil.isEmpty(value)){
			logger.error("or1方法参数为空");
			return defValue;
		}
		return value;
	}
    /**
     * 获取当天时间
     * @return
     */
	public static String getCurrentYmdHm(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY,0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		return DateUtil.fmtDate(c.getTime(),DatePattern.Y_M_D_HM);
	}
	
	/**
	 * 获取下个月的当天
	 * @return
	 */
	public static String getNextMonthCurrentYmdHm(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY,0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		return DateUtil.fmtDate(c.getTime(),DatePattern.Y_M_D_HM);
	}
	
	/**
	 * 
	 * 英文逗号隔开字符串转数组
	 * @param str
	 * @return
	 */
	public static String[] commaToArray(String str){
	    if(StringUtils.isNotBlank(str)){
	        return str.split(",");
	    }
	    return null;
	}
	/**
	 * 
	 * 判断目标字符串是否包含在源字符串
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean existentStr(String source,String target){
	    if(StringUtils.isBlank(source)){
	        return false;
	    }
	    return source.contains(target);
	}
	/**
	 * 符号替换，如 1,2,3==>>1;2;3
	 * @param source
	 * @param replacedStr
	 * @param newStr
	 * @return
	 */
	public static String replace(String source,String  replacedStr,String newStr){
		
		if(source ==null ){
			return null;
		}
		
		return source.replace(replacedStr, newStr);
	}
	
	
	/**
	 * 根据当前登录人交易中心ID，获取公积金类型
	 * @return
	 */
//	public static List<FundTypeExCenterMapping>   getFundTypeList(){
//		UserInfo userInfo = UserInfoHolder.get();
//		if(userInfo!=null){
//			return loanService.getByExCenterId(userInfo.getExchangeCenterId());
//		}
//		return null;
//	}
	
	/**
	 * 获取公积金类型名称
	 * @param fundTypeId
	 * @return
	 */
//	public static String   getByFundTypeName(Integer fundTypeId){
//		Assert.notNull(fundTypeId);
//	  FundTypeExCenterMapping fundType = loanService.getByFundTypeId(fundTypeId);
//	   if(fundType!=null){
//		   return fundType.getFundTypeName();
//	   }
//		return null;
//	}
	
}
