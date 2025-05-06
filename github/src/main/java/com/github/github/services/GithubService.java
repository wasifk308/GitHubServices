package com.portfolio.portfolio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubService {

    private final RestTemplate restTemplate;
    @Autowired
    public GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${github.api.base_url}")
    private String gitHubApiBaseUrl;
    
    @Value("${github.token}")
    private String gitHubToken;

    public String getUser(String userName)
    {
        System.out.println(userName);
        String url = gitHubApiBaseUrl + "/users/" + userName;
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    public String getRepos(String userName)
    {
        String url = gitHubApiBaseUrl + "/users/" + userName + "/repos";
        return restTemplate.getForObject(url, String.class);
    }


}
