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
    public void serialize(Object repoObject, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        GitHubRepositoryInformation repo = (GitHubRepositoryInformation) repoObject;
        jsonGenerator.writeStringField("name", repo.getName());
        jsonGenerator.writeStringField("description", repo.getDescription());
        jsonGenerator.writeStringField("url", repo.getUrl());
        jsonGenerator.writeNumberField("stars", repo.getStars());
        jsonGenerator.writeStringField("createdAt", asISO(repo.getCreatedAt()));
        jsonGenerator.writeEndObject();
    }

    private String asISO(Date date) {
        return new ISO8601DateFormat().format(date);
    }
}