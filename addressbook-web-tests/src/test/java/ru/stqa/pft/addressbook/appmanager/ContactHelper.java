package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("work"),contactData.getWorkPhone());
        type(By.name("email"),contactData.getEmail());
        type(By.name("email2"),contactData.getEmailTwo());
        type(By.name("email3"),contactData.getEmailThree());
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
       // contactCash = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        editContactById(contact.getId());
        fillContactDataForm(contact,false);
        updateContact();
        //contactCash = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        //contactCash = null;
        returnToHomePage();

    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCash = null;

   /* public Contacts all() {
        if (contactCash != null){
            return new Contacts(contactCash);
        }
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            contactCash.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return new Contacts(contactCash);
    } */
    public Contacts all() {
//        if (contactCash != null){
//            return new Contacts(contactCash);
//        }
       // contactCash = new Contacts();
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allPhones = cells.get(5).getText();
            String allEmail = cells.get(4).getText();

            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withAddress(address)
                    .withAllPhones(allPhones)
                    .withAllEmail(allEmail));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String emailTwo = wd.findElement(By.name("email2")).getAttribute("value");
        String emailThree = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstName)
                .withLastName(lastName)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withAddress(address)
                .withEmail(email)
                .withEmailTwo(emailTwo)
                .withEmailThree(emailThree);
    }

    public void initContactModificationById (int id){

        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

     /*   WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
     */
    }

    public void pressDetailButton(int id){
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
        //wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[4]/td[7]/a/img"));
    }

    public String infoFromDetailPage(ContactData contact){
        pressDetailButton(contact.getId());
        String dataFromDetailPage = wd.findElement(By.id("content")).getText();
        return new String(dataFromDetailPage);

    }


}
