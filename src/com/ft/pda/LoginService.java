package com.ft.pda;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ft.util.CommonValues;
import com.ft.util.CommonXml;

import sun.org.mozilla.javascript.internal.Context;

/**
 * 處理用戶登錄，登出請求
 */

@WebServlet(name = "/LoginServlet", urlPatterns = { "/login.do", "/loginout.do" }, loadOnStartup = 1)
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 如果用戶點擊登出，則將頁面跳轉到登錄頁面
		if (request.getRequestURI().equals("/PDAClient/loginout.do")) {
			request.getSession().invalidate();
			response.sendRedirect("/PDAClient/login.jsp");
			return;
		}

		String useraccount = ""; // 用戶帳號
		String password = ""; // 用戶密碼
		String username = ""; // 用戶名稱
		String factno = ""; // 廠區編號
		String dbconn = ""; // 數據庫
		String function = ""; // 執行的方法
		String args = ""; // 方法的參數
		String webserviceurl = ""; // web服務的地址
		String ip = ""; // 客戶端IP
		String configfile = "/com/ft/util/clientconfig.xml"; // 配置文件
		NodeList nodelist = null; // XML節點集合

		// ip = request.getRemoteAddr();
		ip = "172.24.26.110";

		// 從配置文件中獲取廠區編號
		nodelist = CommonXml.getNode(configfile, "/client/ip-info[ip='" + ip.substring(0, 9) + "' or ip='"
				+ ip.substring(0, 7) + "' or ip='" + ip.substring(0, 6) + "']/fact_no");

		if (nodelist == null || nodelist.getLength() == 0) {
			request.setAttribute("error", "獲取IP配置信息錯誤");
			request.getRequestDispatcher("/frame/error.jsp").forward(request, response);
			return;
		}

		if (nodelist.getLength() > 1) {
			request.setAttribute("error", "IP配置信息重複");
			request.getRequestDispatcher("/frame/error.jsp").forward(request, response);
			return;
		}

		factno = CommonXml.getNodeValue(nodelist.item(0));
		if (factno == null || factno.length() == 0) {
			request.setAttribute("error", "無法獲取此IP對應的廠區編號!");
			request.getRequestDispatcher("/frame/error.jsp").forward(request, response);
			return;
		}

		// 從配置文件中獲取Web服務的地址
		nodelist = CommonXml.getNode(configfile, "/client/ip-info[ip='" + ip.substring(0, 9) + "' or ip='"
				+ ip.substring(0, 7) + "' or ip='" + ip.substring(0, 6) + "']/serverurl");

		if (nodelist == null || nodelist.getLength() == 0) {
			request.setAttribute("error", "獲取Web服務地址信息錯誤");
			request.getRequestDispatcher("/frame/error.jsp").forward(request, response);
			return;
		}

		if (nodelist.getLength() > 1) {
			request.setAttribute("error", "Web服務地址配置信息重複");
			request.getRequestDispatcher("/frame/error.jsp").forward(request, response);
			return;
		}

		webserviceurl = CommonXml.getNodeValue(nodelist.item(0));
		if (factno == null || factno.length() == 0) {
			request.setAttribute("error", "無法獲取此IP對應的Web服務地址!");
			request.getRequestDispatcher("/frame/error.jsp").forward(request, response);
			return;
		}

		args = ip + "\t" + factno;
		Enumeration<String> en = request.getParameterNames();

		while (en.hasMoreElements()) {
			String paramName = (String) en.nextElement();

			if (paramName.equals("system")) {
				// 根據選擇的系統獲取數據庫連接字符串
				nodelist = CommonXml.getNode(configfile, "/client/system-info[system='"
						+ new String(request.getParameter(paramName).getBytes("ISO-8859-1"), "UTF-8") + "']/dbSchema");

				if (nodelist == null || nodelist.getLength() == 0) {
					request.setAttribute("error", "獲取數據庫配置信息錯誤");
					request.getRequestDispatcher("/frame/error.jsp").forward(request, response);
					return;
				}

				if (nodelist.getLength() > 1) {
					request.setAttribute("error", "數據庫配置信息重複");
					request.getRequestDispatcher("/frame/error.jsp").forward(request, response);
					return;
				}

				dbconn = CommonXml.getNodeValue(nodelist.item(0));
				if (dbconn == null || dbconn.length() == 0) {
					request.setAttribute("error", "無法獲取此系統對應的數據庫信息!");
					request.getRequestDispatcher("/frame/error.jsp").forward(request, response);
					return;
				}

			} else if (paramName.equals("function")) {
				function = request.getParameter(paramName);
			} else {
				if (paramName.equals("useraccount"))
					useraccount = request.getParameter(paramName);

				if (paramName.equals("password"))
					password = request.getParameter("password");

				args = args + "\t" + request.getParameter(paramName);
			}

		}

		// 獲取WebService
		PDAServices services = (PDAServices) request.getSession().getAttribute("services");

		if (services == null) {
			try {
				PDAServicesServiceLocator servicelocator = new PDAServicesServiceLocator();

				// 設置WebService的URL
				servicelocator.setPDAServicesEndpointAddress(webserviceurl);

				services = servicelocator.getPDAServices();

				String result = services.PDACall(function, args, dbconn);

				if (result.substring(0, 1).equals("0")) {					
					// 將基本信息保存到Session中
					request.getSession().setAttribute("factno", factno);
					request.getSession().setAttribute("webserviceurl", webserviceurl);
					request.getSession().setAttribute("useraccount", useraccount);
					request.getSession().setAttribute("password", password);
					request.getSession().setAttribute("dbconn", dbconn);
					request.getSession().setAttribute("ip", ip);

					// 登錄成功後將頁面跳轉到system頁面
					request.getRequestDispatcher("/frame/system.jsp").forward(request, response);
					return;
				} else {
					// 登錄失敗時
					request.setAttribute("error", result);
					request.getRequestDispatcher("/frame/error.jsp").forward(request, response);
					return;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
