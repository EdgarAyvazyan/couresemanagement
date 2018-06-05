package am.mainserver.coursemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ImportAutoConfiguration(classes = {JerseyConfig.class, PersistenceConfig.class})
@ComponentScan(basePackages = {"am.mainserver.coursemanagement"})
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
