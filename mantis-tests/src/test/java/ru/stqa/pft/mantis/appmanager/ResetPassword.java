package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Edward on 21.10.2016.
 */
public class ResetPassword extends HelperBase {
    public ResetPassword(ApplicationManager app) {
        super(app);
    }

    public void reset(String username) {
        click(By.xpath("//*[@id=\"menu-items\"]/li[7]/a"));
        click(By.xpath("//*[@id=\"manage-menu\"]/ul/li[1]/a"));
        click(By.xpath("//*[@id=\"manage-user-div\"]/table/tbody/tr[2]/td[1]/a"));
        click(By.cssSelector("input[value = 'Сбросить пароль']"));
    }
}
