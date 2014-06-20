/**
 * 
 */

$("#gridStart").click(function() {
	$.post("http://localhost:8080/21_EersteJsp/eerste.jsp", { "Excecute command": "Excecute command" },function() {selectGUI(this);},"html");
});