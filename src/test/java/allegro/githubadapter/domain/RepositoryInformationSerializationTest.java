package allegro.githubadapter.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;

public class RepositoryInformationSerializationTest {

    @Test
    public void shouldMapToJSON() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Writer writer = new StringWriter();

        GitHubRepositoryInformation repositoryInformation = new GitHubRepositoryInformation();
        repositoryInformation.setName("name1");
        repositoryInformation.setDescription("desc1");
        repositoryInformation.setUrl("https://api.github.com/repos/10/10");
        repositoryInformation.setStars(900);
        repositoryInformation.setCreatedAt(new ISO8601DateFormat().parse("2011-03-24T22:05:14Z"));

        mapper.writeValue(writer, repositoryInformation);
        String actual = writer.toString();

        String expected = "{\n" +
                "  \"name\" : \"name1\",\n" +
                "  \"description\" : \"desc1\",\n" +
                "  \"url\" : \"https://api.github.com/repos/10/10\",\n" +
                "  \"stars\" : 900,\n" +
                "  \"createdAt\" : \"2011-03-24T22:05:14Z\"\n" +
                "}";

        Assert.assertEquals(expected, actual);
    }
}
