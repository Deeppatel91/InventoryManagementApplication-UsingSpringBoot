package ca.gbc.orderservice;

import ca.gbc.orderservice.stub.InventoryClientStub;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import com.github.tomakehurst.wiremock.WireMockServer;

import static io.restassured.RestAssured.given;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Use the test profile
public class OrderServiceApplicationTests {

    @LocalServerPort
    private Integer port;

    private static WireMockServer wireMockServer;

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @BeforeAll
    static void initWireMock() {
        // Initialize WireMock with a dynamic port
        wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
        wireMockServer.start();

        // Set the WireMock server port to a system property that Spring will use in the test profile
        System.setProperty("wiremock.server.port", String.valueOf(wireMockServer.port()));
    }

    @AfterAll
    static void stopWireMock() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    @BeforeEach
    void setupRestAssured() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldPlaceOrder() {
        String skuCode = "samsung_tv_2024";
        Integer quantity = 10;

        // Stub the inventory call using WireMock
        InventoryClientStub.stubInventoryCall(skuCode, quantity); // Set up WireMock stub

        String orderJson = """
            {
                "orderNumber": "12345",
                "skuCode": "%s",
                "price": 500.00,
                "quantity": %d
            }
        """.formatted(skuCode, quantity);

        // Place order and verify response
        given()
                .contentType("application/json")
                .body(orderJson)
                .when()
                .post("/api/order")
                .then()
                .statusCode(201)
                .body(equalTo("Order Placed Successfully"));
    }
}
