<%@ include file="top.inc" %>

<%@ include file="middle.inc" %>

<h1>Welcome</h1>

<p>
This is the publisher application.
It allows you to publish and manage a news feed.
</p>

<% 
    String message = (String) request.getAttribute("item_not_found");
    if (message != null) {
             out.println("<p>" + message + "</p>");
    }
%>

<%@ include file="bottom.inc" %>