package boundary;

import adt.DoublyLinkedList;
import adt.ListInterface;
import java.util.Scanner;
import entity.Tutor;

public class TutorMaintenanceUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.print("\n=========================================================\n"
                + "                Tutor Management Main Menu      \n"
                + "=========================================================\n"
                + "1.  Add Tutor\n"
                + "2.  Update Tutor\n"
                + "3.  Search Tutor\n"
                + "4.  Remove Tutor\n"
                + "5.  Display All Tutors\n"
                + "6.  Filter Tutor\n"
                + "7.  History and Versioning\n"
                + "8.  Generate Report\n"
                + "9.  Exit Tutor Management Main Menu\n\nPlease enter choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getUpdateMenu() {
        System.out.print("\n=========================================================\n"
                + "                Update Tutor Details Menu      \n"
                + "=========================================================\n"
                + "1.  Update Particular Info\n"
                + "2.  Update All Info\n"
                + "3.  Exit This Menu\n\nPlease enter choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getTutorUpdateMenuChoice() {
        System.out.print("1.  Update Name\n"
                + "2.  Update Age\n"
                + "3.  Update Gender\n"
                + "4.  Update Email\n"
                + "5.  Update Contact Number\n"
                + "6.  Back to Update Main Menu\n\nWhat information do you want to update? : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getSearchMainMenu() {
        System.out.print("\n=========================================================\n"
                + "                Search Tutor Details Menu      \n"
                + "=========================================================\n"
                + "1.  Tutor ID\n"
                + "2.  Tutor Name\n"
                + "3.  Tutor Age\n"
                + "4.  Tutor Gender\n"
                + "5.  Tutor Email\n"
                + "6.  Tutor Contact Number\n"
                + "7.  Back To Tutor Maintainence Menu\n\nPlease enter choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getRemoveFilterMenu() {
        System.out.print("\n=========================================================\n"
                + "                Filter Tutor Details Menu      \n"
                + "=========================================================\n"
                + "1.  Tutor Name\n"
                + "2.  Tutor Age\n"
                + "3.  Tutor Gender\n"
                + "4.  Tutor Email\n"
                + "5.  Tutor Contact Number\n"
                + "6.  Back To Tutor Maintainence Menu\n\nPlease enter choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getRemoveChoice() {
        System.out.print("\n=========================================================\n"
                + "                Remove Tutor Details Menu      \n"
                + "=========================================================\n"
                + "1.  Remove By Tutor ID\n"
                + "2.  Remove By Filter\n"
                + "3.  Back To Tutor Maintainence Menu\n\nPlease enter choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getFilterMenu(ListInterface<Integer> usedFilters) {
        ListInterface<String> filterOptions = new DoublyLinkedList<>();
        filterOptions.add("Tutor ID");
        filterOptions.add("Tutor Name");
        filterOptions.add("Tutor Age");
        filterOptions.add("Tutor Gender");
        filterOptions.add("Tutor Email");
        filterOptions.add("Tutor Contact Number");
        System.out.print("\n=========================================================\n"
                + "                Filter Tutor Details Menu      \n"
                + "=========================================================\n");
        for (int i = 0; i < filterOptions.size(); i++) {
            String filterOption = filterOptions.get(i);
            if (usedFilters.contains(i + 1)) {
                System.out.println((i + 1) + ". " + filterOption + " (This filter option has already been used)");
            } else {
                System.out.println((i + 1) + ". " + filterOption);
            }
        }
        System.out.println(filterOptions.size() + 1 + ". Revert to the Original List");
        System.out.println("8. Back To Tutor Maintainence Menu");
        System.out.print("Please Enter your choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getAgeDetailsMenu() {
        System.out.print("\n=========================================================\n"
                + "                Search Tutor by Age      \n"
                + "=========================================================\n"
                + "1.  Search by Age (starting range)\n"
                + "2.  Search by Age (ending range)\n"
                + "3.  Search by Age (between range)\n"
                + "4.  Search by Age (equals)\n"
                + "5.  Back to Search Main Page\n\nPlease enter your choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getNameDetailsMenu() {
        System.out.print("\n=========================================================\n"
                + "                Search Tutor Name      \n"
                + "=========================================================\n"
                + "1.  Search Name Starts With\n"
                + "2.  Search Name Ends With\n"
                + "3.  Search Name Contains\n"
                + "4.  Back to Search Main Page\n\nPlease enter choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getSortDetailsMenu() {
        System.out.print("\n=========================================================\n"
                + "                Sort By List      \n"
                + "=========================================================\n"
                + "1.  Sort By Tutor ID\n"
                + "2.  Sort By Tutor Name\n"
                + "3.  Sort By Tutor Age\n"
                + "4.  Sort By Tutor Email\n"
                + "5.  Sort By Tutor ContactNo\n"
                + "6.  Back to Report Main Menu\n\nPlease enter choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getEmailDetailsMenu() {
        System.out.print("\n=========================================================\n"
                + "                Search Tutor Email      \n"
                + "=========================================================\n"
                + "1.  Search Email Starts With\n"
                + "2.  Search Email Ends With\n"
                + "3.  Search Email Contains\n"
                + "4.  Back to Search Main Page\n\nPlease enter choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getPhoneDetailsMenu() {
        System.out.print("\n=========================================================\n"
                + "                Search Tutor Phone      \n"
                + "=========================================================\n"
                + "1.  Search Phone Number Starts With\n"
                + "2.  Search Phone Number Ends With\n"
                + "3.  Search Phone Number Contains\n"
                + "4.  Back to Search Main Page\n\nPlease enter choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getSequenceType() {
        System.out.print("\n=========================================================\n"
                + "                Sequence Type      \n"
                + "=========================================================\n"
                + "1.  Ascending Order\n"
                + "2.  Descending Order\n"
                + "3.  Back To Tutor Maintainence Menu\n\nPlease enter choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public int getDisplayType() {
        System.out.print("\n=========================================================\n"
                + "                Display Tutors Direction      \n"
                + "=========================================================\n"
                + "1.  Forward\n"
                + "2.  Backward\n"
                + "3.  Back To Tutor Maintainence Menu\n\nChoose a direction : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void listAllTutors(String outputStr) {
        System.out.println("\nList of Tutors:\n" + outputStr);
    }

    public void printTutorDetails(Tutor tutor) {
        System.out.printf("| %-10s | %-20s | %-3d | %-6c | %-25s | %-15s |\n",
                tutor.getTutorID(), tutor.getTutorName(), tutor.getAge(), tutor.getGender(), tutor.getEmail(), tutor.getContactNo());
        System.out.print("--------------------------------------------------------------------------------------------------\n");
    }

    public void printTableHeader() {
        System.out.println("\n--------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-3s | %-6s | %-25s | %-15s |\n", "Tutor ID", "Tutor Name", "Age", "Gender", "Email", "Contact No");
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public void printModifyTableHeader() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-3s | %-6s | %-25s | %-15s | %-15s |\n", "Tutor ID", "Tutor Name", "Age", "Gender", "Email", "Contact No", "Modified Time");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }

    public void printTutorModifyDetails(Tutor tutor, String time) {
        System.out.printf("| %-10s | %-20s | %-3d | %-6c | %-25s | %-15s | %-15s |\n",
                tutor.getTutorID(), tutor.getTutorName(), tutor.getAge(), tutor.getGender(), tutor.getEmail(), tutor.getContactNo(), time);
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }

    public void displayTutorsSideBySide(Tutor newTutor, Tutor oldTutor) {
        System.out.println("\n---------------------------------------------------------------------------------------       ---------------------------------------------------------------------------------------");
        System.out.println("|                         Current Data                                                |       |                                         Previous Data                               |");
        System.out.println("---------------------------------------------------------------------------------------       ---------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-15s | %-3s | %-6s | %-20s | %-12s | %5s | %-12s | %-15s | %-3s | %-6s | %-20s | %-12s |\n",
                "ID", "Name", "Age", "Gender", "Email", "Contact No", "", "ID", "Name", "Age", "Gender", "Email", "Contact No");
        System.out.println("---------------------------------------------------------------------------------------       ---------------------------------------------------------------------------------------");
        String tutorID = oldTutor.getTutorID();
        String tutorName = oldTutor.getTutorName();
        String age = String.valueOf(oldTutor.getAge());
        String gender = String.valueOf(oldTutor.getGender());
        String email = oldTutor.getEmail();
        String contactNo = oldTutor.getContactNo();
        String newtutorID = newTutor.getTutorID();
        String newtutorName = newTutor.getTutorName();
        String newAge = String.valueOf(newTutor.getAge());
        String newGender = String.valueOf(newTutor.getGender());
        String newEmail = newTutor.getEmail();
        String newContactNo = newTutor.getContactNo();
        System.out.printf("| %-12s | %-15s | %-3s | %-6s | %-20s | %-12s | %5s | %-12s | %-15s | %-3s | %-6s | %-20s | %-12s |\n",
                newtutorID, newtutorName, newAge, newGender, newEmail, newContactNo, "<---",
                tutorID, tutorName, age, gender, email, contactNo);
        System.out.println("---------------------------------------------------------------------------------------       ---------------------------------------------------------------------------------------");
    }

    public void printTitle(String title) {
        System.out.println("\n============================================================================================");
        System.out.println("                                       " + title + "                                           ");
        System.out.println("=============================================================================================");
    }

    public String inputTutorID() {
        System.out.printf("%20s: ", "Enter Tutor ID (eg. T1001)");
        String tutorID = scanner.nextLine();
        return tutorID;
    }

    public String inputTutorName() {
        System.out.printf("%-20s: ", "Enter Tutor Name");
        String tutorName = scanner.nextLine();
        return tutorName;
    }

    public int inputAge() {
        System.out.printf("%-20s: ", "Enter Age");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return age;
    }

    public char inputGender() {
        System.out.printf("%-20s: ", "Enter Gender (M/F)");
        char gender = scanner.nextLine().charAt(0);
        return gender;
    }

    public String inputEmail() {
        System.out.printf("%-20s: ", "Enter Email");
        String email = scanner.nextLine();
        return email;
    }

    public String inputContactNo() {
        System.out.printf("%-20s: ", "Enter Contact No");
        String contactNo = scanner.nextLine();
        return contactNo;
    }

    public char inputYesNo(String message) {
        System.out.print(message);
        char yesNo = scanner.nextLine().charAt(0);
        return yesNo;
    }

    public int inputStartAge() {
        System.out.printf("%-20s: ", "Enter Start Age");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return age;
    }

    public int inputEndAge() {
        System.out.printf("%-20s: ", "Enter End Age");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return age;
    }

    public char inputUndoRemove() {
        System.out.printf("Options:\n");
        System.out.printf("  Y - Remove This Data from Current Tutor List\n");
        System.out.printf("  N - Skip this state and continue undo\n");
        System.out.printf("  X - Exit history mode\n");
        System.out.print("Enter your choice (Y/N/X): ");
        char choice = scanner.nextLine().charAt(0);
        return choice;
    }

    public char inputUndoAdd() {
        System.out.printf("Options:\n");
        System.out.printf("  Y - Add Back This Data to Current Tutor List\n");
        System.out.printf("  N - Skip this state and continue undo\n");
        System.out.printf("  X - Exit history mode\n");
        System.out.print("Enter your choice (Y/N/X): ");
        char choice = scanner.nextLine().charAt(0);
        return choice;
    }

    public char inputUndoReplace() {
        System.out.printf("Options:\n");
        System.out.printf("  Y - Replace Back This Data to Current Tutor List\n");
        System.out.printf("  N - Skip this state and continue undo\n");
        System.out.printf("  X - Exit history mode\n");
        System.out.print("Enter your choice (Y/N/X): ");
        char choice = scanner.nextLine().charAt(0);
        return choice;
    }

    public int inputViewModificationChoice() {
        System.out.println("\nModification Menu:");
        System.out.println("1. View Additions");
        System.out.println("2. View Deletions");
        System.out.println("3. View Updates");
        System.out.println("4. View All Modifications");
        System.out.println("5. Back To Tutor Maintainence Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

    public int getInputReportMenuChoice() {
        System.out.println("\n1. Today's Modify Report");
        System.out.println("2. Sorting Tutor Details");
        System.out.println("3. Categorize Tutors By Gender");
        System.out.println("4. Back To Tutor Maintainence Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

}
