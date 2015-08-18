String cookievalue=null;
  Cookie[] cooke=request.getCookies();
  for (int i=0;i<cooke.length;i++){
    if (cooke[i].getName().equals("jspcookie")){
      cookievalue=cooke[i].getValue();
    }
  }
  System.out.println(cookievalue);
  if (cookievalue==null){
    Cookie newCookie=new Cookie("jspcookie","ceshi");
    response.addCookie(newCookie);
  }
