package com.migdalmateusz.GitHubProject.controller;

import com.migdalmateusz.GitHubProject.model.GitHubDto;
import com.migdalmateusz.GitHubProject.service.GitHubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/github/{userName}")
    public String getGitHub(@PathVariable (value = "userName") String userName, Model model) {
        GitHubDto[] gitHubDto = gitHubService.getGitHub(userName);
        int starSum = 0;
        for (GitHubDto entry : gitHubDto) {
            starSum = starSum + entry.getStargazers_count();
        }
        model.addAttribute("stars", starSum);
        model.addAttribute("repo", gitHubDto);
        return "displayList";
    }
}
