package address_book_system;

import java.util.Scanner;

public class DisplayInConsole {

    public static final int COUNT_BY_CITY_OR_STATE = 7;
    public static final int SORT_BY_CITY = 9;
    public static final int SORT_BY_STATE = 10;
    public static final int SORT_BY_ZIP = 11;
    public static final int VIEW_DETAILS_BY_CITY_STATE = 6;
    public static final int EDIT_FIRST_NAME = 1;
    public static final int EDIT_LAST_NAME = 2;
    public static final int EDIT_ADDRESS = 3;
    public static final int EDIT_CITY = 4;
    public static final int EDIT_STATE = 5;
    public static final int EDIT_ZIP = 6;
    public static final int EDIT_PHONE_NUMBER = 7;
    public static final int EDIT_EMAIL = 8;
    public static final int EXIT_FUNCTION = 9;
    static final int ADD_DETAILS = 1;
    static final int PRINT_DETAILS = 2;
    static final int EDIT_DETAILS = 3;
    static final int DELETE_DETAILS = 4;
    static final int DISPLAY_BY_CITY_OR_STATE = 5;
    static final int SORT_BY_NAME = 8;
    static final int EXIT_PROGRAM = 12;

    public int choiceOfUsers;

    Scanner choice = new Scanner(System.in);

    void displayChoices() {
        System.out.println("\n \n Welcome to Address Book Program.");
        System.out.println("These are the actions you can perform in the Address book");
        System.out.println("1. Adding a new contact details.");
        System.out.println("2. Printing a existing contact details.");
        System.out.println("3. Editing a existing contact.");
        System.out.println("4. Deleting a existing contact.");
        System.out.println("5. Display details by city or state name.");
        System.out.println("6. View details by city or state grouped view.");
        System.out.println("7. Count person by city or state.");
        System.out.println("8. Sort details by first name.");
        System.out.println("9. Sort details by city name.");
        System.out.println("10. Sort details by state name.");
        System.out.println("11. Sort details by zip code.");
    }

    void displayTermination() {
        System.out.println("Your program is Terminated.");
    }

    public void takingInputOverWhileLoop() {
        displayChoices();
        System.out.println(" \n Enter a number between 1 to 11 to run any one of the functionality and 12 to terminate : \n");
        System.out.print("your choice : ");
        choiceOfUsers = choice.nextInt();
    }

    public void inputOverWhileLoopEditDetails() {

        System.out.println("\n Enter Number which details you want to edit, and 9 to exit from edit details functionality.");
        System.out.println("1. Edit First Name");
        System.out.println("2. Edit Last name");
        System.out.println("3. Edit Address ");
        System.out.println("4. Edit city");
        System.out.println("5. Edit state");
        System.out.println("6. Edit zip code");
        System.out.println("7. Edit phone number");
        System.out.println("8. Edit email");
        System.out.println("9. Exit from Edit Details and returned to main menu.");
        System.out.print("your choice: ");
        choiceOfUsers = choice.nextInt();
    }

    public void displayExitFormEditDetails() {

        System.out.println("\n You have been returned to main menu.");
    }
}