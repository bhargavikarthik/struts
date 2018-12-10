package org.superbiz.struts;

import com.opensymphony.module.sitemesh.filter.PageFilter;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

@SpringBootApplication
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean filterDispatchRegistrationBean(){

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.addInitParameter("actionPackages","com.lq");
        registrationBean.setName("struts2");
        registrationBean.setFilter(new FilterDispatcher());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean actionContextRegistrationBean(){

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("struts-cleanup");
        registrationBean.setFilter(new ActionContextCleanUp());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean pageFilterRegistrationBean(){

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("sitemesh");
        registrationBean.setFilter(new PageFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(3);

        return registrationBean;
    }

}