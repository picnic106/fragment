//写入cookie
  function SetCookie(name,value)
  {
    var Days = 30; //此 cookie 将被保存 30 天
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/";
  }

  //读取cookie
  function getCookie(name)
  {
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
    if(arr != null)
      return unescape(arr[2]);
    return null;
  }


js如何设置一个全站都能使用的cookie来记住用户名，记住访客的信息，方便访客下次的操作。
js设置一个cookie网上有很多资料，但是只能在同目录下使用，其他地方就失效了。设置全局cookie只需加一个参数即可。

把 document.cookie=c_name+ "=" +escape(value)+
((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
改成 document.cookie=c_name+ "=" +escape(value)+
(expiredays==null) ? "" : ";expires="+exdate.toGMTString())+"; path=/";
