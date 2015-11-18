package com.ft.pda;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CallService
 */
@WebServlet(name="/CallService",urlPatterns = {"/pdaclient/*"})
public class CallService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String function = "";
		String args = "";
		HttpSession session = null;
		if (request.getSession().getAttribute("username") == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		session = request.getSession();
		args = (new StringBuilder(String.valueOf((String) session.getAttribute("ip")))).append("\t")
				.append((String) session.getAttribute("factno")).toString();
		for (Enumeration<String> en = request.getParameterNames(); en.hasMoreElements();) {
			String paramName = (String) en.nextElement();
			if (paramName.equals("function"))
				function = request.getParameter(paramName);
			else
				args = (new StringBuilder(String.valueOf(args))).append("\t").append(request.getParameter(paramName))
						.toString();
		}

		PDAServices services = (PDAServices) request.getSession().getAttribute("services");
		if (services == null)
			try {
				PDAServicesServiceLocator servicelocator = new PDAServicesServiceLocator();
				servicelocator.setPDAServicesEndpointAddress((String) session.getAttribute("webserviceurl"));
				services = servicelocator.getPDAServices();
				String result = services.PDACall(function, args, (String) session.getAttribute("dbconn"));
				response.setCharacterEncoding("UTF-8");
				response.getWriter().println(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
