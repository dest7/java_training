package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.fail;

/**
 * Created by Edward on 03.08.2016.
 */
public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCashe = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCashe = null;
        returnToGroupPage();
    }

    public void graph() throws InterruptedException {
//            wd.findElement(By.linkText("Главная")).click();
//            wd.findElement(By.linkText("Проведенные процедуры")).click();
//            wd.findElement(By.linkText("Статистика процедур ГД график")).click();
//            wd.findElement(By.linkText("Отмена")).click();


            wd.findElement(By.xpath("//a[contains(@href, '/Reports/Pages/Folder.aspx')]")).click();
            wd.findElement(By.xpath("//div[9]/table/tbody/tr/td[2]/a")).click();
            wd.findElement(By.xpath("//div[8]/table/tbody/tr/td[2]/a")).click();
            wd.findElement(By.xpath("//div[@id='ctl31_AsyncWait_Wait']/table/tbody/tr/td[2]/div/a")).click();


            wd.findElement(By.id("ctl31_ctl04_ctl07_ddDropDownButton")).click();
            wd.findElement(By.id("ctl31_ctl04_ctl07_divDropDown_ctl00")).click();
            wd.findElement(By.id("ctl31_ctl04_ctl07_divDropDown_ctl05")).click();
            wd.findElement(By.id("ctl31_ctl04_ctl07_ddDropDownButton")).click();
            wd.findElement(By.id("ctl31_ctl04_ctl00")).click();
            for (int second = 0;; second++) {
                if (second >= 60) fail("timeout");
                try { if ("Статистика процедур ГД".equals(wd.findElement(By.xpath("//td[2]/table/tbody/tr/td/div")).getText())) break; } catch (Exception e) {}
                Thread.sleep(1000);
            }
        }


    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCashe = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Groups groupCashe = null;

    public Groups all() {
        if (groupCashe != null){
            return new Groups(groupCashe);
        }

        groupCashe = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCashe.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCashe);
    }

}
