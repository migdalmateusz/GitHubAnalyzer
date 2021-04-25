package com.migdalmateusz.githubproject.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
class GitHubService {

    private final RestTemplate restTemplate;
    private final GithubUrlProvider githubUrlProvider;

    public GitHubService(RestTemplate restTemplate, GithubUrlProvider githubUrlProvider) {
        this.restTemplate = restTemplate;
        this.githubUrlProvider = githubUrlProvider;
    }

    public Repos getRepositoryDetails(String userName) {
        log.debug(">> get repository details for userName={}", userName);
        final URI repositoryDetailsUrl = githubUrlProvider.getRepositoryDetailsUrl(userName);
        final ResponseEntity<GitHubDto[]> exchange = callGetRepositoryDetails(repositoryDetailsUrl);
        final GitHubDto[] githubRepository = exchange.getBody();
        if (githubRepository != null) {
            final var repositoryDetails = convertResponse(githubRepository);
            log.debug("<< got repositoryDetails={}", repositoryDetails);
            return repositoryDetails;
        }
        log.error("No body in response={}", exchange);
        throw new IllegalStateException(String.format("No body in response=%s", exchange));
    }

    private ResponseEntity<GitHubDto[]> callGetRepositoryDetails(URI repositoryDetailsUrl) {
        try {
            return restTemplate.exchange(repositoryDetailsUrl, HttpMethod.GET, createRequestEntity(),  GitHubDto[].class);
        } catch (HttpClientErrorException e) {
            log.error("Client error during connection to repository with url={}", repositoryDetailsUrl, e);
            throw new BadRequestException("Client error", e);
        } catch (RestClientException e) {
            log.error("Unexpected error occurred during connection to repository with url={}", repositoryDetailsUrl, e);
            throw new RepositoryInternalServerError("Unexpected error", e);
        }
    }

    private Repos convertResponse(GitHubDto[] gitHubDto) {
        return Repos.builder()
                .repoList(gitHubDto)
                .starsSum(sumStars(gitHubDto))
                .build();
    }

    private int sumStars(GitHubDto[] gitHubDto) {
        var starSum = 0;
        for (GitHubDto entry : gitHubDto) {
            starSum = starSum + entry.getStargazersCount();
        }
        return starSum;
    }

    private HttpEntity<String> createRequestEntity() {
        final var httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept", "application/vnd.github.v3+json");
        return new HttpEntity<>(httpHeaders);
    }
}


