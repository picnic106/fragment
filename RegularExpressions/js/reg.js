//http://pic.service.yaolan.com/32/20150723/12/1437649675148.html
//取出如上地址最后从/到.html的部分
var itemUrl=item.url;

var reg=/\/([^\/]*)\./g; 
var matchitemUrl=itemUrl.match(reg);
alert(matchitemUrl[matchitemUrl.length-1])                  

//http://blog.csdn.net/zaifendou/article/details/5746988
