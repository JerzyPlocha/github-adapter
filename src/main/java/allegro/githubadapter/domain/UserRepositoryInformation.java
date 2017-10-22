package allegro.githubadapter.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
@JsonSerialize(using = UserRepositoryInformationSerializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRepositoryInformation {


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

