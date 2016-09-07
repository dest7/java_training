package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

    }

    public void editContactById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
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
        contactCash = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        editContactById(contact.getId());
        fillContactDataForm(contact,false);
        updateContact();
        contactCash = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCash = null;
        returnToHomePage();

    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCash = null;
    public Contacts all() {
        if (contactCash != null){
            return new Contacts(contactCash);
        }
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
//            String address = element.getText();
//            String phone = element.getText();
//            String email = element.getText();
//            String group = element.getText();
            contactCash.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return new Contacts(contactCash);
    }

}
