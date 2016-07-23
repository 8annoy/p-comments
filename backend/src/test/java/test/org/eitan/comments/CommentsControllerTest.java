package test.org.eitan.comments;

import com.google.gson.Gson;
import org.eitan.comments.Comment;
import org.eitan.comments.CommentsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.EndsWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.endsWith;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppTestContext.class})
@WebAppConfiguration
public class CommentsControllerTest {

    private static final Comment COMMENT1 =
            Comment.builder().id("123").email("w.w@gmail.com").content("kuku").build();
    private static final Comment COMMENT2 =
            Comment.builder().id("6545").email("q.q@gmail.com").content("popov").build();
    private static final Comment COMMENT_WITHOUT_ID = Comment.builder()
            .email("e.e@gmail.com")
            .content("popov").build();
    private static final String COMMENTS_PATH = "/api/v1/comments";
    private static final String COMMENT_ID = "2323423";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private CommentsRepository commentsRepository;

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    public void shouldRetrieveAllComments() throws Exception {
        List<Comment> allComments = Arrays.asList(COMMENT1, COMMENT2);
        Sort commentsSorting = new Sort(new Sort.Order(Sort.Direction.DESC, "timestamp"));
        when(commentsRepository.findAll(commentsSorting)).thenReturn(allComments);
        mockMvc.perform(get(COMMENTS_PATH))
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(allComments)));
    }

    @Test
    public void shouldDelegateCommentToRepository() throws Exception {
        Comment commentWithId = createCommentWithIdFrom(COMMENT_WITHOUT_ID);
        when(commentsRepository.save(COMMENT_WITHOUT_ID)).thenReturn(commentWithId);
        mockMvc.perform(post(COMMENTS_PATH)
                .content(new Gson().toJson(COMMENT_WITHOUT_ID))
                .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", new EndsWith(COMMENT_ID)))
                .andExpect(content().json(new Gson().toJson(commentWithId)));

    }

    private Comment createCommentWithIdFrom(Comment commentWithoutId) {
        return Comment.builder()
                .id(COMMENT_ID)
                .email(commentWithoutId.getEmail())
                .content(commentWithoutId.getEmail()).build();
    }


}