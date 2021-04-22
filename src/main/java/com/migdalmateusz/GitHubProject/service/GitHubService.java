package com.migdalmateusz.GitHubProject.service;

import com.migdalmateusz.GitHubProject.model.GitHubDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubService {

    private RestTemplate restTemplate = new RestTemplate();

    public GitHubDto[] getGitHub(String user) {
        ResponseEntity<GitHubDto[]> response =
                restTemplate.getForEntity(
                        "https://api.github.com/users/" + user + "/repos?per_page=100",
                        GitHubDto[].class);
        return response.getBody();
    }

}


