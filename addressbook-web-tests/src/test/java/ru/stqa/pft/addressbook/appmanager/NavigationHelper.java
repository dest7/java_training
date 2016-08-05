package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Edward on 04.08.2016.
 */
public class NavigationHelper extends BaseHelper{

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }
    public void goToHome() {
        click(By.linkText("home"));
    }
}
