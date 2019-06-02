package junit5examples;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

class MyHttpClientTest {

    // jukito
    // wiremock
    // mockito

    private WireMockServer wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
    private MyHttpClient sut;

    @BeforeEach
    void setUp() {
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
        stubFor(get(urlEqualTo("/")).willReturn(
                aResponse().withBody("Mock response body").withStatus(200)));
        sut = new MyHttpClient(String.format("http://localhost:%d/", wireMockServer.port()));
    }

    @Test
    void itReturnsResponseBody() throws IOException {
        String actual = sut.get();

        assertThat(actual).isEqualTo("Mock response body");
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }
}
