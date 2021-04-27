package com.lingnan.sas.client.config.shiro;


import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jdk.nashorn.internal.parser.Token;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author 19399.
 * @date 2021/3/25.
 * @time 11:45
 */
@Component
@Scope("prototype")
public class OAuth2Filter extends AuthenticatingFilter {
    //媒介类
    @Autowired
    private ThreadLocalToken threadLocalToken;

    @Value("${sas.jwt.cache-expire}")
    private int cacheExpire;

    //校验令牌有效性
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = getRequestToken(req);
        if (StrUtil.isBlank(token)) {
            return null;
        }
        return new OAuth2Token(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        //如果是option请求则用shiro框架处理
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        return false;
    }

    //上面需要shiro处理的
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        //设置允许跨域
        resp.setHeader("Acess-Control-Allow-Credentials", "true");
        resp.setHeader("Acess-Control-Allow-Origin", req.getHeader("Origin"));
        threadLocalToken.clear();

        String token = getRequestToken(req);
        //判断客户端令牌是否为空
        if (StrUtil.isBlank(token)) {
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            resp.getWriter().print("无效的令牌");
            return false;
        }
        try {
            jwtUtil.verifierToken(token);
        } catch (TokenExpiredException e) {
            //客户端令牌过期但服务端未过期
            if (redisTemplate.hasKey(token)) {
                //令牌刷新
                redisTemplate.delete(token);
                int userId = jwtUtil.getUserId(token);
                token = jwtUtil.createToken(userId);
                //TimeUnit.DAYS单位为天
                redisTemplate.opsForValue().set(token,userId+"",cacheExpire, TimeUnit.DAYS);
                threadLocalToken.setToken(token);
            }else{
                //服务端也没有令牌
                resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
                resp.getWriter().print("令牌已过期");
                return false;
            }
        } catch (Exception e) {
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            resp.getWriter().print("令牌已过期");
            return false;
        }
        //执行认证或授权失败都会返回false
        boolean bool = executeLogin(request,response);
        return bool;
    }

    //shiro中判定用户没登录或登录失败了,为认证失败
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        //设置允许跨域
        resp.setHeader("Acess-Control-Allow-Credentials", "true");
        resp.setHeader("Acess-Control-Allow-Origin", req.getHeader("Origin"));
        resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
        try {
            resp.getWriter().print(e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //掌管拦截请求，放行请求和响应的方法，对应shiro那里
    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        super.doFilterInternal(request, response, chain);
    }

    private String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            request.getParameter("token");
        }
        return token;
    }
}
