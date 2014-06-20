

function autoResizeGUI() {
	resizeIcons();
	//Controls icons to 0 padding top
	$(".icon").each(function() {
	    $(this).css({ 'padding-top': 0 });
	});
	
	$("#googleMap").height($("#map").height()-$("#googleMap").position().top);
	$("#info").height($("#information").height()-$("#info").position().top);
	$("#list").height($("#transportlist").height()-$("#list").position().top);
	$("#controls").height($("#control").height()-$("#controls").position().top);
	
	//Lists
	$(".unitInformation").each(function() {
		resizeParent($(this));
	});
	
	$(".currentPosition").each(function() {
		resizeParent($(this));
	});
	
	//Controls
	resizeIcons();
	$(".icon").each(function() {
		var newtoptoparent = ($(this).parent().height()- $(this).height())/2;
	    $(this).css({ 'padding-top': newtoptoparent });
	    
	    
	});
}

function resizeIcons() {
	//Resize the icon to match its parents parent height or width to 60 % of that
	$(".fa").each(function() {
		var parentsParentWidth = $(this).parent().parent().width();
		var parentsParentHeight = $(this).parent().parent().height();
		
		if (parentsParentWidth > parentsParentHeight) {
			$(this).css({'font-size': parentsParentHeight*0.6});
		}
		else {
			$(this).css({'font-size': parentsParentWidth*0.6});
		}
	});	
}

function resizeParent(obj) {
	var ownHeight = obj.height();
	var parentHeight = obj.parent().height();
	
	if (ownHeight > parentHeight) {
		obj.parent().css({height: ownHeight});
	}
}

function showInformation(obj) {
	var ID = $(obj).attr('id');
	var IDPart = ID.split('_')[1];
	
	$(".infoshow").each(function() {
		if ($(this).attr('id').split('_')[1] === IDPart) {
			$(this).removeClass("hidden");
		}
		else {
			$(this).addClass("hidden");
		}
	});
	autoResizeGUI();
	
	$(".infoclick").each(function() {
		$(this).removeClass("clicked");
	});
	$(obj).addClass("clicked");
}

function selectGUI(obj) {
	var IDtoMaximize = $(obj).attr('id').split('_')[1];
	
	if (IDtoMaximize != "all") {
		$(".guiField").each(function() {
			if ($(this).attr('id') == IDtoMaximize) {
				$(this).removeClass("hidden");
				$(this).addClass("maximize");
			}
			else
			{
				$(this).removeClass("maximize");
				$(this).addClass("hidden");
			}
		});
	}
	else {
		$(".guiField").each(function() {
			$(this).removeClass("hidden");
			$(this).removeClass("maximize");
		});
	}
	autoResizeGUI();
	
	//Set the button as clicked
	//First set all other nav buttons as unclicked
	$(".navigation").each(function() {
		$(this).removeClass("clicked");
	});
	$(obj).addClass("clicked");
}

$(document).ready(function () {
	autoResizeGUI();
	$(window).on('resize', function(){
	      autoResizeGUI();
	});
	
	$(".infoclick").each(function() {
		$(this).click(function() {showInformation(this);});
	});
	
	$(".navigation").each(function() {
		$(this).click(function() {selectGUI(this);});
	});
	
	$("#gridStart").click(function() {
		$.post("http://10.42.0.1:8080/21_EersteJsp/eerste.jsp", { "Excecute command": "Excecute command" },function() {selectGUI(this);},"html");
	});
	$("#gridStop").click(function() {
		$.post("http://10.42.0.1:8080/21_EersteJsp/eerste.jsp", { "Pause command": "Pause command" },function() {selectGUI(this);},"html");
	});
});