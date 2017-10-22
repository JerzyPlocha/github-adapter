package allegro.githubadapter.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import java.io.IOException;
import java.util.Date;

public class UserRepositoryInformationSerializer extends JsonSerializer {
    @Override
    public void serialize(Object repo, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", ((UserRepositoryInformation) repo).getName());
        jsonGenerator.writeStringField("description", ((UserRepositoryInformation) repo).getDescription());
        jsonGenerator.writeStringField("url", ((UserRepositoryInformation) repo).getUrl());
        jsonGenerator.writeNumberField("stars", ((UserRepositoryInformation) repo).getStars());
        jsonGenerator.writeStringField("createdAt", asISO(((UserRepositoryInformation) repo).getCreatedAt()));
        jsonGenerator.writeEndObject();
    }

    private String asISO(Date date) {
        return new ISO8601DateFormat().format(date);
    }
}