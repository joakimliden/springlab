package se.joakimliden.springlab;

//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.*;
//import se.joakimliden.springlab.dtos.LanguageDto;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@SpringBootTest
//class SpringlabApplicationTests {
//
//
//    @Autowired
//    TestRestTemplate testClient;    //RestTemplate om man behöver ansluta till andra webservices    (19/2 14.15)
//
//    @Test
//    void contextLoads() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", "application/xml");
//
//        var result = testClient.getForEntity("httt://localhost:8080/languages/", LanguageDto[].class);
//        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(result.getBody().length).isGreaterThan(0);
//
//    }
//
//}
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import se.joakimliden.springlab.dtos.LanguageDto;
//import se.iths.springdemo.dtos.PersonDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringlabApplicationTests {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testClient;

    @Test
    void contextLoads() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        //testClient.exchange("localhost:8080/person", HttpMethod.GET, new HttpEntity<>(headers), PersonDto[].class);

        var result =testClient.getForEntity("http://localhost:"+port+"/language", LanguageDto[].class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().length).isGreaterThan(0);
    }
}

