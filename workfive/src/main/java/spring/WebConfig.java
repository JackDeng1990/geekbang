package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 基于Java的Bean装配
 * 使用 @Bean 注解将方法返回的实例对象添加到上下文中
 * 在@Bean返回的实例对象中可以通过构造器注入传入相关依赖
 * @author admin
 *
 */
@Configuration
@ComponentScan("spring")
public class WebConfig {
    @Bean
    public User user() {
        return new User(myArticle());
    }

    @Bean
    public MyArticle myArticle() {
        return new MyArticle();
    }
}
