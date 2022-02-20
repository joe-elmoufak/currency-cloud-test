package functions;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.picocontainer.annotations.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static functions.BaseClass.*;
import static utils.HttpClient.*;

public class AuthenticationService {

    @Inject
    utils.HelperFunction HelperFunction;

    String LOGIN_ID = "joeelmoufak83@gmail.com";
    String API_KEY = "d879507c9860cf33420a5afd2b2b62f152b55cacca200fa4ac556c05183a804c";
    String AUTH_TOKEN = "auth_token";
    public static String authToken;

    public void executePostAuthentication() throws IOException {
        HttpPost request = new HttpPost(BASE_ENDPOINT + "/authenticate/api");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("login_id", LOGIN_ID));
        params.add(new BasicNameValuePair("api_key", API_KEY));
        request.setEntity(new UrlEncodedFormEntity(params));
        client.execute(request);
        response = client.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonBody);
        authToken = (String) HelperFunction.getValuefor(jsonObject, AUTH_TOKEN);
    }
}
