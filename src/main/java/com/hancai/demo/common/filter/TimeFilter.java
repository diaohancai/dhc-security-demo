package com.hancai.demo.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * 记录 rest api 服务耗时 过滤器
 *
 * @author diaohancai
 */
public class TimeFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(TimeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("time filter start");

        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();

        log.info("time filter end, cost " + (end-start) + "ms");
    }

    @Override
    public void destroy() {
        log.info("time filter destory");
    }
}
