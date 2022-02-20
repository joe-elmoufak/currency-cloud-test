package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.picocontainer.annotations.Inject;
import java.io.IOException;

public class QuoteStepDef {
    @Inject
    functions.QuoteService QuoteService;

    @Given("I get a quote to {string} {string} of {string} for {string}")
    public void i_get_a_quote_to_of_for(String transactionType, String amount, String originalCurrency, String newCurrency) throws IOException {
        QuoteService.executeGetRates(transactionType, amount, originalCurrency, newCurrency, true);
    }

    @When("I verify that the quote fails with a {string} error")
    public void i_verify_that_the_quote_fails_with_a_error(String errorType) {
        QuoteService.verifyGetRatesResponse(errorType);
    }

    @Given("An unauthorised user attempts to get a conversion quote")
    public void an_unauthorised_user_attempts_to_get_a_conversion_quote() throws IOException {
        QuoteService.executeGetRates("sell", "1150.00", "GBP", "USD", false);
    }

    @Given("I verify that the rates are converted correctly")
    public void I_verify_that_the_rates_are_converted_correctly() throws IOException {
        QuoteService.verifyGetRatesResponse("success");
        QuoteService.verifyRateConversion();
    }
}
