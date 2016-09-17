package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private  String firstName;
    private  String lastName;
    private  String address;
    private  String homePhone;
    private  String mobilePhone;
    private  String workPhone;
    private  String allPhones;
    private  String group;
    private  String email;
    private  String allEmail;
    private  String emailTwo;
    private  String emailThree;
    private String dataFromDetailPage;
    private  String allData;



    public String getAllData() {
        return allData;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
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
