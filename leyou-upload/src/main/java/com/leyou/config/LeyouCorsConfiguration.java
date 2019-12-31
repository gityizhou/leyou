package com.leyou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class LeyouCorsConfiguration {

    // cors解决跨域问题，加入corsFilter
    // 在网关对跨域请求进行拦截处理

    @Bean
    public CorsFilter corsFilter() {
        //初始cors配置对象
        CorsConfiguration configuration = new CorsConfiguration();
        //允许跨域的域名，如果要携带cookie，不能写*， *代表所有域名，都可以跨域访问
        configuration.addAllowedOrigin("http://manage.leyou.com");
        configuration.setAllowCredentials(true); // 是否允许携带cookie
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");  // 允许携带任何头信息
        // 初始化CORS配置源
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(configurationSource);
    }

}
