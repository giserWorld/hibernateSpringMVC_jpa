/*
 *********************时间:2019.08.10 wxt********************
 *更新时间:2020.11.30 wxt
 *************************一、插件概述************************
 *该插件封装了一个canvasRender对象，该对象包含着画布绘制的相关方法
 *************************二、方法*************************
 *createShape_circle(coords,canvas,attr):创建圆图形
 *clearCanvas(canvas):清除canvas画布
 *drawIcon_circle(radius,color):封装绘制圆点图标方法
 *drawIcon_line(canvasWidth,canvasHeight,color):封装绘制线图标方法
 *drawIcon_rect(canvasWidth,canvasHeight,fillColor,borderColor):封装绘制矩形图标方法
 *drawCircle(canvas,style,radius):点击画圆
 *drawPencil(canvas,style):自由勾绘画笔
 *drawRect_one(canvas,style):自由勾绘单个矩形
 *setCanvasAdapt(canvas,isAdapt):设置canvas自适应容器大小
 *************************三、说明*************************
 *
 *   
 *    
 *    
 **************************四、注意*************************
 * 
 *
 *
 * */

//import $ from 'jquery';
var canvasRender={
	drawStatus:true,//绘图状态		
};

window.canvasRender=canvasRender;


/**************************创建圆图形************************
 *更新时间:2020.11.20
 *参数:coords(Array):屏幕坐标点,例如[5,10]
 *****canvas(Dom):canvas dom对象
 *****attr(Object):[option] 图形属性,控制形状大小与样式,详见笔录
 *无返回值
 */
canvasRender.createShape_circle=function(coords,canvas,attr){
	attr=attr||{};
	if(canvas&&coords&&coords.length>1){
		let ctx2D=canvas.getContext("2d");
		for(let key in attr){
			ctx2D[key]=attr[key];
		}
		let radius=attr.radius||10.0;
		ctx2D.beginPath();
		ctx2D.arc(coords[0],coords[1],Number(radius),0,2*Math.PI);
		if(attr.fillStyle)ctx2D.fill();//填充
		ctx2D.stroke();//边框线
	}
}//e



/**************************点击画圆************************
 *更新时间:2020.11.25
 *参数:canvas(Dom):canvas dom对象
 *****style(Object):[option] canvas样式,样式规则见备注
 *****radius(Number):[option] 圆半径,默认为10
 *****callback(Function):[option] 勾绘回调函数,参数(canvasCoord,canvas,mouseEvent)
 *无返回值
 *注解:
 *1.勾绘坐标是相对于canvas的屏幕坐标,而不是相对于视口原点的屏幕坐标
 */
canvasRender.drawCircle=function(canvas,style,radius,callback){
	style=style||{};
	radius=radius||10.0;
	callback=callback||function(){};
	if(canvas){
		let ctx2D=canvas.getContext("2d");
		for(let key in style){
			ctx2D[key]=style[key];
		}
		//onmousemove
		$(canvas).unbind("mousemove").bind("mousemove",function(mouseEvent){
			
		});
		
		//onmousedown
		$(canvas).unbind("mousedown").bind("mousedown",function(mouseEvent){
			
			
		});
		
		//onmouseup
		$(canvas).unbind("mouseup").bind("mouseup",function(mouseEvent){
			
		});
		
		//onclick
		$(canvas).unbind("click").bind("click",function(mouseEvent){
			if(!canvasRender.drawStatus)return false;
			let offset=canvas.getBoundingClientRect();
			let x=mouseEvent.pageX-offset.left;
			let y=mouseEvent.pageY-offset.top;
			ctx2D.beginPath();
			ctx2D.arc(x,y,radius,0,2*Math.PI);
			//ctx2D.fill();//填充
			ctx2D.stroke();//边框线
			callback([x,y],canvas,mouseEvent);
		});
	}
}//e




/**************************自由勾绘单个矩形************************
 *更新时间:2020.11.19
 *参数:canvas(Dom):canvas dom对象
 *****style(Object):[option] canvas样式,样式规则见备注
 *无返回值
 */
canvasRender.drawRect_one=function(canvas,style){
	let _self={startX:0,startY:0,rectWidth:1,rectHeight:1,mouseStatus:""};
	style=style||{};
	if(canvas){
		let ctx2D=canvas.getContext("2d");
		for(let key in style){
			ctx2D[key]=style[key];
		}
		//onmousemove
		$(canvas).unbind("mousemove").bind("mousemove",function(mouseEvent){
			if(_self.mouseStatus==="mousedown"){//如果按下鼠标按钮，则绘制鼠标移动轨迹
				let offset=canvas.getBoundingClientRect();
				ctx2D.clearRect(0,0,canvas.width,canvas.height);
				var x = mouseEvent.pageX-offset.left;
				var y = mouseEvent.pageY-offset.top;
				_self.rectWidth=x-_self.startX;
				_self.rectHeight=y-_self.startY;
				ctx2D.strokeRect(_self.startX,_self.startY,_self.rectWidth,_self.rectHeight);  
				if(style.fillStyle)ctx2D.fillRect(_self.startX,_self.startY,_self.rectWidth,_self.rectHeight);  
			}
		});
		
		//onmousedown
		$(canvas).unbind("mousedown").bind("mousedown",function(mouseEvent){
			_self.mouseStatus="mousedown";
			let offset=canvas.getBoundingClientRect();
			_self.startX=mouseEvent.pageX-offset.left;
			_self.startY=mouseEvent.pageY-offset.top;
		});
		
		//onmouseup
		$(canvas).unbind("mouseup").bind("mouseup",function(mouseEvent){
			_self.mouseStatus="mouseup";
		});
		
		//onclick
		$(canvas).unbind("click").bind("click",function(mouseEvent){
			_self.mouseStatus="click";
		});
	}
}//e


/**************************自由勾绘画笔************************
 *更新时间:2020.11.19
 *参数:canvas(Dom):canvas dom对象
 *****style(Object):[option] canvas样式,样式规则见备注
 *无返回值
 *注解:
 *1.使用该方法时需要设置全局变量"mouseStatus"
 */
canvasRender.drawPencil=function(canvas,style){
	let _self={mouseStatus:""};
	style=style||{};
	if(canvas){
		let ctx2D=canvas.getContext("2d");
		for(let key in style){
			ctx2D[key]=style[key];
		}
		//onmousemove
		$(canvas).unbind("mousemove").bind("mousemove",function(mouseEvent){
			let offset=canvas.getBoundingClientRect();
			if(_self.mouseStatus==="mousedown"){//如果按下鼠标按钮，则绘制鼠标移动轨迹
				let x=mouseEvent.pageX-offset.left;
				let y=mouseEvent.pageY-offset.top;
				ctx2D.lineTo(x,y);
				ctx2D.stroke();  
			}
			else{
				//清空子路径列表
				ctx2D.beginPath();
				let x=mouseEvent.pageX-offset.left;
				let y=mouseEvent.pageY-offset.top;
				ctx2D.moveTo(x,y);
			}
		});
		//onmousedown
		$(canvas).unbind("mousedown").bind("mousedown",function(mouseEvent){
			_self.mouseStatus="mousedown";
		});
		//onmouseup
		$(canvas).unbind("mouseup").bind("mouseup",function(mouseEvent){
			_self.mouseStatus="mouseup";
		});
		//onclick
		$(canvas).unbind("click").bind("click",function(mouseEvent){
			_self.mouseStatus="click";
		});
	}
}//e



/**************************设置canvas自适应容器大小************************
 *更新时间:2020.11.30
 *参数:canvas(Dom):canvas dom对象
 *****isAdapt(Boolean):[option] 是否强制更新canvas自适应容器,默认为false
 *无返回值
 */
canvasRender.setCanvasAdapt=function(canvas,isAdapt){
isAdapt=isAdapt===true?true:false;
if(canvas&&canvas.height===150){//设置width、height属性
canvas.style.width="100%";	
canvas.style.height="100%";
canvas.width=canvas.clientWidth;
canvas.height=canvas.clientHeight;
}
else if(canvas&&isAdapt){
canvas.style.width="100%";	
canvas.style.height="100%";
canvas.width=canvas.clientWidth;
canvas.height=canvas.clientHeight;	
}
}//e



/**************************清除canvas画布************************
 *更新时间:2020.11.19
 *参数:canvas(Dom||String):canvas dom对象或canvasId
 *无返回值
 */
canvasRender.clearCanvas=function(canvas){
if(canvas){
if(typeof(canvas)==="string")canvas=document.getElementById(canvas);
let ctx=canvas.getContext("2d");
ctx.clearRect(0,0,canvas.width,canvas.height);
//样式将被清除
/*let lineWidth=ctx.lineWidth;
let strokeStyle=ctx.strokeStyle;
canvas.height=canvas.height; 
ctx.lineWidth=lineWidth;
ctx.strokeStyle=strokeStyle;*/
}
}//e



/***************************封装绘制圆点图标方法*********************
 *参数:radius(number):圆点半径
 *****color(string):线图标颜色,可以是十六进制或者rgba格式颜色，例如：“#FF2D00”，“rgba(255,45,0)”
 *返回值:iconImg(string):base64图片格式的字符串
 */
canvasRender.drawIcon_circle=function(radius,color){
let iconImg=null;//base64格式图标
if(radius&&color){
let canvasDom=document.createElement("canvas");
canvasDom.width=2*radius+2;
canvasDom.height=2*radius+2;
let ctx=canvasDom.getContext("2d");
let x=parseInt(radius+1);
let y=parseInt(radius+1);
ctx.beginPath();//开始绘制路径
ctx.strokeStyle=color;//线的颜色
ctx.fillStyle=color;
ctx.lineWidth=2.0;
ctx.beginPath();
ctx.arc(x, y,radius,0,2*Math.PI);
ctx.fill();
ctx.stroke();
iconImg=canvasDom.toDataURL();
}
return iconImg;
}//e


/***************************封装绘制线图标方法*********************
 *参数:canvasWidth(number):矩形图标宽度
 *****canvasHeight(number):矩形图标高度
 *****color(string):线图标颜色,可以是十六进制或者rgba格式颜色，例如：“#FF2D00”，“rgba(255,45,0)”
 *返回值:iconImg(string):base64图片格式的字符串
 */
canvasRender.drawIcon_line=function(canvasWidth,canvasHeight,color){
let iconImg=null;//base64格式图标
if(canvasWidth&&canvasHeight&&color){
let canvasDom=document.createElement("canvas");
canvasDom.width=canvasWidth;
canvasDom.height=canvasHeight;
let ctx=canvasDom.getContext("2d");
let lineLen=canvasWidth-2;
let lineH=canvasHeight/2;
ctx.beginPath();//开始绘制路径
ctx.strokeStyle=color;//线的颜色
ctx.lineWidth=2;
ctx.moveTo(1,lineH);//画笔的开始绘制的起点位置
ctx.lineTo(1+lineLen,lineH);//线段1的终点位置
ctx.stroke();
iconImg=canvasDom.toDataURL();
}
return iconImg;
}//e


 /***************************封装绘制矩形图标方法*********************
 *参数:canvasWidth(number):矩形图标宽度
 *****canvasHeight(number):矩形图标高度
 *****fillColor(string):矩形图标颜色,可以是十六进制或者rgba格式颜色，例如：“#FF2D00”，“rgba(255,45,0)”
 *****[borderColor(string)]:边框颜色，默认和填充色一样
 *返回值:iconImg(string):base64图片格式的字符串
 */
canvasRender.drawIcon_rect=function(canvasWidth,canvasHeight,fillColor,borderColor){
let iconImg=null;//base64格式图标
borderColor=borderColor || fillColor;
if(canvasWidth&&canvasHeight&&fillColor){
let canvasDom=document.createElement("canvas");
canvasDom.width=canvasWidth;
canvasDom.height=canvasHeight;
let ctx=canvasDom.getContext("2d");
let rectW=canvasWidth-2;
let rectH=canvasHeight-2;
ctx.beginPath();
ctx.fillStyle=fillColor;
ctx.fillRect(1, 1, rectW,rectH);
ctx.strokeStyle=borderColor;//线的颜色
ctx.strokeRect(1, 1, rectW,rectH);//绘制两次边界
ctx.strokeRect(1, 1, rectW,rectH);
iconImg=canvasDom.toDataURL();
}
return iconImg;
}//e

//export default canvasRender;
