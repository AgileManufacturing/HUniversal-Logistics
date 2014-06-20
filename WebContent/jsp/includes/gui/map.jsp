<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false">
</script>

<script>
function initialize()
{
var mapProp = {
  center:new google.maps.LatLng(51.393191,5.387833),
  zoom:15,
  mapTypeId:google.maps.MapTypeId.ROADMAP
  };
var map=new google.maps.Map(document.getElementById("googleMap")
  ,mapProp);
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>

<div id="map" class="guiField left">
	<div id="mapHeader" class="fieldHeader"><div class="head">Map</div></div>
	<div id="googleMap"></div>
</div>