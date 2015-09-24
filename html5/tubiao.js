

function Tubiao(svgId){
	this.svg =   document.getElementById(svgId);
	this.zeroPoint = {
						x:69,
						y:398,
						yLength:337,
						xLength:550
					};
}
/**
 *
 */
Tubiao.prototype.init=function(cur,data1,data2,data3,data4 ){
	var start , step, times ,cur,data ;

	//x.init( 1 , 2, 12 ,realMonth, [data1,data2,data3,data4] );

	var step=1;
	var start=0;
	var end = 0;
	var stepNum=12;
	//定义 起点，步长，步数
	if( realMonth <= 12 )
	{
		step=1;
	}else if( realMonth <=24)
	{
		step=2;
	} else if( realMonth <= 36 ){
		step=3;
	}
	start = Math.min(data1,data2,data3,data4,realMonth) -  step;
	end = Math.max(data1,data2,data3,data4,realMonth) +  step;
	stepNum = parseInt( ( end - start )/step ) + (( end - start )%step == 0?0:1) ;

	var data = [data1,data2,data3,data4];
	times = stepNum;

	//x.init( start , step, stepNum ,realMonth, [data1,data2,data3,data4] );


	
	this.svg.setAttribute("viewBox" , "0 0 640 530");
	this.svg.setAttribute("width",document.body.offsetWidth);
	this.svg.setAttribute("height",document.body.offsetWidth * 530/640);
	//data = [1,8,3,4];


	var stepLength = this.zeroPoint.yLength / times;
	var curY = stepLength * ((cur - start )/step );

	var lineX = this.createLine({
									x : this.zeroPoint.x,
									y : this.zeroPoint.y
								},{
									x : this.zeroPoint.x + this.zeroPoint.xLength,
									y : this.zeroPoint.y });
	var lineY = this.createLine({
									x : this.zeroPoint.x,
									y : this.zeroPoint.y
								},{
									x : this.zeroPoint.x,
									y : this.zeroPoint.y - this.zeroPoint.yLength});

	this.createRuler(start , step, times );

	this.createText("月",{x:6,y:60},20,"#FF4242");
	this.createText("运动与控制",{x:66,y:436},24,"#FF8B71");
	this.createText("探索与操作",{x:217,y:436},24,"#FF8B71");
	this.createText("语言",{x:401,y:436},24,"#FF8B71");
	this.createText("社会适应",{x:517,y:436},24,"#FF8B71");

	var greenRect = this.createRect(curY - stepLength ,stepLength * 2,"#BBF2E7");
	var redRect = this.createRect(0,curY - stepLength,"#FFF3D2");

	var polygon = this.createPolygon (curY);

	for(var i=0;i<data.length; i++){
		var level = data[i];
		var text ="";
		var color = "";
		if( level > cur + step )
		{
			text = "很棒";
			color = "#8EDF62";
		}else if(level < cur - step)
		{			
			text = "加油";
			color = "#FF9274";
		}else{
			text = "不错";
			color = "#4CE0C2";
		}
		var item  =  this.createItem(text,{x:i*145+20,y:0},(level - start)/step * stepLength ,color);

	}	
}

Tubiao.prototype.createLine = function(startXY,endXY)
{
	var line =  document.createElementNS("http://www.w3.org/2000/svg", "line"); 
	//line.id = "lineX";
	line.x1.baseVal.value = startXY.x;
	line.y1.baseVal.value = startXY.y;
	line.x2.baseVal.value = endXY.x;
	line.y2.baseVal.value =endXY.y;
	line.setAttribute("stroke","#b8b8b8");
	this.svg.appendChild( line );
	return line;
}

Tubiao.prototype.createText = function( str,postion,fontSize,color,fontFamily)
{
	fontFamily = fontFamily || "'ArialMT'";
	var text = document.createElementNS("http://www.w3.org/2000/svg", "text");
	text.setAttribute("x", postion.x );
	text.setAttribute("y", postion.y );
	text.textContent = str ;
	text.setAttribute("font-size", fontSize );
	text.setAttribute("fill", color );
	text.setAttribute("font-family",fontFamily);
	this.svg.appendChild( text );
	return text;
}

Tubiao.prototype.createRuler = function ( start , step, times )
{
	var ruler = [];
	var everyY = this.zeroPoint.yLength / times ;
	for(var i = 0; i <= times; i++ )
	{
		var point = {
			text: this.createText(	 start + (i*step) ,
									 {	x:this.zeroPoint.x - 40,
									 	y:this.zeroPoint.y - (i *  everyY )  },
									 20,
									 "#707070"
								 ),
			line: i==0?null:this.createLine({
									x: this.zeroPoint.x - 12,
									y: this.zeroPoint.y - (i *  everyY ) 
									},
								{
									x: this.zeroPoint.x,
									y: this.zeroPoint.y - (i *  everyY ) 
								}
				)
		};
		this.svg.appendChild( point.text );
		if(point.line  ){
			this.svg.appendChild( point.line );	
		}
		

		ruler.push( point );
	}
}

Tubiao.prototype.createRect = function (y,height,color)
{
	var position = {
		x: this.zeroPoint.x ,
		y: this.zeroPoint.y - y - height
	};
	var width = 550;
	var fillRule = "evenodd";
	var clipRule = "evenodd";
	var Rect = document.createElementNS("http://www.w3.org/2000/svg", "rect");
	Rect.setAttribute("x", position.x );
	Rect.setAttribute("y", position.y );
	Rect.setAttribute("width", width );
	Rect.setAttribute("height", height );
	Rect.setAttribute("fill",color);
	Rect.setAttribute("fill-rule",fillRule);17
	Rect.setAttribute("clip-rule",clipRule);
	this.svg.appendChild( Rect );
	return Rect;
}


Tubiao.prototype.createPolygon = function (height)
{



	var color = "#FF4242";
	var fillRule  = "evenodd";
	var clipRule = "evenodd";
	var position = {x:this.zeroPoint.x ,
					y:this.zeroPoint.y - height };

	var Polygon = document.createElementNS("http://www.w3.org/2000/svg", "polygon");


	var points = ""
	+ position.x + "," + ( position.y - 10 ) + " "
	+ position.x + "," + ( position.y + 10 ) + " "
	+ ( position.x + 12) + "," + position.y  + " "

	Polygon.setAttribute("points", points);
	Polygon.setAttribute("fill",color);
	Polygon.setAttribute("fill-rule",fillRule);
	Polygon.setAttribute("clip-rule",clipRule);
	this.svg.appendChild( Polygon );
	return Polygon;
}

Tubiao.prototype.createPath = function(width,height,postion,color,fillRule,clipRule)
{


	var x = postion.x + this.zeroPoint.x;
	var y =  this.zeroPoint.y - postion.y ;
	
	var borderRadius= 8;

	var d = "M" + x + "," + y
			+ "l" + 0 + "," + ( -1 *  (height - borderRadius) )    		
			+ "q"  	+ 0 + "," + (-1 * borderRadius)+ " "
					+ borderRadius + "," + ( -1 * borderRadius) 
			+ "l" + ( width - (2 * borderRadius) ) + "," + 0
			+ "q"  	+ borderRadius + "," + 0 + " "
					+ borderRadius + "," + borderRadius 			
			+ "l" + ( 0 ) + "," + (  height - borderRadius ) + "z";




	var Path = document.createElementNS("http://www.w3.org/2000/svg", "path");
	Path.setAttribute("d", d);
	Path.setAttribute("fill",color);
	Path.setAttribute("fill-rule",fillRule);
	Path.setAttribute("clip-rule",clipRule);
	this.svg.appendChild( Path );
	return Path;
}

Tubiao.prototype.createItem = function(str,postion,height,color)
{
	 
	var width = 78;	
	var fontSize = 24;
	var Path  = this.createPath(width,height,postion,color,"evenodd","evenodd");
	var text = this.createText(str,{
		x: this.zeroPoint.x + postion.x + (width - fontSize*2)/2,
		y : this.zeroPoint.y -  postion.y  - height + fontSize
	},24,"#fff",'SimSun');

	//Path.appendChild( text );

}


<! -- 页面 -->
<svg id="svg" version="1.1" width="640" height="488"  preserveAspectRatio="xMinYMin meet" ></svg>

//这部分数据由后端给出  end
var x = new Tubiao('svg');
x.init(realMonth,data1,data2,data3,data4);
