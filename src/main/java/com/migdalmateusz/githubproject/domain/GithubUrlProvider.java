package com.migdalmateusz.githubproject.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class GithubUrlProvider {

	private final String host;
	private final String param;

	public GithubUrlProvider(@Value("${github.host}")String host, @Value("${github.param}")String param) {
		this.host = host;
		this.param = param;
	}

	public URI getRepositoryDetailsUrl(final String user) {
		return UriComponentsBuilder.fromUriString(host)
				.pathSegment("users", user, "repos")
				.query(param)
				.build()
				.toUri();
	}
}
