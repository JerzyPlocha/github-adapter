package allegro.githubadapter.web.controller;

import allegro.githubadapter.GithubAdapterApplication;
import allegro.githubadapter.domain.GitHubRepositoryInformation;
import allegro.githubadapter.service.GitHubService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import retrofit2.Response;

import java.nio.charset.Charset;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GithubAdapterApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class GitHubRepositoryInformationControllerTest {

    public static final String TEST_STRING = "abc";

    @Autowired
    private MockMvc mockMvc;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @MockBean
    private GitHubService gitHubService;

    @Test
    public void shouldRetrunStatusOKforExistingUserRepository() throws Exception {

        GitHubRepositoryInformation repo = new GitHubRepositoryInformation();
        repo.setName(TEST_STRING);
        repo.setDescription(TEST_STRING);
        repo.setCreatedAt(new Date(1510181947035L));
        repo.setStars(0);
        repo.setUrl(TEST_STRING);

        Response<GitHubRepositoryInformation> response = Response.success(repo);

        when(gitHubService.fetchGitHubRepository(TEST_STRING, TEST_STRING)).thenReturn(response);

        MvcResult result = mockMvc.perform(get("/repositories/abc/abc")
                .contentType(contentType)
                .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andReturn();

        assertEquals("{\"name\":\"abc\",\"description\":\"abc\",\"url\":\"abc\",\"stars\":0,\"createdAt\":\"2017-11-08T22:59:07Z\"}", result.getResponse().getContentAsString());

        Mockito.verify(gitHubService, times(1)).fetchGitHubRepository(TEST_STRING, TEST_STRING);
    }
}

