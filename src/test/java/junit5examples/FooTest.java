package junit5examples;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

class FooTest {

    // jukito
    // wiremock
    // mockito

    private WireMockServer wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());

    @BeforeEach
    void setUp() {
        wireMockServer.start();
        System.out.println(wireMockServer.port());
    }

    @Test
    void name() {
        System.out.println("foo");
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }
}