package org.example.rest;

import org.example.rest.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class Communication {

    private RestTemplate restTemplate;
    private String sessionID;
    private String URL = "http://94.198.50.185:7081/api/users";

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> entity = restTemplate.exchange(
                URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {});
        List<User> allUsers = entity.getBody();
        sessionID = entity.getHeaders().getFirst("Set-Cookie");
        for (String realSessionId : sessionID.split(";")) {
            System.out.println(realSessionId);
            sessionID = realSessionId;
            break;
        }
        return allUsers;
    }

    public String saveUser(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionID);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(user, headers);
        return restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();
    }

    public String updateUser(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionID);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<User>(user, headers);
        return restTemplate.exchange(URL , HttpMethod.PUT, entity, String.class).getBody();
    }

    public String deleteUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionID);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URL + "/" + 3, HttpMethod.DELETE, entity, String.class).getBody();
    }
}
