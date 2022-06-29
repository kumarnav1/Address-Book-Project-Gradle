package address_book_system;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;

public class AddressBookMain {

    public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        ProcessAddressBook processAddressBook = new ProcessAddressBook();
        DisplayInConsole displayInConsole = new DisplayInConsole();

        while (true) {

            displayInConsole.takingInputOverWhileLoop();

            switch (displayInConsole.choiceOfUsers) {

                case DisplayInConsole.ADD_DETAILS:
                    processAddressBook.addNewContact();
                    break;
                case DisplayInConsole.PRINT_DETAILS:
                    processAddressBook.writeDataToCSV();
                    break;
                case DisplayInConsole.EDIT_DETAILS:
                    processAddressBook.editDetails();
                    break;
                case DisplayInConsole.DELETE_DETAILS:
                    processAddressBook.deleteDetails();
                    break;
                case DisplayInConsole.DISPLAY_BY_CITY_OR_STATE:
                    processAddressBook.displayPersonUsingCityOrState();
                    break;
                case DisplayInConsole.VIEW_DETAILS_BY_CITY_STATE:
                    processAddressBook.viewPersonByCityOrState();
                    break;
                case DisplayInConsole.COUNT_BY_CITY_OR_STATE:
                    processAddressBook.countPersonByCityOrState();
                    break;
                case DisplayInConsole.SORT_BY_NAME:
                    processAddressBook.sortByName();
                    break;
                case DisplayInConsole.SORT_BY_CITY:
                    processAddressBook.sortByCity();
                    break;
                case DisplayInConsole.SORT_BY_STATE:
                    processAddressBook.sortByState();
                    break;
                case DisplayInConsole.SORT_BY_ZIP:
                    processAddressBook.sortByZip();
                    break;
                case DisplayInConsole.EXIT_PROGRAM:
                    displayInConsole.displayTermination();
                    return;
                default:
                    System.out.println("Please Enter correct input !");
                    break;
            }
        }
    }
}