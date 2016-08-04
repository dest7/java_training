package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Edward on 03.08.2016.
 */
public class ContactHelper {
    FirefoxDriver wd;

    public ContactHelper(FirefoxDriver wd) {
        this.wd=wd;
    }

    public void enterNewContactInAb() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillContactDataForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
        wd.findElement(By.name("address")).click();
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(contactData.getPhone());
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    public void addNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void deleteSelectedContact() {
        wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    }

    public void selectContact() {
        wd.findElement(By.xpath("//tr[3]/td/input")).click();
    }

    public void goToHome() {
        wd.findElement(By.linkText("home")).click();
    }

    public void alertYes() {
        wd.switchTo().alert().accept();
    }
}
