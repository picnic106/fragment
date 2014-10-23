package com.nbw.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.nbw.common.util.CallBack;

/**
 * 解析xml串
 *
 * 1，使用回调接口，使处理xml的代码和业务代码分离，且减少代码重复率
 *    其他代码也可以使用这个方法来解析自己的xml串，并嵌入自己的业务
 * 
 * 2，使用jdom解析xml串
 * 
 * @author songyj
 *
 */
public class XmlUtils {
	static Logger logger = Logger.getLogger(XmlUtils.class.getName());
	
  /**
   * 解析xml串
   * 应用串格式为：<root>
   *             <line><id>1222</id><name>测试</name></line>
   *             <line><id>1223</id><name>测试3</name></line>
   *           </root>
   * 
   * 1。取出根元素:root
   * 2。循环遍历子元素:line
   * 3。再循环遍历line元素的子元素:<id>1222</id><name>测试</name>
   * 4。将元素置入hashtable,让其他业务系统可以忽略jdom的存在
   * 5，调用接口，此接口由业务系统实现，因为每个业务系统需要处理的方式不一样
   * 
   * 缺点：目前xml只支持到三级
   * 
   * @param fi        传过来的InputStream流，用于解析xml串
   * @param callBack  回调接口
   * @return
   */
public static boolean handleXml(InputStream fi,CallBack callBack){
	        boolean states=false; 
			SAXBuilder sb = new SAXBuilder();    
			Document doc;
			try {
				doc = sb.build(fi);
				 Element root = doc.getRootElement(); // 得到根元素    
				 List<Element> focs =root.getChildren();
				 Element foc = null;
				 for(int i=0; i<focs.size(); i++) {
					foc=focs.get(i);
					Hashtable<String,Object> ht=transElementToHashTable(foc);
					callBack.xmlHandleElement(ht);//调用回调接口
			 	 }
				 states=true;
			} catch (JDOMException e) {
				logger.error("解析xml串出现JDOMException问题。",e);
			} catch (Exception e) {
				logger.error("解析xml串出现Exception问题。",e);
			} finally{
				try {
					fi.close();
				} catch (IOException e) {
					logger.error("关闭解析xml流错误",e);
				}//关闭流
			}
			return states;
  }
  
/**
 * 解析xml串
 * 应用串格式为：<root>
 *             <line><id>1222</id><name>测试</name></line>
 *             <line><id>1223</id><name>测试3</name></line>
 *           </root>
 * 
 * 1。取出根元素:root
 * 2。循环遍历子元素:line
 * 3。再循环遍历line元素的子元素:<id>1222</id><name>测试</name>
 * 4。将元素置入hashtable,让其他业务系统可以忽略jdom的存在
 * 5，调用接口，此接口由业务系统实现，因为每个业务系统需要处理的方式不一样
 * 
 * @param fi        传过来的InputStream流，用于解析xml串
 * @param callBack  回调接口
 * @param prexi     用于回调接口返回值的分隔符
 * @return
 */
public static String handleXml(InputStream fi,CallBack callBack,String prexi){
	        StringBuffer results=new StringBuffer(); 
			SAXBuilder sb = new SAXBuilder();    
			Document doc;
			try {
				 doc = sb.build(fi);
				 Element root = doc.getRootElement(); // 得到根元素    
				 List<Element> focs =root.getChildren();
				 Element foc = null;
				 for(int i=0; i<focs.size(); i++) {
					foc=focs.get(i);
					Hashtable<String,Object> ht=transElementToHashTable(foc);
					results.append(callBack.xmlHandleElement(ht)).append(prexi);//调用回调函数
			 	 }
			} catch (JDOMException e) {
				logger.error("解析xml串出现JDOMException问题。",e);
			} catch (Exception e) {
				logger.error("解析xml串出现Exception问题。",e);
			} finally{
				try {
					fi.close();
				} catch (IOException e) {
					logger.error("关闭解析xml流错误",e);
				}//关闭流
			}
			return results.length()==0?null:results.toString();
}


public static String handleXml(String ouInfoXml,CallBack callBack,String prexi,String contextType){
	/*
	 * 将传过来的字符串放入流，解析xml的sax将用到
	 */
	ByteArrayInputStream xmlStream = null;
	try {
		xmlStream = new ByteArrayInputStream(ouInfoXml.getBytes(contextType));
	} catch (UnsupportedEncodingException e) {
		logger.error("ByteArrayInputStream解析编码出现问题", e);
	}
	if (xmlStream!=null){
		return handleXml(xmlStream,callBack,prexi);
	}else{
	    return null;	
	}
}



public static boolean handleXml(String ouInfoXml,CallBack callBack,String contextType){
	/*
	 * 将传过来的字符串放入流，解析xml的sax将用到
	 */
	ByteArrayInputStream xmlStream = null;
	try {
		xmlStream = new ByteArrayInputStream(ouInfoXml.getBytes(contextType));
	} catch (UnsupportedEncodingException e) {
		logger.error("ByteArrayInputStream解析编码出现问题", e);
	}
	if (xmlStream!=null){
		return handleXml(xmlStream,callBack);
	}else{
	    return false;	
	}
}

/**
 * 将xml节点放入HashTable中
 * 便于其他业务方法使用
 * @param el
 * @return
 */
  public static Hashtable<String,Object> transElementToHashTable(Element el){
	  Hashtable<String,Object> ht=null;
	  if (el!=null){
		  List<Element> lines=el.getChildren();
		  if (lines.size()>0){
			  ht=new Hashtable<String,Object>();
			  Element line = null;// 存储xml的每个属性
			  for (int j = 0; j < lines.size(); j++) {
				    line = lines.get(j);
					String atrName=line.getName();
					String atrText=line.getText();   
					ht.put(atrName, atrText);
			  }
		  }
	  }
	  return ht;
  }
  /**
   * 返回xml的根节点元素
   * @param fi
   * @param callBack
   * @return
   */
  public static String handleXmlRootElement(InputStream fi,CallBack callBack) throws IOException{
		 SAXBuilder sb = new SAXBuilder();    
		 Document doc;
		try {
			doc = sb.build(fi);
			 Element root = doc.getRootElement(); // 得到根元素    
			 return callBack.xmlHandleElement(root);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return null;
 }
}
