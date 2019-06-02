package junit5examples;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

class MyHttpClientTest {

    private WireMockServer wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
    private MyHttpClient sut;

    @BeforeEach
    void setUp() {
        wireMockServer.start();
        WireMock.configureFor(wireMockServer.port());
        sut = new MyHttpClient(String.format("http://localhost:%d/", wireMockServer.port()));
    }

    @Test
    void itReturnsResponseBody() throws IOException {
        stubFor(get(urlEqualTo("/")).willReturn(
                aResponse().withBody("Mock response body").withStatus(200)));

        String actual = sut.get();

        assertThat(actual).isEqualTo("Mock response body");
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }
}
