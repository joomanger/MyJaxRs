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

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");

        if (encodingParam != null) {
            encoding = encodingParam;
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getSession(false).getAttribute("access") == null) {
//            HttpServletResponse httpResponse = (HttpServletResponse) response;
//            httpResponse.sendRedirect("");
            System.out.println("Access denied");
        } else {
            System.out.println("Access allowed");
        }

        chain.doFilter(request, response);
    }

}
