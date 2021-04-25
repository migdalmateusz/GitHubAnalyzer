package com.migdalmateusz.githubproject.domain;

import lombok.Builder;

@Builder
class Repos {

    private GitHubDto[] repoList;
    private int starsSum;

    public GitHubDto[] getRepoList() {
        return repoList;
    }

    public void setRepoList(GitHubDto[] repoList) {
        this.repoList = repoList;
    }

    public int getStarsSum() {
        return starsSum;
    }

    public void setStarsSum(int starsSum) {
        this.starsSum = starsSum;
    }
}
