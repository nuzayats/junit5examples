package junit5examples;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * TODO: retry can be implemented and tested like this https://nozaki.me/roller/kyle/entry/articles-test-wiremockunstable
 */
public class MyHttpClient {

    private final String endpoint;

    public MyHttpClient(String endpoint) {
        this.endpoint = endpoint;
    }

    public String get() throws IOException {
        HttpGet request = new HttpGet(endpoint);
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpResponse response = httpClient.execute(request);
            return EntityUtils.toString(response.getEntity());
        }
    }
}
