package com.example.client.service;

import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    /**
     * RestTemplate으로 get 호출하기
     */
    // http://localhost/api/server/hello
    // response
    public UserResponse hello() {
        // UriComponentBuilder로 주소 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                /*
                주소 뒤에 쿼리 파라미터가 붙는다. /hello?name=haejun&age=10
                .queryParam("name", "haejun")
                .queryParam("age", 10)
                */
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        // getForObject 사용 시 UserResponse 타입으로 받는다. 밑의 방법 추천.
        // server가 data를 줄 때 json을 보고 클래스 작성 후 RestTemplate으로 get 또는 post 형태로 주고 받는다. post일 때는 RequestBody로 보낸다.
        // getForEntity : http의 get 메서드. Entity 형식으로 가져옴.
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        return result.getBody();
    }

    public UserResponse post() {
        // path variable을 userId로 준다.
        // http://localhost:9090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                // expand : pathVariable로 순차적으로 넣어준다. cf. map을 사용해도 되지만 직관적으로 expand를 써도 좋다.
                .expand(100, "haejun")
                .toUri();
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http json body
        UserRequest req = new UserRequest();
        req.setName("haejun");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class); // 해당 주소에 request body를 만들어서 응답을 받음.


        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }
}
