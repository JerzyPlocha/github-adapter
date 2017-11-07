package allegro.githubadapter.service;

import allegro.githubadapter.config.CacheConfig;
import allegro.githubadapter.domain.GitHubRepositoryInformation;
import allegro.githubadapter.service.api.GitHubApi;
import allegro.githubadapter.service.exception.GitHubRepositoryInformationNotRetrived;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class GitHubService {

    private final GitHubApi gitHubApi;

    /**
     * @param owner Owner of the Github Repository
     * @param name  Name of the Github Repository
     * @return UserRepoInformationFromGithub - information about the Repository
     * @throws GitHubRepositoryInformationNotRetrived Service throws exception when information about repository
     *                                                couldn't be fetched. The reason for this could be exceeded daily request rate.
     */
    @Cacheable(CacheConfig.USER_REPOS_CACHE)
    public Response<GitHubRepositoryInformation> fetchGitHubRepository(String owner, String name) throws GitHubRepositoryInformationNotRetrived {

        Call<GitHubRepositoryInformation> repoInfoCall = gitHubApi.loadUserRepository(owner, name);

        try {
            return repoInfoCall.execute();
        } catch (IOException e) {
            throw new GitHubRepositoryInformationNotRetrived("Check connection.", e);
        }
    }

}
