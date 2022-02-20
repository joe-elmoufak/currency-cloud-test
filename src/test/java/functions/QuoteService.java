package functions;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.picocontainer.annotations.Inject;
import utils.HttpClient;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import static functions.AuthenticationService.authToken;
import static functions.BaseClass.*;
import static org.junit.Assert.assertEquals;
import static utils.HelperFunction.EXPECTED_RESPONSE;

public class QuoteService extends HttpClient {

    @Inject
    utils.HelperFunction HelperFunction;

    static JSONObject rateJson;
    private static String sellCurrency;
    private static String buyCurrency;
    private static String originalConversionAmount;

//    Api request field names
    String CURRENCY_PAIR = "currency_pair";
    String BUY_AMOUNT = "client_buy_amount";
    String CLIENT_RATE = "client_rate";
    String CORE_RATE = "core_rate";

    public void verifyRateConversion() throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());
        System.out.println(jsonBody);
        rateJson = new JSONObject(jsonBody);

        double buyAmount = Double.parseDouble((String) HelperFunction.getValuefor(rateJson, BUY_AMOUNT));
        double clientRate = Double.parseDouble((String) HelperFunction.getValuefor(rateJson, CLIENT_RATE));
        double coreRate = Double.parseDouble((String) HelperFunction.getValuefor(rateJson, CORE_RATE));

        assertEquals((clientRate * Double.parseDouble(originalConversionAmount)), buyAmount, 0.006);
        assertEquals((coreRate * Double.parseDouble(originalConversionAmount)), buyAmount, 0.006);
        assertEquals(HelperFunction.getValuefor(rateJson, CURRENCY_PAIR), sellCurrency+buyCurrency);
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