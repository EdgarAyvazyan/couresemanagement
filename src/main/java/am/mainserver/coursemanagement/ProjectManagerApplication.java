package am.mainserver.coursemanagement;

import am.mainserver.coursemanagement.web.MainRestController;
import am.mainserver.coursemanagement.web.UploadController;
import am.mainserver.coursemanagement.web.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan(basePackages = {"am.mainserver.coursemanagement"} ,basePackageClasses = {UploadController.class,MainRestController.class,UserController.class})
public class ProjectManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagerApplication.class, args);
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            container.setSessionTimeout(1000);  // session timeout value
        });
    }
}
