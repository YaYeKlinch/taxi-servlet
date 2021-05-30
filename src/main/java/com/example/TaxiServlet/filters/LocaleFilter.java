package com.example.TaxiServlet.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    private static final String LOCALE = "locale";
    private static final String LANG = "language";
    private static final String EN = "en";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String locale = req.getParameter(LANG);
        final HttpSession session = req.getSession();
        if(session !=null){
            Object currentLocale = session.getAttribute(LOCALE);
            if (currentLocale==null){
                if (locale == null){
                    session.setAttribute(LOCALE , EN );
                }
                else {
                    session.setAttribute(LOCALE , locale);
                }
            }
            else if (locale != null && !currentLocale.toString().equals(locale)) {
                session.setAttribute(LOCALE, locale);
            }
        }

        filterChain.doFilter(req, res);

    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}