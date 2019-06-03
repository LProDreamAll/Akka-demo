package fakepanshi.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Copyright (C), 2019-2019
 * FileName: SwaggerConfig
 * Author:   s·D·bs
 * Date:     2019/5/31 16:20
 * Description: Swagger配置
 * Motto: 0.45%
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error"))).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("s·D·bs APIs")
                .description("fake磐石")
                .contact(new Contact("北京车车科技有限公司", "https://www.cheche365.com/", "hanghangli1993@gmail.com"))
                .version("1.0")
                .build();
    }
}
