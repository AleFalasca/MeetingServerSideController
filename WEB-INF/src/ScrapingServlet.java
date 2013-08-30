import java.io.*;
import java.util.HashMap;

import javax.servlet.*;
import javax.servlet.http.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class ScrapingServlet extends HttpServlet {

	MeetingUtil meetingUtil;
	
    HashMap<String, String> couple = new HashMap<String, String>();
	  public void init() throws ServletException
	  {
		  meetingUtil = new MeetingUtil();
		  
	  }


	
	        

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
	      // Set response content type
	      response.setContentType("text/html");
	      String gara = request.getParameter("gara");
	      // Actual logic goes here.
	     
	    /*  System.out.println(meetingUtil.returnTable());
	      response.getOutputStream().write(meetingUtil.returnTable().getBytes());*/
	  }
	  
	     
	  public void destroy()
	  {
	      // do nothing.
	  }
}
