package functions;

import org.apache.http.client.methods.HttpPost;
import utils.HttpClient;
import java.io.IOException;
import static functions.AuthenticationService.authToken;
import static functions.BaseClass.BASE_ENDPOINT;
import static org.junit.Assert.assertEquals;

public class CloseSession extends HttpClient {

    public void executePostCloseSession() throws IOException {
        HttpPost request = new HttpPost(BASE_ENDPOINT + "/authenticate/close_session");
        request.setHeader("X-Auth-Token", authToken);
        response = client.execute(request);
    }

    public void verifySessionIsClosed() {
        assertEquals(response.getStatusLine().getStatusCode(), 200);
    }
}
