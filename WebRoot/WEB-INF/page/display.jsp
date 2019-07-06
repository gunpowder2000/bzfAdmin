
<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    try{
        JSONArray json = null;
        json = (JSONArray) request.getAttribute("jsonObject");
        out.println(json);
    } catch(Exception e){ 
        JSONObject json = null;
        json = (JSONObject) request.getAttribute("jsonObject");        
        out.println(json);
    }

%>
