package allegro.githubadapter.domain;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;

public class RepositoryInformationDeserializationTest {


    @Test
    public void shouldMapJSvariablesToJavaVariableNotation() throws Exception {

        // given
        String JSON = "{\n" +
                "  \"name\" : \"name1\",\n" +
                "  \"description\" : \"desc1\",\n" +
                "  \"url\" : \"https://api.github.com/repos/10/10\",\n" +
                "  \"stargazers_count\" : 900,\n" +
                "  \"created_at\" : \"2011-03-24T22:05:14Z\"\n" +
                "}";
        String JSON1 = "{\n" +
                "  \"name\" : \"name1\",\n" +
                "  \"description\" : \"desc1\",\n" +
                "  \"url\" : \"https://api.github.com/repos/10/10\",\n" +
                "  \"stars\" : 900,\n" +
                "  \"createdAt\" : \"2011-03-24T22:05:14Z\"\n" +
                "}";

        // when
        Gson gson = new GsonBuilder().create();
        GitHubRepositoryInformation actual = gson.fromJson( JSON , GitHubRepositoryInformation.class);
        GitHubRepositoryInformation notExpected = gson.fromJson( JSON1 , GitHubRepositoryInformation.class);
        GitHubRepositoryInformation expected = new GitHubRepositoryInformation();
        expected.setName("name1");
        expected.setDescription("desc1");
        expected.setUrl("https://api.github.com/repos/10/10");
        expected.setStars(900);
        expected.setCreatedAt(new ISO8601DateFormat().parse("2011-03-24T22:05:14Z"));

        // then
        Assert.assertEquals(expected, actual);
    }
}
