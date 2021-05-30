package se.consid.applications.tidig.reportedtime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ReportedTimeRestTest {

    String apiKeyValue = "0505f8a8-482a-4219-8e3b-108203b95064";  // tofu
    //String apiKeyValue = "78beab2f-5b80-48b5-894b-b3ef6d6f4ee5";  // alpu
    //  String apiKeyValue = "d2562eff-d9ed-4091-b95c-b53e9e802326"; // alka



    @Autowired
    private WebTestClient client;


    @Test
    public void getEmployeeTime() {

        LocalDate fromDate = LocalDate.of(2018,1,28);
        LocalDate toDate = LocalDate.of(2018,1,30);

        EntityExchangeResult<String> entityExchangeResult = client.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/Api/Time")
 //                               .queryParam("empId", "tofu")
  //                              .queryParam("customerId", 10)
    //                            .queryParam("projectId", 9)
                                .queryParam("fromDate", fromDate)
                                .queryParam("toDate", toDate)
                                .build())
                .accept(APPLICATION_JSON)
                .header("X-ApiKey", apiKeyValue)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult();

        String jsonTree = entityExchangeResult.getResponseBody();
        System.out.println(jsonTree);
        assertNotNull(jsonTree);

    }
    
    
    @Test
    public void getEmployeeTimeNoChildNodes() {

        LocalDate fromDate = LocalDate.of(2018,1,28);
        LocalDate toDate = LocalDate.of(2018,1,30);

        EntityExchangeResult<String> entityExchangeResult = client.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/Api/Time")
 //                               .queryParam("empId", "tofu")
  //                              .queryParam("customerId", 10)
    //                            .queryParam("projectId", 9)
                                .queryParam("fromDate", fromDate)
                                .queryParam("toDate", toDate)
                                .build())
                .accept(APPLICATION_JSON)
                .header("X-ApiKey", "384383b0-4213-4be2-abb5-a8e0d69b2dd0")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult();

        String jsonTree = entityExchangeResult.getResponseBody();
        System.out.println(jsonTree);
        assertNotNull(jsonTree);

    }
    
    

    
    
    

    
    
    

// 2018-01-27 28 30 29

}
