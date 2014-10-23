package com.nbw.common.util;

import java.util.Hashtable;

import org.jdom.Element;




/**
 * xml串处理接口，由业务系统实现
 * 
 * @author songyj
 *
 */
public interface CallBack {
   /**
    * 注册执行xml属性
    * 
    * 每个功能转换的方法可能不一样所以使用接口
    * 这样保证如果需要执行xml则不需要重复写代码
    * @param xmlFieldName
    */
   public  String  xmlHandleElement(Hashtable<String,Object> ht);   
   
   
   /**
    * 注册执行xml属性
    * 
    * 每个功能转换的方法可能不一样所以使用回调   
    * 这样保证如果需要执行xml则不需要重复写代码
    * @param xmlFieldName
    */
   public  String  xmlHandleElement(Element ro);  
   
}
