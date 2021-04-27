package com.lingnan.sas.client.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author 19399.
 * @date 2021/3/25.
 * @time 0:29
 */
public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
