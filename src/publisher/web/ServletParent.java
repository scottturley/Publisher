package publisher.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class ServletParent extends HttpServlet
{
	protected Logger logger = Logger.getLogger(this.getClass());
	protected RequestDispatcher jsp;
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		String jsp_path = config.getInitParameter("jsp-path");
		logger.debug("Initializating, jsp path = " + jsp_path);
		ServletContext context = config.getServletContext();
		jsp = context.getRequestDispatcher(config.getInitParameter("jsp-path"));
	}
}
