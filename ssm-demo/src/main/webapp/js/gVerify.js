!(function(window, document) {
	function GVerify(options) { //创建一个图形验证码对象，接收options对象为参数
		this.options = { //默认options参数值
			id: "", //容器Id
			canvasId: "verifyCanvas", //canvas的ID
			width: "100", //默认canvas宽度
			height: "30", //默认canvas高度
			type: "blend", //图形验证码默认类型blend:数字字母混合类型、number:纯数字、letter:纯字母
			code: ""
		}
		
		if(Object.prototype.toString.call(options) == "[object Object]"){//判断传入参数类型
			for(var i in options) { //根据传入的参数，修改默认参数值
				this.options[i] = options[i];
			}
		}else{
			this.options.id = options;
		}
		
		this.options.numArr = "0,1,2,3,4,5,6,7,8,9".split(",");
		this.options.letterArr = getAllLetter();

		this._init();
		this.refresh();
	}

	GVerify.prototype = {
		/**版本号**/
		version: '1.0.0',
		
		/**初始化方法**/
		_init: function() {
			var con = document.getElementById(this.options.id);
			var canvas = document.createElement("canvas");
			this.options.width = con.offsetWidth > 0 ? con.offsetWidth : "100";
			this.options.height = con.offsetHeight > 0 ? con.offsetHeight : "30";
			canvas.id = this.options.canvasId;
			canvas.width = this.options.width;
			canvas.height = this.options.height;
			canvas.style.cursor = "pointer";
			canvas.innerHTML = "您的浏览器版本不支持canvas";
			con.appendChild(canvas);
			var parent = this;
			canvas.onclick = function(){
				parent.refresh();
			}
		},
		
		/**生成验证码**/
		refresh: function() {
			var _this = this.options
			$.ajax({
				type: "POST",
				url: route + "/system/login/getVerifyNum",
				data:"",
				success: function(data) {
					var nn = String(data.num).split("");
					num = nn;
					var canvas = document.getElementById(_this.canvasId);
					if(canvas.getContext) {
						var ctx = canvas.getContext('2d');
					}
					ctx.textBaseline = "middle";

					ctx.fillStyle = randomColor(180, 240);
					ctx.fillRect(0, 0, _this.width, _this.height);

					if(_this.type == "blend") { //判断验证码类型
						var txtArr = _this.numArr.concat(_this.letterArr);
					} else if(_this.type == "number") {
						var txtArr = _this.numArr;
					} else {
						var txtArr = _this.letterArr;
					}

					for(var i = 0; i <= 3; i++) {
						var txt = num[i];
						_this.code += txt;
						ctx.font = randomNum(_this.height/1.3, _this.height) + 'px SimHei'; //随机生成字体大小
						ctx.fillStyle = randomColor(50, 160); //随机生成字体颜色		
						ctx.shadowOffsetX = randomNum(-3, 3);
						ctx.shadowOffsetY = randomNum(-3, 3);
						ctx.shadowBlur = randomNum(-3, 3);
						ctx.shadowColor = "rgba(0, 0, 0, 0.3)";
						var x = _this.width / 5 * (i+1);
						var y = _this.height / 2;
						var deg = randomNum(-30, 30);
						/**设置旋转角度和坐标原点**/
						ctx.translate(x, y);
						ctx.rotate(deg * Math.PI / 180);
						ctx.fillText(txt, 0, 0);
						/**恢复旋转角度和坐标原点**/
						ctx.rotate(-deg * Math.PI / 180);
						ctx.translate(-x, -y);
					}
					num = Number(num.join(""))
				}
			})
		},
		
		/**验证验证码**/
		validate: function(code){
			var code = code.toLowerCase();
			var str = this.options.code.toLowerCase();
			var v_code = str.substr(str.length-4);
			if(code == v_code){
				return true;
			}else{
				return false;
			}
		}
	}
	/**生成字母数组**/
	function getAllLetter() {
		var letterStr = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		return letterStr.split(",");
	}
	/**生成一个随机数**/
	function randomNum(min, max) {
		return Math.floor(Math.random() * (max - min) + min);
	}
	/**生成一个随机色**/
	function randomColor(min, max) {
		var r = randomNum(min, max);
		var g = randomNum(min, max);
		var b = randomNum(min, max);
		return "rgb(" + r + "," + g + "," + b + ")";
	}
	window.GVerify = GVerify;
})(window, document);