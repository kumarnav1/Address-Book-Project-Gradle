package address_book_system;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class AddressBookContacts {

    @CsvBindByName(column = " FIRST_NAME", required = true)
    private String firstName;
    @CsvBindByName(column = "LAST_NAME", required = true)
    private String lastName;
    @CsvBindByName(column = "ADDRESS", required = true)
    private String address;
    @CsvBindByName(column = "CITY", required = true)
    private String city;
    @CsvBindByName(column = "STATE", required = true)
    private String state;
    @CsvBindByName(column = "ZIP_CODE", required = true)
    private String zipCode;
    @CsvBindByName(column = "PHONE_NUMBER", required = true)
    private String phoneNumber;
    @CsvBindByName(column = "EMAIL", required = true)
    private String email;

    public AddressBookContacts() {
    }

    public AddressBookContacts(String firstName, String lastName, String address, String city, String state, String zipCode, String phoneNumber, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {

        this.zipCode = zipCode;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    @Override
    public String toString() {
        return " First Name   = " + firstName +
                "\n Last Name    = " + lastName +
                "\n Address      = " + address +
                "\n City         = " + city +
                "\n State        = " + state +
                "\n Zip Code     = " + zipCode +
                "\n Phone Number = " + phoneNumber +
                "\n Email        = " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBookContacts that = (AddressBookContacts) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(city, that.city) && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, city, state);
    }
}