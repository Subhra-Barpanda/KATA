package com.sg.employee.employeeservice.config;

import com.sg.employee.employeeservice.controller.EmployeeServiceController;
import com.sg.employee.employeeservice.exception.handler.ServiceExceptionMapper;
import com.sg.employee.employeeservice.exception.handler.ValidationExceptionMapper;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class EmployeeConfig extends ResourceConfig {

    @Value("${spring.jersey.application-path:/}")
    private String apiPath;

    @PostConstruct
    public void register() {
        registerControllers();
        registerSwaggerEndPoint();
        registerProviders();
    }

    private void registerSwaggerEndPoint() {
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setConfigId("employee-service-api");
        beanConfig.setTitle("Employee Service API");
        beanConfig.setVersion("v1");
        beanConfig.setContact("Subhra Barpanda");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath(this.apiPath);
        beanConfig.setResourcePackage("com.sg.employee.employeeservice.controller");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
    }

    private void registerProviders() {
        register(ServiceExceptionMapper.class);
        register(ValidationExceptionMapper.class);
    }

    private void registerControllers() {
        register(EmployeeServiceController.class);
    }
}
