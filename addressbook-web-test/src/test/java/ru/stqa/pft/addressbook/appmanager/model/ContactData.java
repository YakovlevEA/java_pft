package ru.stqa.pft.addressbook.appmanager.model;

import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firstName;
    private String middleName;
    private String lastName;
    private String group;
    private String homePhone;
    private String mobilePhone;

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    private String workPhone;

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }



    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData(int id, String firstName, String middleName, String lastName, String group) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.group = group;
    }
    public ContactData() {
        this.firstName = null;
        this.middleName = null;
        this.lastName = null;
        this.group = null;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getMiddleName() {

        return middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName);
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    public String getGroup() {
        return group;
    }


}
