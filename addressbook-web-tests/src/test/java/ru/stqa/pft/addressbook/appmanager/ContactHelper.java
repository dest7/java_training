package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Edward on 03.08.2016.
 */
public class ContactHelper extends BaseHelper{
    //FirefoxDriver wd;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void enterNewContactInAb() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactDataForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstName());
        type(By.name("lastname"),contactData.getLastName());
        type(By.name("address"),contactData.getAddress());
        type(By.name("home"),contactData.getPhone());
        type(By.name("email"),contactData.getEmail());

        //Сформирован метод, позволяющий проверить наличие или отсутствие элементов
        // Значением true мы определяем, что элемент на форме должен быть, а fasle а том
        // что элемента быть не должно на форме
        //Проверка на то, что элемента быть не должно
        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void alertYes() {
        wd.switchTo().alert().accept();
    }



    public void editContact() {
        click(By.xpath("//*[@id='maintable']//tr[2]/td[8]/a"));
    }


    public void updateContact() {
        click(By.name("update"));
    }
    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void createContact(ContactData contact) {

        addNewContact();
        fillContactDataForm(contact, true);
        enterNewContactInAb();
        returnToHomePage();
    }

       public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
