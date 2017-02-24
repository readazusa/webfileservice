package club.lovety.webfileservice.config;

import org.springframework.boot.autoconfigure.social.FacebookAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;

/**
 * Created by 念梓  on 2017/2/21.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
@Configuration
public class SpringConfig {

//    @Bean(value = "multipartResolver")
//    public CommonsMultipartResolver createCommonsMultipartResolver(){
//        CommonsMultipartResolver  commonsMultipartResolver = new CommonsMultipartResolver();
//        commonsMultipartResolver.setMaxUploadSize(10240);
//        return commonsMultipartResolver;
//    }

}
