package utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;

public class HttpClient {

    public static CloseableHttpClient client;
    public static CloseableHttpResponse response;

    public void createClient(){
        int timeout = 5;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();

         client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    public void closeClient() throws IOException {
        client.close();
        response.close();
    }
}
