package com.migdalmateusz.githubproject.model;

public class GitHubDto {
    private String name;
    private Integer size;
    private Integer stargazers_count;

    public String getName() {
        return name;
    }

    public Integer getStargazers_count() {
        return stargazers_count;
    }

    public Integer getSize() {
        return size;
    }
}