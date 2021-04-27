package com.lingnan.sas.client.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        // ApiInfoBuilder 用于在Swagger界面上添加各种信息
        ApiInfoBuilder builder = new ApiInfoBuilder();
        //设置标题
        builder.title("校园助手");
        ApiInfo info = builder.build();
        docket.apiInfo(info);

        // ApiSelectorBuilder 用来设置哪些类中的方法会生成到REST API中
        ApiSelectorBuilder selectorBuilder = docket.select();
        //表示所有包下面的所有类添加到swagger
        selectorBuilder.paths(PathSelectors.any());
        //使用@ApiOperation的方法会被提取到REST API中
        selectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
        /*
         * * 下面的语句是开启对JWT的支持，当用户用Swagger调用受JWT认证保护的方法，
         * 必须要先提交参数（例如令牌）
         */
        //存储用户必须提交的参数
        docket = selectorBuilder.build();

        ////规定用户需要输入什么参数
        //在请求头header接受客户端发起jwt请求上传的令牌，会在swagger中保存
        // name:文本框名字 keyname:往请求头写参数的参数名字 ，passAs:把参数写到请求头
        ApiKey apiKey = new ApiKey("token", "token", "header");
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(apiKey);
        //将令牌字符串放入swagger
        docket.securitySchemes(apiKeyList);

        //设置令牌的作用域
        //如果用户JWT认证通过，则在Swagger中全局有效
        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] scopes = {scope};
        //存储令牌和作用域
        SecurityReference reference = new SecurityReference("token", scopes);
        List refList = new ArrayList();
        refList.add(reference);
        SecurityContext context = SecurityContext.builder().securityReferences(refList).build();
        List cxtList = new ArrayList();
        cxtList.add(context);
        docket.securityContexts(cxtList);

        return docket;
    }
}
