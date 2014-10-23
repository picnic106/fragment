XmlUtils
========

使处理xml的代码和业务代码分离，减少代码重复率。


使用示例：  

    
{% codeblock  lang:java 客户端示例%}
        ByteArrayInputStream xmlStream = null;
				try {
					xmlStream = new ByteArrayInputStream(users.getBytes("GBK"));
				} catch (UnsupportedEncodingException e) {
					logger.error(
							"findUsersRepeat-ByteArrayInputStream解析编码出现问题", e);
				}
				
				boolean yes = XmlUtils.handleXml(xmlStream, new CallBack() {
					public String xmlHandleElement(Hashtable<String, Object> ht) {
						lm.findRepeat(ht, filter);
						return null;
					}

					public String xmlHandleElement(Element ro) {
						return null;
					}
				});
{% endcodeblock %}   
