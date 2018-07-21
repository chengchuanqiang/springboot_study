package com.ccq.springbootkafka;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class SpringbootkafkaApplication extends WebMvcConfigurerAdapter {

    @Value("${spring.profiles.active}")
    private String activeProfiles;

    @PostConstruct
    private void afterInit() {
        System.out.println("----------it works -----------------");
        System.out.println("Application is Started With Active Profile is : [" + activeProfiles + "]");
        System.out.println("--------------------------------------------");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        // 初始化转换器
        FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
        // 初始化一个转换器配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 将配置设置给转换器并添加到HttpMessageConverter转换器列表中
        fastConvert.setFastJsonConfig(fastJsonConfig);

        converters.add(fastConvert);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootkafkaApplication.class, args);
    }
}
