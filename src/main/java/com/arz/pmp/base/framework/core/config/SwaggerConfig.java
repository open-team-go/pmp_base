package com.arz.pmp.base.framework.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.base.Predicate;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket openApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                // 只有添加了ApiOperation注解的method才在API中显示
                // return input.isAnnotatedWith(ApiOperation.class);
                return input.getHandlerMethod().hasMethodAnnotation(ApiOperation.class);
            }
        };

        return new Docket(DocumentationType.SWAGGER_2).groupName("openApi")
            .genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false).forCodeGeneration(false)
            .select().apis(predicate)
            // 过滤的接口
            .paths(PathSelectors.any())
            // 过滤的接口
            // .paths(predicatePath)
            .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("api文档").description("restfun 风格接口")
            // 服务条款网址
            // .termsOfServiceUrl("http://blog.csdn.net/forezp")
            .version("1.0").description("接口文档").build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowCredentials(true);
    }
}
