package allegro.githubadapter.web.controller;

import allegro.githubadapter.domain.GitHubRepositoryInformation;
import allegro.githubadapter.service.GitHubService;
import allegro.githubadapter.web.controller.exception.GitHubRepositoryInformationNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GitHubRepositoryInformationController {

    private final GitHubService gitHubService;

    @GetMapping(value = "/repositories/{owner}/{repositoryName}")
    public GitHubRepositoryInformation getUserRepositoryInformation(@PathVariable String owner, @PathVariable String repositoryName) {
        try {
            return gitHubService.fetchGitHubRepository(owner, repositoryName).get();
        } catch (Exception e) {
            log.error("Couldn't retrieve info from GitHub API: {}, {}", owner, repositoryName);
            throw new GitHubRepositoryInformationNotFound();
        }
    }

    @ExceptionHandler(GitHubRepositoryInformationNotFound.class)
    public String handleUserRepositoryNotFound() {
        return "We couldn't retrieve this user or repository";
    }
}
