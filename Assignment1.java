package assignment1;

/**
 * @author Maddison Tidball Assignment 1. This program will read from a CSV file
 * with employee information, then show a menu to the user with the options to
 * display information, add new employee, or search for an employee and display
 * the data. This program will also implement HashMaps to track attendance from
 * the employee object.
 *
 * @since January 28, 2023. I have followed the UNCG Academic Integrity policy
 * on this assignment.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Assignment1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        //Opening and reading file
        FileReader file = new FileReader("employee.txt");
        BufferedReader buffer = new BufferedReader(file);
        String line = null;

        //Creating maps, employeeMap will map employee object to attendence
        //idMap will map iD to the employee object
        HashMap<Object, Integer> employeeMap = new HashMap<>();
        HashMap<String, Object> idMap = new HashMap<>();

        //Creating an array of Strings from the file, and creating object
        assignData(line, buffer, employeeMap, idMap);

        //Menu
        displayMenu(employeeMap, idMap);

    }

    /**
     * This will split, and assign the data from the file into a string array,
     * which allows each word to be accessed independently and then used to
     * create an employee object from each line of the file
     *
     * @param line is a string that will be split to separate the data by ','
     * @param buffer is the reader, and is used to read the file and check if if
     * the file has ended
     * @param employeeMap is a HashMap that maps employee object to the value
     * int attendance and is set to 1 initially, attendance is incremented if
     * employeeMap already contains the key employee object
     * @param idMap is a HashMap that maps iD to employee object
     */
    public static void assignData(String line, BufferedReader buffer,
            HashMap<Object, Integer> employeeMap, HashMap<String, Object> idMap) {
        try {
            while ((line = buffer.readLine()) != null) {

                String[] data = line.split(",");
                String iD = data[0];
                Employee e = new Employee(data[0], data[1], data[2], Double.parseDouble(data[3]));

                //IF EMPLOYEE HAS NOT BEEN READ, ADD 1 TO ATTENDANCE
                if (!employeeMap.containsKey(e)) {
                    employeeMap.put(e, 1);
                    idMap.put(iD, e);
                } else {
                    //INC ATTENDANCE BY 1
                    int attendance = employeeMap.get(e);
                    employeeMap.put(e, attendance + 1);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Assignment1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is used to display the menu to the user, and will loop until
     * the user enters a 4 to end the program
     *
     * @param employeeMap maps employee object to int attendance
     * @param idMap maps employee id to employee object
     */
    public static void displayMenu(HashMap<Object, Integer> employeeMap,
            HashMap<String, Object> idMap) {
        int choice;
        do {

            Scanner input = new Scanner(System.in);
            System.out.println("\n\n1. Display Employee Information" + "\n2. Add Employee"
                    + "\n3. Search for Employee" + "\n4. Quit");
            System.out.print("\nEnter Menu Option: ");
            choice = input.nextInt();
            switch (choice) {

                case 1:
                    //PRINT EMPLOYEE REGISTRY WITHOUT BRACKETS
                    System.out.print(String.format("%s", employeeMap.keySet().toString().replaceAll("[\\[\\]]", "")));
                    break;

                case 2:
                    //ADD EMPLOYEE
                    addNewEmployee(employeeMap);
                    break;

                case 3:
                    //SEARCH FOR EMPLOYEE, DISPLAY DATA
                    searchForEmployee(employeeMap, idMap);
                    break;
            }
        } while (choice != 4);

    }

    /**
     * This will add a new employee to employeeMap, using data from user
     *
     * @param employeeMap will be the employeeMap used to store the employee
     * object, as well as the attendance. Since this employee is new, the
     * attendance integer is set to 1
     */
    public static void addNewEmployee(HashMap<Object, Integer> employeeMap) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter employee ID: ");
        String id = s.nextLine();
        System.out.println("Please enter first name: ");
        String fName = s.nextLine();
        System.out.println("Please enter last name: ");
        String lName = s.nextLine();
        System.out.println("Please enter employee salary: ");
        String salary = s.nextLine();

        Employee e = new Employee(id, fName, lName, Double.parseDouble(salary));
        employeeMap.put(e, 1);

    }

    /**
     * This will search for the employee by obtaining iD from user, and search
     * for that iD using idMap
     *
     * @param employeeMap will be used to get the attendance of the employee, by
     * using the value (employee object) from idMap to find the value (int
     * attendance) of employeeMap employeeMap
     * @param idMap is the map that connects employee iD to the employee object
     */
    public static void searchForEmployee(HashMap<Object, Integer> employeeMap,
            HashMap<String, Object> idMap) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the Employee ID to search: ");
        String searchID = s.nextLine();
        if (idMap.containsKey(searchID)) {

            int x = employeeMap.get(idMap.get(searchID));

            System.out.println(idMap.get(searchID) + "\nEmployee was present "
                    + x + " times.");

        } else {
            System.out.println("\nEmployee does not exist.");
        }

    }

}
