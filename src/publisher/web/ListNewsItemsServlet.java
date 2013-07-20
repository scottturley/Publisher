package publisher.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publisher.data.NewsItem;
import publisher.data.NewsItemDAO;

public class ListNewsItemsServlet extends ServletParent
{  
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
   throws ServletException, IOException
   {
      logger.debug("doGet()");
      List<NewsItem> newsItems = new NewsItemDAO().findAll();
      req.setAttribute("newsItems", newsItems);
      jsp.forward(req, resp);
   }
}
