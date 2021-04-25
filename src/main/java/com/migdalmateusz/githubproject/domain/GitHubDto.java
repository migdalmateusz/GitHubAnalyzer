package com.migdalmateusz.githubproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

class GitHubDto {
    private String name;
    @JsonProperty("stargazers_count")
    private Integer stargazersCount;

    public String getName() {
        return name;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

}