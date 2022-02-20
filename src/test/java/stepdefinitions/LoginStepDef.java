package stepdefinitions;

import functions.AuthenticationService;
import io.cucumber.java.en.Given;
import org.picocontainer.annotations.Inject;
import java.io.IOException;

public class LoginStepDef {
    @Inject
    AuthenticationService getLoginToken;

    @Given("I login to Currency Cloud")
    public void I_login_to_Currency_Cloud() throws IOException {
        getLoginToken.executePostAuthentication();
    }
}
