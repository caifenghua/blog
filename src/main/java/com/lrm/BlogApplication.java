package com.lrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

import javax.swing.*;

@SpringBootApplication
public class BlogApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder application){
        return application.sources(BlogApplication.class);
    };

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
