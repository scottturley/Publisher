package publisher.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publisher.data.NewsItem;
import publisher.data.NewsItemDAO;

public class ViewNewsItemServlet extends ServletParent
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		logger.debug("doGet()");
		String idString = req.getParameter("id");
		Long id = new Long(idString);
		NewsItem newsItem = new NewsItemDAO().find(id);

		if (newsItem == null)
		{
			resp.sendRedirect("list-news-items");
			return;
		}

		req.setAttribute("newsItem", newsItem);
		jsp.forward(req, resp);
	}
}
