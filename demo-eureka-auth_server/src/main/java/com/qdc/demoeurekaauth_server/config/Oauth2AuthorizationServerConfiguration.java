package com.qdc.demoeurekaauth_server.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Configuration
public class Oauth2AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DruidDataSource druidDataSource;
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //这对于access token认证很重要，只有具有 ROLE_TRUSTED_CLIENT权限的客户端才可以通过认证
        //所以要将表 oauth_client_details中客户端记录的authorities字段设置为ROLE_TRUSTED_CLIENT
        //这样才能使客户端满足要求
        security.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //数据库管理客户端应用，从dataSource配置的数据源中读取客户端数据
        //客户端数据都保存在表 oauth_client_details中
        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
    }

    //配置认证服务器的非安全属性，总之一切都通过数据库管理
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //用户信息查询服务
        endpoints.userDetailsService(userDetailsService);
        //数据库管理access token和refresh token
        TokenStore tokenStore=new JdbcTokenStore(dataSource);
        //endpoints.tokenStore(tokenStore);
        DefaultTokenServices tokenServices=new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(new JdbcClientDetailsService(dataSource));
        tokenServices.setAccessTokenValiditySeconds(38000);
        //tokenServices.setRefreshTokenValiditySeconds(180);
        endpoints.tokenServices(tokenServices);
        //数据库管理授权码
        endpoints.authorizationCodeServices(new JdbcAuthorizationCodeServices(dataSource));
        //数据库管理授权信息
        ApprovalStore approvalStore=new JdbcApprovalStore(dataSource);
        endpoints.approvalStore(approvalStore);
    }
}
