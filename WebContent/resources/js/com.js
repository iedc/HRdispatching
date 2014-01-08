function tipShow(idName){

	var idObj = $(idName);
	var idBgObj = $("#bgWindow");

	if(idBgObj.length == 0){
		var iframe,div;
		div = $("<div></div>");
		div.attr({id:"bgWindow",style:"display:none"});
		iframe = $("<iframe></iframe>");
		iframe.attr({id:"bgWindowIframe",src:"about:blank",marginwidth:"0",marginheight:"0",frameBorder:"no",framespacing:"0",allowtransparency:"true"});
		div.append(iframe);
		$(document.body).append(div);
		idBgObj = $("#bgWindow");	
	}

	var winH = $(window).height();
	var docH = $(document.body).height();
	if(winH > docH){docH = winH;}
	
	var winW = $(window).width();
	var docW = $(document.body).width();
	if(winW > docW){docW = winW;}

	var scrollH = $(document).scrollTop();
	if(scrollH == undefined){scrollH = 0}
	//alert(idObj.height());
	var t = parseInt((winH - idObj.height())/2);
	if(idObj.css("position") == "absolute"){t = t + scrollH;}
	if(t != parseInt(idObj.css("top"))){idObj.css("top",t);}

	var l = parseInt((winW - idObj.width())/2);
	if(l < 0){l = 0;}
	if(l != parseInt(idObj.css("left"))){idObj.css("left",l);}
	
	if(docW != parseInt(idBgObj.css("width"))){idBgObj.css("width",docW);}
	if(docH != parseInt(idBgObj.css("height"))){idBgObj.css("height",docH);idBgObj.find("iframe").css("height",docH);}

	idBgObj.show();
	idObj.show();

	window.onresize = function(){
		if(idObj.css("display") == "block"){tipShow(idName);}
	};
	window.onscroll = function(){
		if(idObj.css("display") == "block"){tipShow(idName);}
	};
	
	var close = idName + "Close";
	var reset = idName + "Reset";
	var ok = idName + "Ok";
	if($(close).length == 1){$(close).click(function(){tipHide(idName);});}
	if($(reset).length == 1){$(reset).click(function(){tipHide(idName);});}
	if($(ok).length == 1){$(ok).click(function(){tipHide(idName);});}
}

function tipHide(idName){$(idName).hide();$("#bgWindow").hide();}