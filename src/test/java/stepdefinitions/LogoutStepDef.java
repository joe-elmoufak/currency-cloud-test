package stepdefinitions;

import functions.CloseSessionService;
import io.cucumber.java.en.Given;
import org.picocontainer.annotations.Inject;
import java.io.IOException;

public class LogoutStepDef {

    @Inject
    CloseSessionService CloseSession;

    @Given("I logout of Currency Cloud and close the session")
    public void I_logout_of_Currency_Cloud_and_close_the_session() throws IOException {
        CloseSession.executePostCloseSession();
        CloseSession.verifySessionIsClosed();
    }
}
