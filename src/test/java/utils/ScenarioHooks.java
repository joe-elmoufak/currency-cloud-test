package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.picocontainer.annotations.Inject;
import java.io.IOException;

public class ScenarioHooks {

    @Inject
    utils.HttpClient HttpClient;

    @Before
    public void beforeScenario(){
        HttpClient.createClient();
    }

    @After
    public void afterScenario() throws IOException {
        HttpClient.closeClient();
    }
}
