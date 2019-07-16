package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ServletTest extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServletTest() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*response.setHeader("ContextType", "application/json;charset=utf-8");
		ServletContext servletContext  = getServletContext();
		String path = servletContext.getRealPath("");
		String rootPath = servletContext.getContextPath();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("path", path);
		jsonObject.put("rootPaht", rootPath);
		System.out.println(path);
		System.out.println(rootPath);
		PrintWriter writer = response.getWriter();
		writer.println(jsonObject);*/
		request.setAttribute("first", "first");
		request.getRequestDispatcher("/mmm.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
