package allegro.githubadapter.service.api;

import allegro.githubadapter.domain.GitHubRepositoryInformation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {

    @GET("/repos/{owner}/{repositoryName}")
    Call<GitHubRepositoryInformation> loadUserRepository(@Path("owner") String owner, @Path("repositoryName") String repositoryName);
}
