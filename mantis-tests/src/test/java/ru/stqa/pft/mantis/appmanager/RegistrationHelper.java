package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by Edward on 20.10.2016.
 */
public class RegistrationHelper {
    private final ApplicationManager app;
    private final WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseURl") + "/login_page.php");
    }
}

