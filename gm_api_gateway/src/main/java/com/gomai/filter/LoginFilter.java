package com.gomai.filter;

import com.gomai.auth.utils.JwtUtils;
import com.gomai.config.FilterProperties;
import com.gomai.config.JwtProperties;
import com.gomai.utils.CookieUtils;
import com.gomai.utils.ReturnMessageUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author jl
 * @description
 * @date 2019-11-06 14:05
 */
@Component
public class LoginFilter extends ZuulFilter {
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private FilterProperties filterProperties;
    /**
    pre：请求在被路由之前执行
    routing：在路由请求时调用
    post：在routing和errror过滤器之后调用
    error：处理请求时发生错误调用
    */
    @Override
    public String filterType() {
        return "pre";
    }

    //过虑器序号，越小越被优先执行
    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        //白名单校验
        List<String> allowPaths = filterProperties.getAllowPaths();
        //初始化运行上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到request
        HttpServletRequest request = requestContext.getRequest();
        //获取请求路径(带域名的路径)
        String url = request.getRequestURL().toString();
        System.out.println(url);
        for (String allowPath : allowPaths) {
            if (StringUtils.contains(url, allowPath)) {
                return false;
            }
        }
        System.out.println("wwwww");
        //返回true表示要执行此过虑器
        return true;
    }
    //过虑器的内容
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到request
        HttpServletRequest request = requestContext.getRequest();

        String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());
        if (StringUtils.isEmpty(token)) {
            //拒绝访问
            requestContext.setSendZuulResponse(false);
            //设置响应代码
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

            return ReturnMessageUtil.error(101,"登录过期");
        }
        try {
            JwtUtils.getInfoFromToken(token,jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            //拒绝访问
            requestContext.setSendZuulResponse(false);
            //设置响应代码
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return ReturnMessageUtil.error(101,"登录过期");
    }

}
