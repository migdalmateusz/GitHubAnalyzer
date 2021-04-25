package com.migdalmateusz.githubproject.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/users/{userName}/repos")
    public ResponseEntity<Repos> getRepositoryDetails(@PathVariable (value = "userName") String userName) {
        log.info("> Get repository details called with userName={}", userName);
        final var repository = gitHubService.getRepositoryDetails(userName);
        final ResponseEntity<Repos> response = ResponseEntity.ok(repository);
        log.info("< Finished with response={}", response);
        return response;
    }
}
