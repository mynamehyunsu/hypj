package com.example.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration//등록
@EnableWebMvc//활성화
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        configureViewResolvers();
        registry.addResourceHandler("/IMG/**")///IMG/**로 시작하는 요청이 들어오게 되면
                .addResourceLocations("file:///C:/upload/");//file:///패턴으로 C:/upload/로 요청을 바꿔 전달한다
        registry.addResourceHandler("/css/**",
                        "/img/**",
                        "/js/**")
                .addResourceLocations("classpath:/static/css/",
                        "classpath:/static/img/",
                        "classpath:/static/js/");


//                .setCachePeriod(20);
//                .setCachePeriod(60 * 60 * 24 * 365);//캐쉬1년동안안바뀜
//        registry.addResourceHandler("/resources/**/")
//                .addResourceLocations("classpath:/static/img/")
//                .setCachePeriod(60 * 60 * 24 * 365);
//        registry.addResourceHandler("/resources/**/")
//                .addResourceLocations("classpath:/static/js/")
//                .setCacheControl(CacheControl.noCache().cachePrivate());
    }



    //    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.
//
//    }

    //DispacherServlet의 매핑 경로를 /로 줄 때 JSP/HTML/CSS
    //올바르게 처리를 위하여 configureDefaultServletHandling을 설정합니다.
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
}
