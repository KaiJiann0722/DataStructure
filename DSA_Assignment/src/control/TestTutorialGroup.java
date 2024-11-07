package control;

import adt.*;
import entity.*;
import dao.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Loh Voon Yee
 */
public class TestTutorialGroup {

    Scanner scanner = new Scanner(System.in);
    ListInterface<TutorialGroup> groupList = new ArrayList<>();
    TutorialGroupDAO tutorialGroupDAO = new TutorialGroupDAO();

    public TestTutorialGroup() {
        groupList = (ArrayList) tutorialGroupDAO.retrieveFromFile();
    }

    public void runTutorialGroupMaintainence() {
        System.out.println("Welcome to the Tutorial Group Management App!");
        int choice = 0;
        while (choice != 9) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Change Tutorial Group");
            System.out.println("4. Find student");
            System.out.println("5. Display Student");
            System.out.println("6. Filter Tutorial Group");
            System.out.println("7. Generate Report");
            System.out.println("8. Add Tutorial Group");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()
            char next;
            switch (choice) {
                case 1:
                    do {
                        addStudent();
                        tutorialGroupDAO.saveToFile(groupList);
                        System.out.print("Do you want to add more student? (Y/N): ");
                        next = scanner.next().toUpperCase().charAt(0);
                    } while (next != 'N');
                    break;
                case 2:
                    do {
                        removeStudent();
                        tutorialGroupDAO.saveToFile(groupList);
                        System.out.print("Do you want to delete more student? (Y/N): ");
                        next = scanner.next().toUpperCase().charAt(0);
                    } while (next != 'N');
                    break;
                case 3:
                    do {
                        changeTutorialGroup();
                        tutorialGroupDAO.saveToFile(groupList);
                        System.out.print("Do you want to change more student? (Y/N): ");
                        next = scanner.next().toUpperCase().charAt(0);
                    } while (next != 'N');
                    break;
                case 4:
                    do {
                        findStudent();
                        System.out.print("Do you want to find more student? (Y/N): ");
                        next = scanner.next().toUpperCase().charAt(0);
                    } while (next != 'N');
                    break;
                case 5:
                    do {
                        showAllStudentInGroup();
                        System.out.print("Do you want to view other group? (Y/N): ");
                        next = scanner.next().toUpperCase().charAt(0);
                    } while (next != 'N');

                    break;
                case 6:
                    do {
                        filterTutorialGroup();
                        System.out.print("Do you want to filter again? (Y/N): ");
                        next = scanner.next().toUpperCase().charAt(0);
                    } while (next != 'N');
                    break;
                case 7:
                    showReport();
                    break;

                case 8:
                    do {
                        addTutorialGroup();
                        tutorialGroupDAO.saveToFile(groupList);
                        System.out.print("Do you want to add more tutorial group? (Y/N): ");
                        next = scanner.next().toUpperCase().charAt(0);
                    } while (next != 'N');
                    break;
                case 9:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        TestTutorialGroup tg = new TestTutorialGroup();
        tg.runTutorialGroupMaintainence();
    }

    public void addStudent() {
        //List out all avaliable tutorial group and let user select a group
        showAllTutorialGroup();
        System.out.println("0) Back");
        System.out.print("Select a tutorial group from above (Enter sequence number only): ");
        int group = scanner.nextInt();
        scanner.nextLine();
        if (group != 0) {
            boolean repeated = false;
            String id;
            SortedLinkedList<Student> tempStudentList = new SortedLinkedList<Student>();

            //get all student of group for validation
            for (int i = 1; i <= groupList.size(); i++) {
                Iterator<Student> itr = groupList.get(i).getStudents().getIterator();
                while (itr.hasNext()) {
                    tempStudentList.add(itr.next());
                }
            }

            //Getting Student Information
            do {
                System.out.print("\nEnter new student's ID: ");
                id = scanner.nextLine().toUpperCase();

                if (tempStudentList.contains(new Student(id))) {
                    System.out.println("This ID already exists, please enter other id");
                    repeated = true;
                } else {
                    repeated = false;
                }
            } while (repeated);

            System.out.print("Enter new student's name: ");
            String name = scanner.nextLine();
            System.out.print("Enter student's email:");
            String email = scanner.nextLine();

            //Adding new student & determine the successful
            boolean success = groupList.get(group).getStudents().add(new Student(id, name, email, groupList.get(group).getGroupNumber()));

            if (success) {
                System.out.println("New student successfully added to group " + groupList.get(group).getGroupNumber());
            } else {
                System.out.println("Failed to add new student!");
            }
            return;
        } else {
            return; //back to menu
        }

    }

    public void removeStudent() {
        //List out all avaliable tutorial group and let user select a group
        showAllTutorialGroup();
        System.out.print("Select a tutorial group from above (Enter sequence number only): ");
        int group = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        //Show all student in tutorial group
        System.out.println(groupList.get(group));
        System.out.print("Enter a student ID to remove(non-case sensetive): ");
        String id = scanner.nextLine().toUpperCase();

        if (groupList.get(group).getStudents().getElement(new Student(id)) != null) {
            System.out.println("Student Information:");
            System.out.println("========================================================================");
            System.out.println("    ID     |        Name        |             Email              | Group ");
            System.out.println(groupList.get(group).getStudents().getElement(new Student(id)));
            System.out.println("========================================================================");
            System.out.print("\nAre you confirm to remove this student?(Y/N): ");
            char confrim = scanner.nextLine().toUpperCase().charAt(0);

            if (confrim == 'Y') {
                boolean success = groupList.get(group).getStudents().remove(new Student(id));
                if (success) {
                    System.out.println("Removed successfully.");
                } else {
                    System.out.println("Failed to remove.");
                }
            }
        } else {
            System.out.println("No such student in this group!");
        }
    }

    public void changeTutorialGroup() {
        //List out all avaliable tutorial group and let user select a group
        showAllTutorialGroup();
        System.out.print("Select a tutorial group from above (Enter sequence number only): ");
        int groupFrom = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        //Show all student in tutorial group
        System.out.println(groupList.get(groupFrom));
        System.out.print("Enter a student ID to select: ");
        String id = scanner.nextLine().toUpperCase();

        //Select another group to change
        if (groupList.get(groupFrom).getStudents().contains(new Student(id))) {
            showAllTutorialGroup();
            System.out.print("Select a tutorial group to change the student's group (Enter sequence number only): ");
            int groupTo = scanner.nextInt();
            scanner.nextLine();
            //process
            Student tempStudent = groupList.get(groupFrom).getStudents().removeAndReturn(new Student(id));
            tempStudent.setGroupNumber(groupList.get(groupTo).getGroupNumber());
            boolean success = groupList.get(groupTo).getStudents().add(tempStudent);

            if (tempStudent != null && success) {
                System.out.println("Student changed group successfully.");
            } else {
                System.out.println("Student unsuccessful to change group.");
            }
        } else {
            System.out.println("No such student");
        }
    }

    public void findStudent() {

        SortedLinkedList<Student> tempStudentList = new SortedLinkedList<Student>();
        Student tempStudent = new Student();
        boolean found = false;

        System.out.println("\n");
        System.out.println("Search method:");
        System.out.println("1) Student In a group");
        System.out.println("2) All student");
        System.out.print("Choose a method: ");
        int method = scanner.nextInt();
        scanner.nextLine();

        if (method == 1) {
            showAllTutorialGroup();
            System.out.print("Select a tutorial group from above (Enter sequence number only): ");
            int group = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter student ID to search: ");
            String searchID = scanner.nextLine().toUpperCase();

            tempStudent.setId(searchID);

            //get the student with that id
            Iterator<Student> itr = groupList.get(group).getStudents().getIterator();
            while (itr.hasNext()) {
                tempStudent = itr.next();
                if (tempStudent.getId().startsWith(searchID)) {
                    tempStudentList.add(tempStudent);
                    found = true;
                }
            }
            //got then display, else no this student
            if (found) {
                System.out.println("========================================================================");
                System.out.println("    ID     |        Name        |             Email              | Group ");
                System.out.print(tempStudentList);
                System.out.println("========================================================================");
            } else {
                System.out.println("\nNo such student ID!");
            }
        } else if (method == 2) {
            SortedLinkedList<Student> tempStudentList2 = new SortedLinkedList<Student>();
            System.out.print("Enter student ID to search: ");
            String searchID = scanner.nextLine().toUpperCase();

            //get all student of group
            for (int i = 1; i <= groupList.size(); i++) {
                Iterator<Student> itr = groupList.get(i).getStudents().getIterator();
                while (itr.hasNext()) {
                    tempStudentList.add(itr.next());
                }
            }
            Iterator<Student> itr = tempStudentList.getIterator();

            //Find the student in all student
            while (itr.hasNext()) {
                tempStudent = itr.next();
                if (tempStudent.getId().startsWith(searchID)) {
                    tempStudentList2.add(tempStudent);
                    found = true;
                }
            }

            //got then display, else no this student
            if (found) {
                System.out.println("========================================================================");
                System.out.println("    ID     |        Name        |             Email              | Group ");
                System.out.print(tempStudentList2);
                System.out.println("========================================================================");
            } else {
                System.out.println("\nNo such student ID!");
            }

        }
    }

    public void showAllStudentInGroup() {
        showAllTutorialGroup();
        System.out.print("Select a tutorial group from above (Enter sequence number only): ");
        int group = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        System.out.println("\n" + groupList.get(group));
        System.out.println("Total student in this group: " + groupList.get(group).getStudents().getNumberOfEntries());
    }

    public void filterTutorialGroup() {

        int counter = 0;

        System.out.println("\n\nOperator - ");
        System.out.println("1. <");
        System.out.println("2. >");
        System.out.println("3. =");
        System.out.print("Select an Operator: ");
        int op = scanner.nextInt();

        System.out.print("Enter a student number: ");
        int num = scanner.nextInt();

        System.out.println("\nResult :");
        System.out.println(" Group | Tutor ID |      Tutor Name      | Number of Student");
        System.out.println("------------------------------------------------------------");
        if (op == 1) {
            for (int i = 1; i <= groupList.size(); i++) {
                if (groupList.get(i).getStudents().getNumberOfEntries() < num) {
                    System.out.printf("%6d | %7s  | %-20s | %9d\n",
                            groupList.get(i).getGroupNumber(),
                            groupList.get(i).getTutorId(),
                            groupList.get(i).getTutorName(),
                            groupList.get(i).getStudents().getNumberOfEntries());
                    counter++;
                }
            }
            System.out.println("------------------------------------------------------------");
            System.out.println("These are " + counter + " group(s) less than " + num + " student(s).");
        } else if (op == 2) {
            for (int i = 1; i <= groupList.size(); i++) {
                if (groupList.get(i).getStudents().getNumberOfEntries() > num) {
                    System.out.printf("%6d | %7s  | %-20s | %9d\n",
                            groupList.get(i).getGroupNumber(),
                            groupList.get(i).getTutorId(),
                            groupList.get(i).getTutorName(),
                            groupList.get(i).getStudents().getNumberOfEntries());
                    counter++;
                }
            }
            System.out.println("------------------------------------------------------------");
            System.out.println("These are " + counter + " group(s) more than " + num + " student(s).");
        } else {
            for (int i = 1; i <= groupList.size(); i++) {
                if (groupList.get(i).getStudents().getNumberOfEntries() == num) {
                    System.out.printf("%6d | %7s  | %-20s | %9d\n",
                            groupList.get(i).getGroupNumber(),
                            groupList.get(i).getTutorId(),
                            groupList.get(i).getTutorName(),
                            groupList.get(i).getStudents().getNumberOfEntries());
                    counter++;
                }
            }
            System.out.println("------------------------------------------------------------");
            System.out.println("There are " + counter + " group(s) that have " + num + " student(s).");
        }

    }

    public void showReport() {
        int count = 0;
        System.out.println("");
        for (int i = 1; i <= groupList.size(); i++) {
            System.out.println("");
            System.out.println("========================================================================");
            System.out.print(groupList.get(i));
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Total student in this group: " + groupList.get(i).getStudents().getNumberOfEntries());
            System.out.println("========================================================================");
            count += groupList.get(i).getStudents().getNumberOfEntries();
        }
        System.out.println("\nTotal Number of Tutorial Group: " + groupList.size());
        System.out.println("Total Number of All Student: " + count);

    }

    public void showAllTutorialGroup() {
        System.out.println("\n\nTutorial Group - ");
        for (int i = 1; i <= groupList.size(); i++) {
            System.out.println(i + ") Group " + groupList.get(i).getGroupNumber());
        }
    }

    public void addTutorialGroup() {

        boolean repeated = false;
        boolean stop = false;
        int group;

        do {
            System.out.print("\nEnter tutorial group number(0 to exit): ");
            group = scanner.nextInt();
            scanner.nextLine();

            //if not repeated then turn off loop
            for (int i = 1; i <= groupList.size(); i++) {
                if (groupList.get(i).getGroupNumber() == group && !stop) {
                    repeated = true;
                    stop = true;
                } else if (i == groupList.size() && repeated && !stop) {
                    repeated = false;
                }
            }
            stop = false;
            //if repeated show error message
            if (repeated) {
                System.out.println("This number have taken! Please try again.");

            }
        } while (repeated);

        if (group != 0) {
            //Getting Student Information
            System.out.print("\nEnter tutor ID: ");
            String tutorID = scanner.nextLine().toUpperCase();
            System.out.print("Enter tutor name: ");
            String tutorName = scanner.nextLine();

            //Adding new student & determine the successful
            boolean success = groupList.add(new TutorialGroup(group, tutorID, tutorName));

            if (success) {
                System.out.println("New tutorial group successfully added.");
            } else {
                System.out.println("Failed to add new tutorial group!");
            }
            return;
        } else {
            return; //back to menu
        }
    }
}
