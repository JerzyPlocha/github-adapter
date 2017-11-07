package allegro.githubadapter.web.controller;

import allegro.githubadapter.domain.GitHubRepositoryInformation;
import allegro.githubadapter.service.GitHubService;
import allegro.githubadapter.service.exception.GitHubRepositoryInformationNotRetrived;
import allegro.githubadapter.web.controller.exception.GitHubRepositoryInformationNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GitHubRepositoryInformationController {

    private final GitHubService gitHubService;

    @GetMapping(value = "/repositories/{owner}/{repositoryName}")
    public ResponseEntity<GitHubRepositoryInformation> getUserRepositoryInformation(@PathVariable String owner, @PathVariable String repositoryName) throws GitHubRepositoryInformationNotRetrived {

        Response<GitHubRepositoryInformation> response = gitHubService.fetchGitHubRepository(owner, repositoryName);

        if (response.code() >= 400 && response.code() < 500) {
            throw new GitHubRepositoryInformationNotFound();
        }

        if (!response.isSuccessful()) {
            throw new GitHubRepositoryInformationNotRetrived();
        }

        return new ResponseEntity<>(response.body(), HttpStatus.OK);
    }

    @ExceptionHandler(GitHubRepositoryInformationNotFound.class)
    public String handleUserRepositoryNotFound() {
        return "User or repository not found.";
    }

    @ExceptionHandler(GitHubRepositoryInformationNotRetrived.class)
    public String handleUserRepositoryNotRetrived() {
        return "We couldn't retrieve this user or repository";
    }
}
