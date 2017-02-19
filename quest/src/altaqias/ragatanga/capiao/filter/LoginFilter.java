package altaqias.ragatanga.capiao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import altaqias.ragatanga.capiao.beans.SessaoBean;

@WebFilter("/pages/*")
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		SessaoBean sessaoBean = (SessaoBean)((HttpServletRequest)request).getSession().getAttribute("SessaoBean");

		String url = ((HttpServletRequest)request).getRequestURI();
		
		if((url.contains("login")) && sessaoBean != null && sessaoBean.isLogado()){
			String contextPath = ((HttpServletRequest)request).getContextPath();
			((HttpServletResponse)response).sendRedirect(contextPath + "/home.jsf");
		}

		if(!url.contains("login") && !url.contains("vinculos")){
			if (sessaoBean == null || !sessaoBean.isLogado()) {
				String contextPath = ((HttpServletRequest)request).getContextPath();
				((HttpServletResponse)response).sendRedirect(contextPath + "/login.jsf");
			}else{
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}