package com.example.Spotify.Api;


import Model.SpotifyAlbum;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class SpotifyAlbumClient {


    @GetMapping("/album/{authorName}")
    public SpotifyAlbum getAlbumsByAuthor(OAuth2Authentication details , @PathVariable String authorName) {

       String token =  ((OAuth2AuthenticationDetails) details.getDetails()).getTokenValue();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer " + token );
        HttpEntity httpEntity = new HttpEntity(httpHeaders);


        ResponseEntity<SpotifyAlbum> exchange = restTemplate.exchange("https://api.spotify.com/v1/search?q="+ authorName + "&type=track&market=US&limit=10&offset=5",
               HttpMethod.GET,
               httpEntity, SpotifyAlbum.class);
                return  exchange.getBody();
    }

    private class URL {
    }
}