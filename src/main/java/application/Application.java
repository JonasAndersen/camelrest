package application;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
//@EnableAutoConfiguration
@ComponentScan({"camel","database.services"})
@EntityScan("database.entities")
@EnableJpaRepositories("database.repositories")

public class Application extends RouteBuilder {

    public static void main(String args[]){
        SpringApplication.run(Application.class, args);

    }

    @Bean
    public ServletRegistrationBean camelServletRegistrationBean(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/api/*");
        registrationBean.setName("CamelServlet");
        return registrationBean;
    }


    @Override
    public void configure(){

    }
}
