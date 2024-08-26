package shop.mtcoding.blog.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.mtcoding.blog.core.interceptor.Logininterceptor;

// 설정은 configuration으로 띄운다 IoC에 저장됨
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //특정한 곳에서 할 수 있게. 해서 추가해줌
        ///user, /board 때만 사용됨
        registry.addInterceptor(new Logininterceptor())
                .addPathPatterns("/api/**");
    }
}
