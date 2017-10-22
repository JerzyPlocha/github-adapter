package allegro.githubadapter.domain.serializer;

import allegro.githubadapter.domain.GitHubRepositoryInformation;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import java.io.IOException;
import java.util.Date;

public class GitHubRepositoryInformationSerializer extends JsonSerializer {
    @Override
    public void serialize(Object repo, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", ((GitHubRepositoryInformation) repo).getName());
        jsonGenerator.writeStringField("description", ((GitHubRepositoryInformation) repo).getDescription());
        jsonGenerator.writeStringField("url", ((GitHubRepositoryInformation) repo).getUrl());
        jsonGenerator.writeNumberField("stars", ((GitHubRepositoryInformation) repo).getStars());
        jsonGenerator.writeStringField("createdAt", asISO(((GitHubRepositoryInformation) repo).getCreatedAt()));
        jsonGenerator.writeEndObject();
    }

    private String asISO(Date date) {
        return new ISO8601DateFormat().format(date);
    }
}