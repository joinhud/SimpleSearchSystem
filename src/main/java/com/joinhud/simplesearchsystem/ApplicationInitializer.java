package com.joinhud.simplesearchsystem;

import com.joinhud.simplesearchsystem.config.DatabaseConfig;
import com.joinhud.simplesearchsystem.config.SecurityConfig;
import com.joinhud.simplesearchsystem.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { DatabaseConfig.class, WebConfig.class, SecurityConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }

}
