package se.consid.applications.tidig.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;


@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeRestTest {

    @Autowired
    private WebTestClient client;


    @Test
    public void verifyApiKey() { // mostly a demo on how to pass it

        int id = 1;

        client.get()
                .uri("/Api/Employee/SubTree")
                .accept(APPLICATION_JSON)
                .header("X-ApiKey", "TEST_APIKEY_HEADER")
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    public void verifyQueryParam() {  // mostly a demo on how to pass it

        int id = 1;

        client.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/Api/Employee/SubTree")
                                .queryParam("apiKey", "TEST_APIKEY_QUERYPARAM")
                                .build())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().is4xxClientError();
    }


    @Test
    public void getEmployeeSubTree() {

        String apiKeyValue = "0505f8a8-482a-4219-8e3b-108203b95064";  // tofu
        //String apiKeyValue = "78beab2f-5b80-48b5-894b-b3ef6d6f4ee5";  // alpu
        //  String apiKeyValue = "d2562eff-d9ed-4091-b95c-b53e9e802326"; // alka

        EntityExchangeResult<String> entityExchangeResult = client.get()
                .uri("/Api/Employee/SubTree")
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
    public void getEmployeeSubTreeErrorInKey() {

        String apiKeyValue = "0505f8a8-482a-4219-8e3b-108203b9506X";

        EntityExchangeResult<String> entityExchangeResult = client.get()
                .uri("/Api/Employee/SubTree")
                .accept(APPLICATION_JSON)
                .header("X-ApiKey", apiKeyValue)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody(String.class)
                .returnResult();

        String jsonTree = entityExchangeResult.getResponseBody();
        assertNotNull(jsonTree);
    }

    @Test
    public void getEmployeeTimePermission() {

        String apiKeyValue = "0505f8a8-482a-4219-8e3b-108203b95064";  // tofu
        //String apiKeyValue = "78beab2f-5b80-48b5-894b-b3ef6d6f4ee5";  // alpu
        //  String apiKeyValue = "d2562eff-d9ed-4091-b95c-b53e9e802326"; // alka

        EntityExchangeResult<String> entityExchangeResult = client.get()
                .uri("/Api/Employee/TimePermission")
                .accept(APPLICATION_JSON)
                .header("X-ApiKey", apiKeyValue)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult();

        String jsonTree = entityExchangeResult.getResponseBody();
        assertNotNull(jsonTree);
    }

    @Test
    public void getEmployeeTimePermissionErrorInKey() {

        String apiKeyValue = "0505f8a8-482a-4219-8e3b-108203b9506X";  // tofu
        //String apiKeyValue = "78beab2f-5b80-48b5-894b-b3ef6d6f4ee5";  // alpu
        //  String apiKeyValue = "d2562eff-d9ed-4091-b95c-b53e9e802326"; // alka

        EntityExchangeResult<String> entityExchangeResult = client.get()
                .uri("/Api/Employee/TimePermission")
                .accept(APPLICATION_JSON)
                .header("X-ApiKey", apiKeyValue)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody(String.class)
                .returnResult();

        String jsonTree = entityExchangeResult.getResponseBody();
        assertNotNull(jsonTree);
    }





}


