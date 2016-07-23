package test.org.eitan.comments;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.eitan.comments"},
        excludeFilters = @ComponentScan.Filter(value = Repository.class, type = FilterType.ANNOTATION)
)
public class WebAppTestContext extends WebMvcConfigurerAdapter {

}
