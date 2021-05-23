package org.javaboy.vhr.config;


import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value(value = "${server.port}")
    private String serverPort;

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描端口
                .apis(RequestHandlerSelectors.basePackage("org.javaboy.vhr"))
                .paths(PathSelectors.any())
                .build()
                .enable(enableSwagger);
    }

//    @Bean
//    public Docket jump() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(jumpInfo())
//                .select()
//                //扫描端口
//                .apis(RequestHandlerSelectors.basePackage("org.javaboy.vhr"))
//                .paths(PathSelectors.ant("/salary/**"))
//                .build()
//                .groupName("工资")
//                .enable(enableSwagger);
//    }

    @Bean
    public Docket system() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(systemInfo())
                .select()
                //扫描端口
                .apis(RequestHandlerSelectors.basePackage("org.javaboy.vhr"))
                //.paths(PathSelectors.ant("/system/**"))
                .paths(Predicates.or(PathSelectors.ant("/system/**"),PathSelectors.ant("/salary/**")))
                .build()
                .groupName("系统")
                .enable(enableSwagger);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-bootstrap-ui RESTful APIs")
                .description("swagger-bootstrap-ui")
                .termsOfServiceUrl("http://127.0.0.1:"+serverPort+"/")
                .contact("ceshi@mail.com")
                .version("1.0")
                .build();
    }

    private ApiInfo jumpInfo() {
        return new ApiInfoBuilder()
                .title("swagger-bootstrap-ui RESTful APIs")
                .description("swagger-bootstrap-ui")
                .termsOfServiceUrl("http://www.flydolphin.fun")
                .contact("ceshi@mail.com")
                .version("1.0")
                .build();
    }

    private ApiInfo systemInfo() {
        return new ApiInfoBuilder()
                .title("swagger-bootstrap-ui RESTful APIs")
                .description("swagger-bootstrap-ui")
                .termsOfServiceUrl("http://www.flydolphin.fun")
                .contact("ceshi@mail.com")
                .version("1.0")
                .build();
    }
}