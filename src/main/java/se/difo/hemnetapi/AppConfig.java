package se.difo.hemnetapi;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import se.difo.hemnetapi.api.filter.ApiMessageLoggingFilter;

@Configuration
@EnableCaching
@EnableJpaRepositories
public class AppConfig {

    @Bean
    public FilterRegistrationBean getFilterRegistrationBean() {
        ApiMessageLoggingFilter loggingFilter = new ApiMessageLoggingFilter();
        FilterRegistrationBean<ApiMessageLoggingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(loggingFilter);
        registration.addUrlPatterns("/api/*"); // only log HTTP messages on the external REST API
        return registration;
    }


}
