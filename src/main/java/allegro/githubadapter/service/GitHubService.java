package allegro.githubadapter.service;

import allegro.githubadapter.config.CacheConfig;
import allegro.githubadapter.domain.GitHubRepositoryInformation;
import allegro.githubadapter.service.api.GitHubApi;
import allegro.githubadapter.service.exception.GitHubRepositoryInformationNotRetrived;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class GitHubService {

    private final GitHubApi gitHubApi;

    /**
     * @param owner Owner of the Github Repository
     * @param name Name of the Github Repository
     * @return UserRepoInformationFromGithub - information about the Repository
     * @throws GitHubRepositoryInformationNotRetrived Service throws exception when information about repository
     *         couldn't be fetched. The reason for this could be exceeded daily request rate.
     */
    @Async
    @Cacheable(CacheConfig.USER_REPOS_CACHE)
    public CompletableFuture<GitHubRepositoryInformation> fetchGitHubRepository(String owner, String name) throws GitHubRepositoryInformationNotRetrived {

        GitHubRepositoryInformation result = null;
        try {
            result = gitHubApi.loadUserRepository(owner, name).execute().body();
        } catch (IOException e) {
            log.warn("Check connection. Couldn't connect to GitHub API: {}", e);
        }

        if (result != null && result.getName() != null) {
            return CompletableFuture.completedFuture(result);

        } else {
            log.warn("null response from GitHub for: {}/{}", owner, name);
            throw new GitHubRepositoryInformationNotRetrived();
        }
    }

}
