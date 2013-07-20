<%@ page import="java.util.Iterator" %>
<%@ page import="publisher.data.NewsItem" %>
<jsp:useBean id="newsItems" scope="request" type="java.util.List" />
<%@ include file="top.inc" %>
<%@ include file="middle.inc" %>
<ul>
   <% 
      Iterator it = newsItems.iterator();
      while (it.hasNext())
      {
         NewsItem newsItem = (NewsItem) it.next();
   %>
      <li>
         <a href="view-news-item?id=<%=newsItem.getId()%>">View the <%=newsItem.getTitle()%> Page</a> => <a href = "<%=newsItem.getUrl()%>"><%=newsItem.getUrl() %></a>
      </li>
   <% } %>
</ul>
<%@ include file="bottom.inc" %>