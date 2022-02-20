package stepdefinitions;

import functions.AuthenticationService;
import io.cucumber.java.en.Given;
import org.picocontainer.annotations.Inject;
import java.io.IOException;

public class AuthenticationStepDef {
    @Inject
    AuthenticationService getLoginToken;

    @Given("I login to Currency Cloud")
    public void I_login_to_Currency_Cloud() throws IOException {
        getLoginToken.executePostAuthentication();
    }

    @Given("I logout of Currency Cloud and close the session")
    public void I_logout_of_Currency_Cloud_and_close_the_session() throws IOException {
        getLoginToken.executePostCloseSession();
        getLoginToken.verifySessionIsClosed();
    }
}
