package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
@Entity
@Table(name = "addressbook")

@XStreamAlias("contact")
public class ContactData {

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private  String firstName;

    @Expose
    @Column(name = "lastname")
    private  String lastName;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private  String address;

    @Expose

    @Column(name = "home")
    @Type(type = "text")
    private  String homePhone;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private  String mobilePhone;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private  String workPhone;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private  String email;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private  String emailTwo;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private  String emailThree;

    @Transient
    private  String allEmail;

    @Transient
    private  String allPhones;

    @Expose
    @Transient
    private  String group;

    @Transient
    private  String dataFromDetailPage;

    @Transient
    private  String allData;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (emailTwo != null ? !emailTwo.equals(that.emailTwo) : that.emailTwo != null) return false;
        if (emailThree != null ? !emailThree.equals(that.emailThree) : that.emailThree != null) return false;
        return group != null ? group.equals(that.group) : that.group == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (emailTwo != null ? emailTwo.hashCode() : 0);
        result = 31 * result + (emailThree != null ? emailThree.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }

    public File getPhoto() {
        if (photo == null || photo.equals("")) {
            return null;
        } else {
            return new File(photo);
        }
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData getAllData(ContactData contact) {
        return contact;
    }
    public ContactData() {
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getGroup() {
        return group;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmail() {
        return allEmail;
    }

    public String getEmail() {
        return email;
    }

    public String  getEmailTwo() {
        return emailTwo;
    }

    public String  getEmailThree() {
        return emailThree;
    }

    public String getDataFromDetailPage() {
        return dataFromDetailPage;
    }

    public ContactData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }
    public ContactData withEmailTwo(String emailTwo) {
        this.emailTwo = emailTwo;
        return this;
    }
    public ContactData withEmailThree(String emailThree) {
        this.emailThree = emailThree;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String phone) {
        this.homePhone = phone;
        return this;
    }

    public ContactData withMobilePhone(String phone) {
        this.mobilePhone = phone;
        return this;
    }

    public ContactData withWorkPhone(String phone) {
        this.workPhone = phone;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withDataFromDetailPage(String dataFromDetailPage) {
        this.dataFromDetailPage = dataFromDetailPage;
        return this;
    }

    public ContactData withAllData(String allData) {
        this.allData = allData;
        return this;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }



}
