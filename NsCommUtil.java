package com.nsmall.backend.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Class Name : NsCommUtil.java
 * @Description : 사용 빈도가 높은 함수들을 이 유틸리티 클래스안에 메소드로 구현하고 서로 공유하기 위함이며, 
 * 		  Contents에 적힌 번호로 관련 함수를 검색하여 사용.
 * @Contents
 * - NS001 : Null, 공백처리
 * - NS002 : 데이터 파싱
 * 
 * @see Copyright ⓒ 2018. NsHomeShopping Corp.
 */
public class NsCommUtil {

	/* NS001 */
	
	/**
	 * String 값이 null 이거나 공백이면 0으로 치환
	 * @param value
	 * @return 
	 */
	 public static String nullToZero(String value){	 
		 
		  if(value == null || value.equals("")) {
			  value   =   "0";
		  }
		  	  return value;
	 }
	 
	 /**
	  * 문자열 좌측의 공백을 제거하는 메소드
	  * @param  str 대상 문자열
	  * @return 
	  */
	 public static String ltrim(String str){ 
		  int len = str.length();
		  int idx = 0;
	
		  while ((idx < len) && (str.charAt(idx) <= ' ')) {
			  idx++;
		  }
		  return str.substring(idx, len);
	 }

	 /**
	  * 문자열 우측의 공백을 제거하는 메소드
	  * @param  str 대상 문자열
	  * @return 
	  */
	 public static String rtrim(String str){
		 int len = str.length();

		 while ((0 < len) && (str.charAt(len-1) <= ' ')) {
			 len--;
		 }
		 return str.substring(0, len);
	 }

	 /**
	  * 숫자 0이 넘어오면 ""로 대치
	  * @param  int 대상 숫자
	  * @return 
	  */
	 public static String isOneNull(int num){
		  if (num == 0) return "";
		  else return Integer.toString(num);
	 }
	 
	 /**
	  * str이 null 이거나 "", "    " 일경우 return true
	  * @param str
	  * @return
	  */
	 public static boolean isNull(String str){
		 return (str == null || (str.trim().length()) == 0 );
		 }	
	 
		 public static boolean isNull(Object obj) {			 
			 String str = null;
			 
			 if( obj instanceof String ) {
				 str = (String)obj;
			 } else {
				 return true;
			 }
				 return isNull(str);
	 }

	 /**
	  * null이 아닐때 isNull이 true이면 false, false이면 true
	  * @param str
	  * @return
	  */
	 public static boolean isNotNull(String str){
	  
		 if( isNull(str) ) {
			 return false;
		 } else {
			 return true;
		 }
	 }

	 /**
	  * null 체크
	  * @param obj
	  * @return
	  */
	 public static boolean isNotNull(Object obj){
		 String str = null;
		 
		if( obj instanceof String ) {
			str = (String)obj;
		} else {
			return false;
		}
			return isNotNull(str);
	 }

	 /**
	  * 파라미터가 null 이거나 공백이 있을경우 "" 로 return
	  * @param value
	  * @return
	  */
	 public static String replaceNull(String value){
		 return replaceNull(value, "");
	 }

	 /**
	  * Object를 받아서 String 형이 아니거나 NULL이면 ""를 return
	  * String 형이면 형 변환해서 넘겨준다.
	  * @param value
	  * @return
	  */
	 public static String replaceNull(Object value){
		 Object rtnValue = value;
		 
		 if( rtnValue == null || !"java.lang.String".equals(rtnValue.getClass().getName())) {
			rtnValue = "";
		 }
		 	return replaceNull((String)rtnValue, "");
	 }

	 /**
	  * 파라미터로 넘어온 값이 null 이거나 공백이 포함된 문자라면
	  * defaultValue를 return. 아니면 값을 trim해서 넘겨준다.
	  * @param value
	  * @param repStr
	  * @return
	  */
	 public static String replaceNull(String value, String defaultValue) {
		 if (isNull(value)) {
			return defaultValue;
		 }
		 	return value.trim();
	 }

	 /**
	  * Object를 받아서 String 형이 아니거나 NULL이면 defaultValue를 return
	  * String 형이면 형 변환해서 넘겨준다.
	  * @param value
	  * @param repStr
	  * @return
	  */
	 public static String replaceNull(Object value, String defaultValue) {
		 String valueStr = replaceNull(value);
		 
		 if ( isNull(valueStr) ) {
			return defaultValue;
		 }
		 	return valueStr.trim();
	 }

	 /* NS002 */

	 /**
	  * String을 int형으로
	  * @param value
	  * @return
	  */
	 public static int parseInt(String value) {
		 return parseInt(value, 0);
	 }
	 
	 /**
	  * Object를 int형으로, defaultValue는 0이다.
	  * @param value
	  * @return
	  */
	 public static int parseInt(Object value) {
		 String valueStr = replaceNull(value);
		 return parseInt(valueStr, 0);
	 }
	 /**
	  * Object를 int형으로, Object가 null이면 defaultValue return
	  * @param value
	  * @param defaultValue
	  * @return
	  */
	 public static int parseInt(Object value, int defaultValue) {
		 String valueStr = replaceNull(value);
		 return parseInt(valueStr, defaultValue);
	 }

	 /**
	  * Exception을 String으로 뽑아준다.
	  * @param ex
	  * @return
	  */
	 public static String stackTraceToString2(Throwable e) {
		 ByteArrayOutputStream b = new ByteArrayOutputStream();
		 PrintStream p = new PrintStream(b);
		 e.printStackTrace(p);
		 p.close();
		 String stackTrace = b.toString();
		 
	 	try {
			 b.close();
		 } catch (IOException ex) {
			 ex.printStackTrace();
		 }
	 	//return convertHtmlBr(stackTrace);
		return stackTrace;
	 }

	 /**
	  * String 배열을 List로 변환
	  * @param values
	  * @return
	  */
	 public static List changeList(String [] values) {
		 List list = new ArrayList();
	
		 if( values == null ) {
			return list;
		 }
		 	for(int i=0, n=values.length; i<n; i++) {
		 	list.add(values[i]);
		 }
		 	return list;
	 }


	 
}
