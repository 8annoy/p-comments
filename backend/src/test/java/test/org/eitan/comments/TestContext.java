package test.org.eitan.comments;

import org.eitan.comments.CommentsRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestContext {

    @Bean
    public CommentsRepository eventsRepository() {
        return mock(CommentsRepository.class);
    }

}
