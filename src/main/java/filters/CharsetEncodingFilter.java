/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author savin
 */
public class CharsetEncodingFilter implements Filter {
    
    private String encoding = "utf-8";

    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        System.out.println("filter init");
        if (encodingParam != null) {
            encoding = encodingParam;
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("doFilter handleNavigation="+req.getSession(false).getAttribute("handleNavigation"));
        HttpServletResponse res = (HttpServletResponse) response;
        if ((req.getSession(false).getAttribute("handleNavigation") == null)&&(req.getSession(false).getAttribute("doFilter")==null)) {
            //req.getSession(false).setAttribute("handleNavigation", "true");
            req.getSession(false).setAttribute("doFilter","true");
            res.sendRedirect("faces/index.xhtml");
        }
        chain.doFilter(request, response);
        req.getSession(false).setAttribute("handleNavigation",null);
    }

}
