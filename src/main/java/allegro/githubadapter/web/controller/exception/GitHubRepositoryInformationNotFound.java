package allegro.githubadapter.web.controller.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class GitHubRepositoryInformationNotFound extends RuntimeException {

}
