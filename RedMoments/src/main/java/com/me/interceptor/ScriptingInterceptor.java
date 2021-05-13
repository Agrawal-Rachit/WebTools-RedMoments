/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.interceptor;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ScriptingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {
		try {
			System.out.println("In Scripting Interceptor");
			request.setAttribute("unsafe_check", "false");
			Map<String, String[] > paramMap = request.getParameterMap();
			for (String param: paramMap.keySet()) {
				String[] paramValues = paramMap.get(param);
				for (int i = 0; i<paramValues.length; i++) {
					System.out.println(paramValues[i]);
					if (stripXSS(paramValues[i]) || stripSQL(paramValues[i])) {
						System.out.println("After checking");
						request.setAttribute("unsafe_check", "true");
						break;
					}
				}

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
		System.out.println("In Post Handle");
	}

	@Override
	public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
		System.out.println("In After Completion");
	}

	private boolean stripXSS(String value) {
		if (value != null) {
			return (value.matches("<script>(.*?)</script>") || value.matches("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'") || value.matches("\"<script(.*?)>\""));

		}
		return false;
	}

	private boolean stripSQL(String sql) {
		if (sql != null) {
			return (sql.contains(";") || sql.contains("'") || sql.contains("--") || sql.contains("/*") || sql.contains("*/") || sql.contains("xp_"));
		}
		return false;
	}

}