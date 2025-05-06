package com.portfolio.portfolio.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.portfolio.portfolio.services.GithubService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.core.io.Resource;
@RestController
@RequestMapping("/api/github")
public class GitHubController {

    @Autowired
    private RestTemplateBuilder builder;
    

    private final GithubService githubService;

    public  GitHubController (GithubService githubService)
    {
        this.githubService = githubService;
    }


    @GetMapping("/users/{username}")
    public String getUser(@PathVariable String username) {
        return githubService.getUser(username);
    }


    @GetMapping("/users/{username}/repos")
    public String getRepos(@PathVariable String username) {
        return githubService.getRepos(username);
    }
    
 
    
@GetMapping("/download-repo")
public void downloadRepo(@RequestParam String url, HttpServletResponse response) throws IOException {
    RestTemplate restTemplate = builder.build();

    ResponseEntity<Resource> entity = restTemplate.exchange(
        url, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), Resource.class);

    response.setContentType("application/zip");
    response.setHeader("Content-Disposition", "attachment; filename=repository.zip");

    StreamUtils.copy(entity.getBody().getInputStream(), response.getOutputStream());
}
}
