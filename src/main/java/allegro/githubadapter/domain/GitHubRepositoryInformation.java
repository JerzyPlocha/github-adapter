package allegro.githubadapter.domain;

import allegro.githubadapter.domain.serializer.GitHubRepositoryInformationSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
@JsonSerialize(using = GitHubRepositoryInformationSerializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubRepositoryInformation {


    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @SerializedName("stargazers_count")
    private Integer stars;

    @SerializedName("created_at")
    private Date createdAt;
}

