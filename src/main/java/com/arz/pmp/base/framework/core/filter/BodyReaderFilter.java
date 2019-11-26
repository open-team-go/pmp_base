package com.arz.pmp.base.framework.core.filter;

import com.arz.pmp.base.framework.core.bean.BodyReaderHttpServletRequestWrapper;
import com.arz.pmp.base.framework.core.utils.WebUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * description 获取请求流并缓存
 * 
 * @author chen wei
 * @date 2019/11/12
 */

public class BodyReaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {

        ServletRequest requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {

            if (!WebUtil.isMultipart(((HttpServletRequest)servletRequest))) {
                requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest)servletRequest);
            }
        }
        if (requestWrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
