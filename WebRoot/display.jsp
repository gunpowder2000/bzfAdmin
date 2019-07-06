
<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    /*
    String jsontype = (String) request.getAttribute("jsontype");
    if ("jsonarray".equals(jsontype)) {
        JSONArray json = null;
        json = (JSONArray) request.getAttribute("json");
        out.println(json);
    } else if ("jsonobject".equals(jsontype)) {
        JSONObject json = null;
        json = (JSONObject) request.getAttribute("json");
        out.println(json);
    } else {
        String json = "";
        json = (String)request.getAttribute("json");
        out.println(json);
    }
    */
    
    try{
     JSONArray jsonArray = null;
     jsonArray = (JSONArray) request.getAttribute("json");
     out.println(jsonArray);
    }catch(Exception e){
     JSONObject jsonObject = null; 
     jsonObject = (JSONObject) request.getAttribute("json");
     out.println(jsonObject);
    } 

%>
