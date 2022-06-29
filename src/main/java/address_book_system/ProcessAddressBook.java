package address_book_system;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ProcessAddressBook {

    Scanner scanner = new Scanner(System.in);
    Map<String, ArrayList<AddressBookContacts>> multipleAddressBookMap = new HashMap<>();
    ArrayList<AddressBookContacts> contactArray;
    AddressBookContacts addressBookContacts;
    Map<String, List<AddressBookContacts>> cityAndPersonMap;
    Map<String, List<AddressBookContacts>> stateAndPersonMap;

    public void addNewContact() {
        System.out.println("\n You have chosen to Add a new contact details.\n");

        System.out.println("\n Enter the book name ");
        String bookName = scanner.next();

        System.out.println("\n Enter 1 if you want to add the contact using .csv file or \n Enter anything to add contact using console.");

        if (scanner.nextInt() == 1) {
            try {
                ArrayList<AddressBookContacts> csvToBean = (ArrayList<AddressBookContacts>) new CsvToBeanBuilder(new FileReader("Resources//person.csv"))
                        .withType(AddressBookContacts.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build()
                        .parse();
                multipleAddressBookMap.put(bookName, csvToBean);
                System.out.println("Details added to the address book, inside the book " + bookName);
                for (AddressBookContacts addressBookContacts : csvToBean) {
                    System.out.println(addressBookContacts);
                    System.out.println();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.print("Enter contact's first name : ");
            String firstName = scanner.next();

            System.out.print("Enter contact's last name : ");
            String lastName = scanner.next();

            System.out.print("Enter contact's address : ");
            scanner.nextLine();
            String address = scanner.nextLine();

            System.out.print("Enter contact's city : ");
            String city = scanner.next();

            System.out.print("Enter contact's state : ");
            String state = scanner.next();

            System.out.print("Enter contact's zip code : ");
            String zipCode = scanner.next();

            System.out.print("Enter contact's phone number : ");
            String phoneNumber = scanner.next();

            System.out.print("Enter contact's email : ");
            scanner.nextLine();
            String email = scanner.nextLine();

            addressBookContacts = new AddressBookContacts(firstName, lastName, address, city, state, zipCode, phoneNumber, email);
            duplicateCheckWhileAdding(bookName, addressBookContacts);
        }
    }

    private void duplicateCheckWhileAdding(String bookName, AddressBookContacts addressBookContacts) {
        if (multipleAddressBookMap.containsKey(bookName)) {
            String isFirstName = addressBookContacts.getFirstName();
            AddressBookContacts isFound = multipleAddressBookMap
                    .get(bookName)
                    .stream()
                    .filter(arrayRef -> arrayRef.getFirstName().equals(isFirstName))
                    .findFirst()
                    .orElse(null);

            if (isFound != null) {
                System.out.println("\n Match found, duplicate Entry \n ");
                return;
            }

            System.out.println("\nContact not found in the existing address book, No duplicate Entry will be there.");
            multipleAddressBookMap.get(bookName).add(addressBookContacts);
            System.out.println("Contact added successfully exiting arrayList and existing book : \"" + bookName + " \"");
        } else {
            contactArray = new ArrayList<>();
            contactArray.add(addressBookContacts);
            multipleAddressBookMap.put(bookName, contactArray);
            System.out.println("Successfully created book " + bookName);
            System.out.println("New contact added in the new arraylist in new address book " + bookName);
        }
        displayAddedDetails(addressBookContacts);
    }

    void writeDataToCSV() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        System.out.println("Enter 1 to print data to a .csv file or any character to display data in console.");
        if (scanner.nextInt() == 1) {
            if (multipleAddressBookMap.isEmpty()) {
                System.out.println("No book exist in the Data base.");
                return;
            }
            System.out.println("Enter the address book name to sort the Entries: ");
            displayAllAddressBooksName();
            System.out.println("Your Entries: ");
            String getBook = scanner.next();
            ArrayList<AddressBookContacts> contactsInEachList = multipleAddressBookMap.get(getBook);
            try (Writer writer = Files.newBufferedWriter(Paths.get("Resources\\personDisplay.csv"))) {
                StatefulBeanToCsvBuilder<AddressBookContacts> builder = new StatefulBeanToCsvBuilder<>(writer);
                StatefulBeanToCsv<AddressBookContacts> beanWriter = builder.build();
                beanWriter.write(contactsInEachList);
                System.out.println("Data successfully written on the .csv file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            displayPersonDetails();
    }

    void editDetails() {
        System.out.println("\n You have chosen to update the existing contact details.\n");
        AddressBookContacts varEdit = isDetailsMatched();

        if (varEdit == null) {
            System.out.println("Contact not found in the address book");
            return;
        }

        System.out.println("\n Match found.\n ");
        DisplayInConsole displayInConsole = new DisplayInConsole();

        while (true) {
            displayInConsole.inputOverWhileLoopEditDetails();

            switch (displayInConsole.choiceOfUsers) {

                case DisplayInConsole.EDIT_FIRST_NAME:
                    System.out.print("Enter contact's first name : ");
                    String firstName = scanner.next();
                    varEdit.setFirstName(firstName);
                    System.out.println("\n First Name was successfully edited in Address book");
                    break;
                case DisplayInConsole.EDIT_LAST_NAME:
                    System.out.print("Enter contact's last name : ");
                    String lastName = scanner.next();
                    varEdit.setLastName(lastName);
                    break;
                case DisplayInConsole.EDIT_ADDRESS:
                    System.out.print("Enter contact's address : ");
                    scanner.nextLine();
                    String address = scanner.nextLine();
                    varEdit.setAddress(address);
                    break;
                case DisplayInConsole.EDIT_CITY:
                    System.out.print("Enter contact's city : ");
                    String city = scanner.next();
                    varEdit.setCity(city);
                    break;
                case DisplayInConsole.EDIT_STATE:
                    System.out.print("Enter contact's state : ");
                    String state = scanner.next();
                    varEdit.setState(state);
                    break;
                case DisplayInConsole.EDIT_ZIP:
                    System.out.print("Enter contact's zip code : ");
                    String zipCode = scanner.next();
                    varEdit.setZipCode(zipCode);
                    break;
                case DisplayInConsole.EDIT_PHONE_NUMBER:
                    System.out.print("Enter contact's phone number : ");
                    String phoneNumber = scanner.next();
                    varEdit.setPhoneNumber(phoneNumber);
                    break;
                case DisplayInConsole.EDIT_EMAIL:
                    System.out.print("Enter contact's email : ");
                    scanner.nextLine();
                    String email = scanner.nextLine();
                    varEdit.setEmail(email);
                    break;
                case DisplayInConsole.EXIT_FUNCTION:
                    displayInConsole.displayExitFormEditDetails();
                    return;
                default:
                    System.out.println("Please Enter correct input !");
                    break;
            }
        }
    }

    void deleteDetails() {
        System.out.println("\n You have chosen to remove the existing contact details.\n");
        AddressBookContacts varDelete = isDetailsMatched();
        if (varDelete == null) {
            System.out.println("Contact not found in the address book");
            return;
        }

        System.out.println("\n Match found.\n ");
        contactArray.remove(varDelete);
        System.out.println("Record was Deleted");
    }

    void displayAddedDetails(AddressBookContacts toDisplayDetails) {
        System.out.println(toDisplayDetails);
    }

    void displayPersonDetails() {
        AddressBookContacts varPrint = isDetailsMatched();
        if (varPrint == null) {
            System.out.println("Contact not found in the address book");
            return;
        }
        System.out.println("\n Match found.\n ");
        displayAddedDetails(varPrint);
    }

    AddressBookContacts isDetailsMatched() {
        if (!displayAllAddressBooksName()) {
            System.out.println("No book exist in the Data base.");
            return null;
        }
        System.out.println("Enter the address book name first. \n ");
        System.out.println("Your choice: ");
        String bookName = scanner.next();
        if (!multipleAddressBookMap.containsKey(bookName)) {
            System.out.println("Book Doesn't match.");
            return null;
        }
        System.out.print(" \n Enter the first name of the person : ");
        String enteredName = scanner.next();
        return multipleAddressBookMap
                .get(bookName)
                .stream()
                .filter(arrayRef -> arrayRef.getFirstName().equals(enteredName))
                .findFirst()
                .orElse(null);
    }

    boolean displayAllAddressBooksName() {
        if (multipleAddressBookMap.isEmpty()) {
            return false;
        }
        multipleAddressBookMap.forEach((key, value) -> System.out.println(key));
        return true;
    }


    void displayPersonUsingCityOrState() {
        if (multipleAddressBookMap.isEmpty()) {
            System.out.println(" No book exist in the Data base.");
            return;
        }
        System.out.println("\n To display the details");
        findPersonUsingCityOrState();
    }

    void findPersonUsingCityOrState() {
        System.out.println("Enter city name to display the person. ");
        String city = scanner.next();

        System.out.println("\nEnter state name to display the person. \n");
        String state = scanner.next();

        for (Map.Entry<String, ArrayList<AddressBookContacts>> pair : multipleAddressBookMap.entrySet()) {
            List<AddressBookContacts> cityCollect = pair
                    .getValue()
                    .stream()
                    .filter(addressBookContacts1 -> addressBookContacts1.getCity().equals(city) || addressBookContacts1.getState().equals(state))
                    .collect(Collectors.toList());
            System.out.println(cityCollect);
        }
    }

    void viewPersonByCityOrState() {
        if (multipleAddressBookMap.isEmpty()) {
            System.out.println("No book exist in the Data base.");
            return;
        }
        this.cityAndPersonMap = new HashMap<>();
        this.stateAndPersonMap = new HashMap<>();

        for (Map.Entry<String, ArrayList<AddressBookContacts>> pair : multipleAddressBookMap.entrySet()) {
            this.cityAndPersonMap = pair.getValue()
                    .stream()
                    .collect(Collectors.groupingBy(AddressBookContacts::getCity));
        }

        for (Map.Entry<String, ArrayList<AddressBookContacts>> pair : multipleAddressBookMap.entrySet()) {
            this.stateAndPersonMap = pair.getValue()
                    .stream()
                    .collect(Collectors.groupingBy(AddressBookContacts::getState));
        }
        System.out.println("City and person map: \n " + this.cityAndPersonMap);
        System.out.println("State and person map: \n " + this.stateAndPersonMap);
    }

    void countPersonByCityOrState() {
        if (multipleAddressBookMap.isEmpty()) {
            System.out.println("No book exist in the Data base.");
            return;
        }
        viewPersonByCityOrState();
        long cityCount = cityAndPersonMap.size();
        System.out.println("\n Total persons using Count by city : " + cityCount);
        long stateCount = stateAndPersonMap.size();
        System.out.println("\n Total persons using Count by state : " + stateCount);
    }

    void sortByName() {
        if (multipleAddressBookMap.isEmpty()) {
            System.out.println("No book exist in the Data base.");
            return;
        }
        System.out.println("Enter the address book name to sort the Entries: ");
        displayAllAddressBooksName();
        System.out.println("Your Entries: ");
        String getBook = scanner.next();
        ArrayList<AddressBookContacts> newList = multipleAddressBookMap.get(getBook);
        newList.sort(Comparator.comparing(AddressBookContacts::getFirstName));
        System.out.println("Printing Alphabetical order Sorted List using first name of the person :\n " + newList);
    }

    void sortByCity() {
        if (multipleAddressBookMap.isEmpty()) {
            System.out.println("No book exist in the Data base.");
            return;
        }
        System.out.println("Enter the address book name to sort the Entries: ");
        displayAllAddressBooksName();
        System.out.println("Your Entries: ");
        String getBook = scanner.next();
        ArrayList<AddressBookContacts> newList = multipleAddressBookMap.get(getBook);

        newList.sort(Comparator.comparing(AddressBookContacts::getCity));

        System.out.println("printing Alphabetical order Sorted List using city :\n " + newList);
    }

    void sortByState() {
        if (multipleAddressBookMap.isEmpty()) {
            System.out.println("No book exist in the Data base.");
            return;
        }
        System.out.println("Enter the address book name to sort the Entries: ");
        displayAllAddressBooksName();
        System.out.println("Your Entries: ");
        String getBook = scanner.next();
        ArrayList<AddressBookContacts> newList = multipleAddressBookMap.get(getBook);

        newList.sort(Comparator.comparing(AddressBookContacts::getState));

        System.out.println("printing Alphabetical order Sorted List using State :\n " + newList.toString());
    }

    void sortByZip() {
        if (multipleAddressBookMap.isEmpty()) {
            System.out.println("No book exist in the Data base.");
            return;
        }
        System.out.println("Enter the address book name to sort the Entries: ");
        displayAllAddressBooksName();
        System.out.println("Your Entries: ");
        String getBook = scanner.next();
        ArrayList<AddressBookContacts> newList = multipleAddressBookMap.get(getBook);

        newList.sort(Comparator.comparing(AddressBookContacts::getZipCode));

        System.out.println("printing Alphabetical order Sorted List zip  :\n " + newList);
    }
}