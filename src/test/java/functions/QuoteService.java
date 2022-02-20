package functions;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static functions.AuthenticationService.BASE_ENDPOINT;
import static functions.AuthenticationService.authToken;
import static org.junit.Assert.assertEquals;
import static utils.HttpClient.*;

public class QuoteService {

    private static String sellCurrency;
    private static String buyCurrency;
    private static String originalConversionAmount;
    private static final Map<String, Integer> EXPECTED_RESPONSE = Map.of(
            "success", 200,
            "bad_request", 400,
            "unauthorised", 401
    );

//    Api request field names
    String CURRENCY_PAIR = "currency_pair";
    String BUY_AMOUNT = "client_buy_amount";
    String CLIENT_RATE = "client_rate";
    String CORE_RATE = "core_rate";

    public void verifyRateConversion() throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonBody);

        double buyAmount = Double.parseDouble((String) jsonObject.get(BUY_AMOUNT));
        double clientRate = Double.parseDouble((String) jsonObject.get(CLIENT_RATE));
        double coreRate = Double.parseDouble((String) jsonObject.get(CORE_RATE));

        assertEquals((clientRate * Double.parseDouble(originalConversionAmount)), buyAmount, 0.006);
        assertEquals((coreRate * Double.parseDouble(originalConversionAmount)), buyAmount, 0.006);
        assertEquals(jsonObject.get(CURRENCY_PAIR), sellCurrency+buyCurrency);
    }

    public void executeGetRates(String transactionType, String amount, String toSell, String toBuy, Boolean authenticate) throws IOException {
        List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("sell_currency", sellCurrency = toSell));
        params.add(new BasicNameValuePair("buy_currency", buyCurrency = toBuy));
        params.add(new BasicNameValuePair("fixed_side", transactionType));
        params.add(new BasicNameValuePair("amount",  originalConversionAmount = amount));
        HttpGet request = new HttpGet(BASE_ENDPOINT + "/rates/detailed" + "?" + URLEncodedUtils.format(params, "utf-8"));
        if (authenticate) request.setHeader("X-Auth-Token", authToken);
        response = client.execute(request);
    }

    public void verifyGetRatesResponse(String expectedOutcome) {
        int expectedStatus = EXPECTED_RESPONSE.get(expectedOutcome);
        assertEquals(expectedStatus,response.getStatusLine().getStatusCode());
    }
}