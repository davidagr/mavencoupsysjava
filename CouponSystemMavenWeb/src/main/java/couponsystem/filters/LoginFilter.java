//package couponsystem.filters;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//@WebFilter("/secure/*")
//public class LoginFilter implements Filter {
//
//	private HttpSession session;
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
//			throws IOException, ServletException {
//
//		HttpServletRequest req = (HttpServletRequest) request;
//
//		session = req.getSession(false);
//		Object sessionUser = session.getAttribute("user");
//
//		if (session == null || sessionUser == null) {
//			return;
//		}
//
//		filterChain.doFilter(request, response);
//	}
//
//}
