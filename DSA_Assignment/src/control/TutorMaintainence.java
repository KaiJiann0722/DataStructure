package control;

import entity.Tutor;
import adt.*;
import boundary.TutorMaintenanceUI;
import dao.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import utility.MessageUI;

/**
 *
 * @author Tan Kai Jiann
 */
public class TutorMaintainence implements Serializable {

    private ListInterface<Tutor> tutorList = new DoublyLinkedList<>();
    private ListInterface<ModificationRecord> modificationRecords = new DoublyLinkedList<>();
    private TutorDAO tutorDAO = new TutorDAO();
    private TutorMaintenanceUI tutorUI = new TutorMaintenanceUI();

    public TutorMaintainence() {
        tutorList = tutorDAO.retrieveFromFile();
        Tutor.setNumTutor(tutorDAO.retrieveFromFile1());
    }

    private void todayModify(Tutor modifiedTutor, String modificationType) {
        Date currentDate = new Date();
        ModificationRecord record = new ModificationRecord(modifiedTutor, currentDate, modificationType);
        modificationRecords.add(record);
    }

    public void runTutorMaintenance() {
        int choice = 0;
        do {
            choice = tutorUI.getMenuChoice();
            switch (choice) {
                case 1:
                    addTutor();
                    tutorDAO.saveToFile(tutorList);
                    break;
                case 2:
                    updateTutor();
                    tutorDAO.saveToFile(tutorList);
                    break;
                case 3:
                    searchTutor();
                    break;
                case 4:
                    removeTutor();
                    tutorDAO.saveToFile(tutorList);
                    break;
                case 5:
                    displayAllTutor();
                    break;
                case 6:
                    filterTutor();
                    break;
                case 7:
                    history();
                    break;
                case 8:
                    generateReport();
                    tutorDAO.saveToFile(tutorList);
                    break;
                case 9:
                    MessageUI.success("Exiting System");
                    break;
                default:
                    MessageUI.error("Invalid Choice");
            }
        } while (choice != 9);
    }

    public void todayModifyReport() {
        if (modificationRecords.isEmpty()) {
            MessageUI.hint("No Modifications Made yet");
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String todayDate = dateFormat.format(new Date());
            int choice = tutorUI.inputViewModificationChoice();
            if (choice >= 1 && choice <= 4) {
                System.out.println("\nToday's Modification (" + todayDate + "):");
                System.out.println("------------------------------------------------------");
            }
            switch (choice) {
                case 1:
                    printModificationsByType(todayDate, "Addition");
                    break;
                case 2:
                    printModificationsByType(todayDate, "Deletion");
                    break;
                case 3:
                    printModificationsByType(todayDate, "Update");
                    break;
                case 4:
                    printModificationsByType(todayDate, "Addition");
                    printModificationsByType(todayDate, "Deletion");
                    printModificationsByType(todayDate, "Update");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void printModificationsByType(String todayDate, String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm::ss");
        System.out.println(type + " Modifications:");
        boolean found = false;
        int count = 0;
        for (int i = 0; i < modificationRecords.size(); i++) {
            ModificationRecord record = modificationRecords.get(i);
            Date modificationDate = record.getModificationDate();
            String modificationDateString = dateFormat.format(modificationDate);
            if (modificationDateString.equals(todayDate) && record.getModificationType().equals(type)) {
                if (!found) {
                    tutorUI.printModifyTableHeader();
                }
                Tutor modifiedTutor = record.getModifiedTutor();
                tutorUI.printTutorModifyDetails(modifiedTutor, timeFormat.format(modificationDate));
                count++;
                found = true;
            }
        }
        if (found) {
            MessageUI.hint("Total " + type + " Modifications Action Made: " + count);
        } else {
            MessageUI.hint("No " + type + " Modificitions Action Made");
        }
        System.out.println("");
    }

    public void categorizeByGender() {
        ListInterface<Tutor> maleTutor = tutorList.filter(t -> t.getGender() == 'M');
        ListInterface<Tutor> femaleTutor = tutorList.filter(t -> t.getGender() == 'F');
        double total = tutorList.size();
        tutorUI.printTitle("Tutors List (Male)");
        printTutor(maleTutor);
        System.out.println("Total Number of Male Tutors : " + maleTutor.size());
        System.out.println("Percentage of Male Tutors : " + String.format("%.2f", (maleTutor.size() / total) * 100) + "%");
        tutorUI.printTitle("Tutors List (Female)");
        printTutor(femaleTutor);
        System.out.println("Total Number of Female Tutors : " + femaleTutor.size());
        System.out.println("Percentage of Female Tutors : " + String.format("%.2f", (femaleTutor.size() / total) * 100) + "%");

    }

    public void generateReport() {
        int choice = 0;
        do {
            tutorUI.printTitle("REPORT LIST");
            choice = tutorUI.getInputReportMenuChoice();
            switch (choice) {
                case 1:
                    todayModifyReport();
                    break;
                case 2:
                    sort();
                    break;
                case 3:
                    categorizeByGender();
                    break;
                case 4:
                    MessageUI.success("Exit Succesffully");
                    break;
                default:
                    MessageUI.error("Invalid Choice");
            }
        } while (choice != 4);
    }

    public void sort() {
        int choice = tutorUI.getSortDetailsMenu();
        if (choice != 6) {
            int sequenceType = tutorUI.getSequenceType();
            if (sequenceType != 3) {
                switch (choice) {
                    case 1:
                        sortById(sequenceType);
                        break;
                    case 2:
                        sortByName(sequenceType);
                        break;
                    case 3:
                        sortByAge(sequenceType);
                        break;
                    case 4:
                        sortByEmail(sequenceType);
                        break;
                    case 5:
                        sortByContactNo(sequenceType);
                        break;
                    default:
                        MessageUI.failed("Invalid Choice");
                }
            }
        }
    }

    public void sortById(int sequenceType) {
        switch (sequenceType) {
            case 1:
                tutorList.sort((t1, t2) -> {
                    return t1.getTutorID().compareTo(t2.getTutorID()) > 0 ? 1 : -1;
                });
                break;
            case 2:
                tutorList.sort((t1, t2) -> {
                    return t2.getTutorID().compareTo(t1.getTutorID()) > 0 ? 1 : -1;
                });
                break;
        }
        printTutor(tutorList);
    }

    public void sortByName(int sequenceType) {
        switch (sequenceType) {
            case 1:
                tutorList.sort((t1, t2) -> {
                    return t1.getTutorName().compareTo(t2.getTutorName()) > 0 ? 1 : -1;
                });
                break;
            case 2:
                tutorList.sort((t1, t2) -> {
                    return t2.getTutorName().compareTo(t1.getTutorName()) > 0 ? 1 : -1;
                });
                break;
        }
        printTutor(tutorList);
    }

    public void sortByAge(int sequenceType) {
        switch (sequenceType) {
            case 1:
                tutorList.sort((t1, t2) -> {
                    return t1.getAge() > t2.getAge() ? 1 : -1;
                });
                break;
            case 2:
                tutorList.sort((t1, t2) -> {
                    return t2.getAge() > t1.getAge() ? 1 : -1;
                });
                break;
        }
        printTutor(tutorList);
    }

    public void sortByEmail(int sequenceType) {
        switch (sequenceType) {
            case 1:
                tutorList.sort((t1, t2) -> {
                    return t1.getEmail().compareToIgnoreCase(t2.getEmail()) > 0 ? 1 : -1;
                });
                break;
            case 2:
                tutorList.sort((t1, t2) -> {
                    return t2.getEmail().compareToIgnoreCase(t1.getEmail()) > 0 ? 1 : -1;
                });
                break;
        }
        printTutor(tutorList);
    }

    public void sortByContactNo(int sequenceType) {
        switch (sequenceType) {
            case 1:
                tutorList.sort((t1, t2) -> {
                    return t1.getContactNo().compareTo(t2.getContactNo()) > 0 ? 1 : -1;
                });
                break;
            case 2:
                tutorList.sort((t1, t2) -> {
                    return t2.getContactNo().compareTo(t1.getContactNo()) > 0 ? 1 : -1;
                });
                break;
        }
        printTutor(tutorList);
    }

    public void history() {
        if (!modificationRecords.isEmpty()) {
            ListInterface<ModificationRecord> lastModified = modificationRecords.clone();
            int total = lastModified.size();
            boolean update = false;
            char opt = 'N';
            do {
                Tutor currentTutor = new Tutor();
                System.out.println("");
                MessageUI.hint("Previous Data" + " (Remaining " + (total--) + " Chance(s) to Undo)");
                ModificationRecord modify = lastModified.removeFromEnd();
                Tutor modifyTutor = modify.getModifiedTutor();
                String type = modify.getModificationType();
                tutorUI.printTitle("Latest Changes: " + type);
                if (type.equalsIgnoreCase("Addition")) {
                    tutorUI.printTableHeader();
                    tutorUI.printTutorDetails(modifyTutor);
                    if (tutorList.contains(modifyTutor)) {
                        opt = Character.toUpperCase(tutorUI.inputUndoRemove());
                        if (opt == 'Y') {
                            tutorList.remove(modifyTutor);
                            update = true;
                        }
                    } else {
                        MessageUI.hint("This Tutor already be removed from current Tutor List, Not allowed to remove");
                    }
                } else if (type.equalsIgnoreCase("Deletion")) {
                    tutorUI.printTableHeader();
                    tutorUI.printTutorDetails(modifyTutor);
                    if (!tutorList.contains(modifyTutor)) {
                        opt = Character.toUpperCase(tutorUI.inputUndoAdd());
                        if (opt == 'Y') {
                            tutorList.add(modifyTutor);
                            update = true;
                        }
                    } else {
                        MessageUI.hint("This Tutor already contains in current Tutor List, Not allowed to add back");
                    }
                } else {
                    currentTutor = tutorList.first(t -> t.getTutorID().equalsIgnoreCase(modifyTutor.getTutorID()));
                    if (currentTutor == null) {
                        tutorUI.printTableHeader();
                        tutorUI.printTutorDetails(modifyTutor);
                        MessageUI.hint("This Tutor already be removed from current Tutor List, Not allowed to replace back");
                    } else {
                        if (currentTutor.getTutorID().equals(modifyTutor.getTutorID())
                                && currentTutor.getTutorName().equalsIgnoreCase(modifyTutor.getTutorName())
                                && currentTutor.getAge() == modifyTutor.getAge()
                                && currentTutor.getGender() == modifyTutor.getGender()
                                && currentTutor.getEmail().equalsIgnoreCase(modifyTutor.getEmail())
                                && currentTutor.getContactNo().equalsIgnoreCase(modifyTutor.getContactNo())) {
                            tutorUI.printTableHeader();
                            tutorUI.printTutorDetails(modifyTutor);
                            MessageUI.hint("This Tutor Details same with current tutor List");
                        } else {
                            tutorUI.displayTutorsSideBySide(currentTutor, modifyTutor);
                            opt = Character.toUpperCase(tutorUI.inputUndoReplace());
                            if (opt == 'Y') {
                                tutorList.replace(tutorList.indexOf(currentTutor), modifyTutor);
                                update = true;
                            }
                        }
                    }

                }
                if (opt == 'X') {
                    MessageUI.success("Exit Successfully");
                    break;
                }
                if (update) {
                    switch (type) {
                        case "Addition":
                            MessageUI.success("This tutor already be removed from current tutor List");
                            todayModify(modifyTutor, "Deletion");
                            break;
                        case "Deletion":
                            MessageUI.success("This tutor already added back to current tutor List");
                            todayModify(modifyTutor, "Addition");
                            break;
                        case "Update":
                            MessageUI.success("This tutor already replace back to current tutor List");
                            todayModify(currentTutor, "Update");
                            break;
                    }
                    tutorDAO.saveToFile(tutorList);
                    break;
                }
            } while (total != 0);
            if (total == 0 && !update && opt != 'X') {
                System.out.println("");
                MessageUI.cancelled("No more chance to undo");
            }
        } else {
            MessageUI.error("No more undo operations available.");
        }
    }

    public void addTutor() {
        tutorUI.printTitle("Add Tutor");
        char cont = 'Y';
        do {
            MessageUI.otherMsg("New Tutor Details", 1);
            String tutorId = 'T' + String.format("%4d", Tutor.getNumTutor() + 1);;
            System.out.println(String.format("%-20s: %s", "Tutor ID", tutorId));
            String name = tutorUI.inputTutorName();
            int age = tutorUI.inputAge();
            char gender = Character.toUpperCase(tutorUI.inputGender());
            String email = tutorUI.inputEmail();
            String contactNo = tutorUI.inputContactNo();
            tutorUI.printTableHeader();
            tutorUI.printTutorDetails(new Tutor(tutorId, name, age, gender, email, contactNo));
            if (Character.toUpperCase(tutorUI.inputYesNo("Please make sure that all the details given above is correct (Y/N): ")) == 'Y') {
                Tutor tutor = new Tutor(name, age, gender, email, contactNo);
                tutorList.add(tutor);
                todayModify(tutor, "Addition");
                tutorDAO.saveToFile1(Tutor.getNumTutor());
                MessageUI.success("The above tutor has been added into list");
            } else {
                MessageUI.cancelled("Adding above tutor has been cancelled: ");
            }
            cont = tutorUI.inputYesNo("\nDo you want to add another tutor? (Y/N): ");
        } while (Character.toUpperCase(cont) == 'Y');
    }

    public void updateTutor() {
        char cont = 'N';
        int choice = 0;
        do {
            choice = tutorUI.getUpdateMenu();
            switch (choice) {
                case 1:
                    updateParticularInfo();
                    break;
                case 2:
                    updateAllInfo();
                    break;
                case 3:
                    MessageUI.success("Exit Sucessfully...");
                    break;
                default:
                    MessageUI.failed("Invalid Choice");
            }
        } while (choice != 3);
    }

    private boolean confirmUpdate(String fieldName, String newValue) {
        return Character.toUpperCase(tutorUI.inputYesNo("Confirm updating " + fieldName + " to " + newValue + "? (Y/N): ")) == 'Y';
    }

    private Tutor copyTutorDetails(Tutor updateTutor) {
        return new Tutor(updateTutor.getTutorID(), updateTutor.getTutorName(), updateTutor.getAge(), updateTutor.getGender(), updateTutor.getEmail(), updateTutor.getContactNo());
    }

    public void updateParticularInfo() {
        int choice = 0;
        boolean update = false;
        MessageUI.otherMsg("Update Tutor Details", 1);
        String id = tutorUI.inputTutorID();
        Tutor updateTutor = tutorList.first(t -> t.getTutorID().equalsIgnoreCase(id));
        if (updateTutor == null) {
            MessageUI.failed("Tutor ID not found: " + id);
        } else {
            char cont = 'N';
            do {
                Tutor oldTutor = copyTutorDetails(updateTutor);
                tutorUI.printTitle("Current Information");
                tutorUI.printTableHeader();
                tutorUI.printTutorDetails(updateTutor);
                choice = tutorUI.getTutorUpdateMenuChoice();
                switch (choice) {
                    case 1:
                        String name = tutorUI.inputTutorName();
                        if (update = confirmUpdate("Name", name)) {
                            updateTutor.setTutorName(name);
                        }
                        break;
                    case 2:
                        int age = tutorUI.inputAge();
                        if (update = confirmUpdate("Age", String.valueOf(age))) {
                            updateTutor.setAge(age);
                        }
                        break;
                    case 3:
                        char gender = Character.toUpperCase(tutorUI.inputGender());
                        if (update = confirmUpdate("Gender", String.valueOf(gender))) {
                            updateTutor.setGender(gender);
                        }
                        break;
                    case 4:
                        String email = tutorUI.inputEmail();
                        if (update = confirmUpdate("Email", email)) {
                            updateTutor.setEmail(email);
                        }
                        break;
                    case 5:
                        String contactNo = tutorUI.inputContactNo();
                        if (update = confirmUpdate("Contact No", contactNo)) {
                            updateTutor.setContactNo(contactNo);
                        }
                        break;
                    case 6:
                        MessageUI.success("Exit Successfully ...");
                        break;
                    default:
                        MessageUI.failed("Invalid Choice");
                }
                if (update) {
                    todayModify(oldTutor, "Update");
                    tutorUI.printTitle("Updated Information");
                    tutorUI.printTableHeader();
                    tutorUI.printTutorDetails(updateTutor);
                    MessageUI.success("The above tutor has been updated into list");
                } else {
                    MessageUI.cancelled("Updating above tutor has been cancelled: ");
                }
                cont = tutorUI.inputYesNo("\nDo you want to continue edit " + id + " Details (Y/N): ");
            } while (Character.toUpperCase(cont) == 'Y');
        }
    }

    public void updateAllInfo() {
        MessageUI.otherMsg("Update Tutor Details", 1);
        String id = tutorUI.inputTutorID();
        Tutor updateTutor = tutorList.first(t -> t.getTutorID().equalsIgnoreCase(id));
        if (updateTutor == null) {
            MessageUI.failed("Tutor ID not found: " + id);
        } else {
            Tutor oldTutor = copyTutorDetails(updateTutor);
            tutorUI.printTitle("Current Information");
            tutorUI.printTableHeader();
            tutorUI.printTutorDetails(updateTutor);
            String name = tutorUI.inputTutorName();
            int age = tutorUI.inputAge();
            char gender = Character.toUpperCase(tutorUI.inputGender());
            String email = tutorUI.inputEmail();
            String contactNo = tutorUI.inputContactNo();
            if (Character.toUpperCase(tutorUI.inputYesNo("Please make sure that all the details given above is correct (Y/N): ")) == 'Y') {
                updateTutor.setTutorName(name);
                updateTutor.setAge(age);
                updateTutor.setGender(gender);
                updateTutor.setEmail(email);
                updateTutor.setContactNo(contactNo);
                todayModify(oldTutor, "Update");
                MessageUI.success("The above tutor has been updated into list");
            } else {
                MessageUI.cancelled("Updating above tutor has been cancelled: ");
            }
        }
    }

    public void searchTutor() {
        char cont = 'N';
        int choice = 0;
        boolean isFound;
        ListInterface<Tutor> getTutorList = new DoublyLinkedList<>();
        do {
            isFound = false;
            choice = tutorUI.getSearchMainMenu();
            switch (choice) {
                case 1:
                    isFound = findById();
                    break;
                case 2:
                    getTutorList = findByName();
                    break;
                case 3:
                    getTutorList = findByAge();
                    break;
                case 4:
                    getTutorList = findByGender();
                    break;
                case 5:
                    getTutorList = findByEmail();
                    break;
                case 6:
                    getTutorList = findByPhone();
                    break;
                case 7:
                    MessageUI.success("Exit Secessfully");
                    break;
                default:
                    MessageUI.failed("Invalid Choice");
            }
            if (choice >= 1 && choice <= 6) {
                if (!getTutorList.isEmpty()) {
                    printTutor(getTutorList);
                } else if (!isFound) {
                    MessageUI.failed("Nothing tutors found");
                }
            }
        } while (choice != 7);
    }

    public void printTutor(ListInterface<Tutor> tList) {
        tutorUI.printTableHeader();
        for (int i = 0; i < tList.size(); i++) {
            tutorUI.printTutorDetails(tList.get(i));
        }
    }

    public boolean findById() {
        String id = tutorUI.inputTutorID();
        Tutor tutor = tutorList.first(t -> t.getTutorID().equalsIgnoreCase(id));
        if (tutor == null) {
            MessageUI.failed("Tutor ID not found: " + id);
            return false;
        } else {
            tutorUI.printTableHeader();
            tutorUI.printTutorDetails(tutor);
            return true;
        }
    }

    public ListInterface<Tutor> findByName() {
        int choice = 0;
        String name;
        do {
            choice = tutorUI.getNameDetailsMenu();
            switch (choice) {
                case 1:
                    name = tutorUI.inputTutorName();
                    return tutorList.filter(t -> t.getTutorName().startsWith(name));
                case 2:
                    name = tutorUI.inputTutorName();
                    return tutorList.filter(t -> t.getTutorName().endsWith(name));
                case 3:
                    name = tutorUI.inputTutorName();
                    return tutorList.filter(t -> t.getTutorName().contains(name));
                case 4:
                    MessageUI.success("Exit Successfully");
                    break;
                default:
                    MessageUI.cancelled("Invalid Choice");
            }
        } while (choice != 4);
        return new DoublyLinkedList<>();
    }

    public ListInterface<Tutor> findByAge() {
        int choice = 0, startRange, endRange, age;
        do {
            choice = tutorUI.getAgeDetailsMenu();
            switch (choice) {
                case 1:
                    startRange = tutorUI.inputStartAge();
                    return tutorList.filter(t -> t.getAge() >= startRange);
                case 2:
                    endRange = tutorUI.inputEndAge();
                    return tutorList.filter(t -> t.getAge() <= endRange);
                case 3:
                    startRange = tutorUI.inputStartAge();
                    endRange = tutorUI.inputEndAge();
                    return tutorList.filter(t -> t.getAge() >= startRange && t.getAge() <= endRange);
                case 4:
                    age = tutorUI.inputAge();
                    return tutorList.filter(t -> t.getAge() == age);
                case 5:
                    MessageUI.success("Exit Successfully");
                    break;
                default:
                    MessageUI.cancelled("Invalid Choice");
            }
        } while (choice != 5);
        return new DoublyLinkedList<>();
    }

    public ListInterface<Tutor> findByEmail() {
        int choice = 0;
        String email;
        do {
            choice = tutorUI.getEmailDetailsMenu();
            switch (choice) {
                case 1:
                    email = tutorUI.inputEmail();
                    return tutorList.filter(t -> t.getEmail().startsWith(email));
                case 2:
                    email = tutorUI.inputEmail();
                    return tutorList.filter(t -> t.getEmail().endsWith(email));
                case 3:
                    email = tutorUI.inputEmail();
                    return tutorList.filter(t -> t.getEmail().contains(email));
                case 4:
                    MessageUI.success("Exit Successfully");
                    break;
                default:
                    MessageUI.cancelled("Invalid Choice");
            }
        } while (choice != 4);
        return new DoublyLinkedList<>();
    }

    public ListInterface<Tutor> findByGender() {
        char gender = Character.toUpperCase(tutorUI.inputGender());
        return tutorList.filter(t -> t.getGender() == gender);
    }

    public ListInterface<Tutor> findByPhone() {
        int choice = 0;
        String phone;
        do {
            choice = tutorUI.getPhoneDetailsMenu();
            switch (choice) {
                case 1:
                    phone = tutorUI.inputContactNo();
                    return tutorList.filter(t -> t.getContactNo().startsWith(phone));
                case 2:
                    phone = tutorUI.inputContactNo();
                    return tutorList.filter(t -> t.getContactNo().endsWith(phone));
                case 3:
                    phone = tutorUI.inputContactNo();
                    return tutorList.filter(t -> t.getContactNo().contains(phone));
                case 4:
                    MessageUI.success("Exit Successfully ...");
                    break;
                default:
                    MessageUI.cancelled("Invalid Choice");
            }
        } while (choice != 4);
        return new DoublyLinkedList<>();
    }

    public void removeTutor() {
        char cont = 'N';
        int choice = 0;
        do {
            choice = tutorUI.getRemoveChoice();
            switch (choice) {
                case 1:
                    removeById();
                    break;
                case 2:
                    removeByFilter();
                    break;
                case 3:
                    MessageUI.success("Exit Successfully");
                    return;
                default:
                    MessageUI.error("Invalid Choice");
            }

            cont = tutorUI.inputYesNo("\nDo you want to remove another tutor? (Y/N): ");
        } while (Character.toUpperCase(cont) == 'Y');
    }

    public void removeById() {
        MessageUI.otherMsg("Remove Tutor Details", 1);
        String id = tutorUI.inputTutorID();
        Tutor removedTutor = tutorList.first(t -> t.getTutorID().equalsIgnoreCase(id));
        if (removedTutor == null) {
            MessageUI.failed("Tutor ID not found: " + id);
        } else {
            tutorUI.printTableHeader();
            tutorUI.printTutorDetails(removedTutor);
            if (Character.toUpperCase(tutorUI.inputYesNo("Are you sure you want to delete above tutor (Y/N): ")) == 'Y') {
                tutorList.remove(removedTutor);
                todayModify(removedTutor, "Deletion");
                MessageUI.success("The above tutor has been removed from list");
            } else {
                MessageUI.cancelled("Deleting above tutor has been cancelled: ");
            }
        }
    }

    public void removeByFilter() {
        char cont = 'N';
        int choice = 0;
        boolean isFound;
        ListInterface<Tutor> getRemoveList = new DoublyLinkedList<>();
        isFound = false;
        choice = tutorUI.getRemoveFilterMenu();
        switch (choice) {
            case 1:
                getRemoveList = findByName();
                break;
            case 2:
                getRemoveList = findByAge();
                break;
            case 3:
                getRemoveList = findByGender();
                break;
            case 4:
                getRemoveList = findByEmail();
                break;
            case 5:
                getRemoveList = findByPhone();
                break;
            case 6:
                MessageUI.success("Exit Successfully");
                break;
            default:
                MessageUI.failed("Invalid Choice");
        }
        if (choice >= 1 && choice <= 5) {
            if (!getRemoveList.isEmpty()) {
                printTutor(getRemoveList);
                if (Character.toUpperCase(tutorUI.inputYesNo("Are you sure you want to delete above tutors (Y/N): ")) == 'Y') {
                    tutorList.removeAll(getRemoveList);
                    for (int i = 0; i < getRemoveList.size(); i++) {
                        todayModify(getRemoveList.get(i), "Deletion");
                    }
                    MessageUI.success("The above tutor has been removed from list");
                } else {
                    MessageUI.cancelled("Deleting above tutor has been cancelled: ");
                }
            } else if (!isFound) {
                MessageUI.failed("Nothing tutors found");
            }
        }
    }

    public void displayAllTutor() {
        int choice = 0;
        Iterator<Tutor> itertorList = null;
        do {
            choice = tutorUI.getDisplayType();
            switch (choice) {
                case 1:
                    itertorList = tutorList.getForwardIterator();
                    break;
                case 2:
                    itertorList = tutorList.getBackwardIterator();
                    break;
                case 3:
                    MessageUI.success("Exit Sucessfully...");
                    break;
                default:
                    MessageUI.failed("Invalid Choice");
            }
            if (choice == 1 || choice == 2) {
                tutorUI.printTableHeader();
                while (itertorList.hasNext()) {
                    Tutor tutor = itertorList.next();
                    tutorUI.printTutorDetails(tutor);
                }
                MessageUI.otherMsg("Total Number of Tutor(s): " + tutorList.size(), 1);
            }
        } while (choice != 3);
    }

    public void filterTutor() {
        char cont = 'Y';
        int choice = 0;
        ListInterface<Tutor> tmpList = new DoublyLinkedList<>();
        ListInterface<Integer> usedFilters = new DoublyLinkedList<>();
        tmpList = tutorList.clone();
        do {
            choice = tutorUI.getFilterMenu(usedFilters);
            if (usedFilters.contains(choice)) {
                MessageUI.failed("This filter option has already been used. Choose another");
                continue;
            }
            switch (choice) {
                case 1:
                    String id = tutorUI.inputTutorID();
                    tutorList = tutorList.filter(t -> t.getTutorID().equalsIgnoreCase(id));
                    break;
                case 2:
                    tutorList = findByName();
                    break;
                case 3:
                    tutorList = findByAge();
                    break;
                case 4:
                    tutorList = findByGender();
                    break;
                case 5:
                    tutorList = findByEmail();
                    break;
                case 6:
                    tutorList = findByPhone();
                    break;
                case 7:
                    break;
                case 8:
                    tutorList = tmpList.clone();
                    MessageUI.success("Exit Secessfully");
                    return;
                default:
                    MessageUI.failed("Invalid Choice");
            }
            if (choice >= 1 && choice <= 6) {
                usedFilters.add(choice);
                if (!tutorList.isEmpty()) {
                    printTutor(tutorList);
                } else {
                    MessageUI.failed("Nothing found");
                    char opt = Character.toUpperCase(tutorUI.inputYesNo("Would you like to revert to original list (Y/N): "));
                    if (opt == 'Y') {
                        choice = 7;
                    } else {
                        break;
                    }
                }
            }
            if (choice == 7) {
                usedFilters.clear();
                tutorList = tmpList.clone();
                continue;
            }
            cont = Character.toUpperCase(tutorUI.inputYesNo("Would you like to continue adding more filter criteria? (Y/N): "));
        } while (cont == 'Y');
        tutorList = tmpList.clone();
    }

    public static void main(String[] args) {
        TutorMaintainence tutorMaintainence = new TutorMaintainence();
        tutorMaintainence.runTutorMaintenance();
    }

}
