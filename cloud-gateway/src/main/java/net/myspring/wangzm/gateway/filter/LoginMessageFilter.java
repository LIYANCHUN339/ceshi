package net.myspring.wangzm.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;

public class LoginMessageFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(LoginMessageFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if((ctx.getRequest().getRequestURI().contains("/user/"))) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        System.out.println("请求的url包含user:"+ctx.getRequest().getRequestURI());
        return null;
    }
}
