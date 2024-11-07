package dao;

import adt.*;
import adt.ListInterface;
import entity.Tutor;
import java.io.*;

/**
 *
 * @author tankj
 */
public class TutorDAO {

    private String fileName = "tutors.dat"; // For security and maintainability, should not have filename hardcoded here.
    private String fileName1 = "numbersTotal.dat";

    public void saveToFile(ListInterface<Tutor> tutorList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(tutorList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public void saveToFile1(int totalNumber) {
        File file = new File(fileName1);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(totalNumber);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public ListInterface<Tutor> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<Tutor> tutorList = new DoublyLinkedList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            tutorList = (DoublyLinkedList<Tutor>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return tutorList;
        }
    }

    public int retrieveFromFile1() {
        int total =0;
        File file = new File(fileName1);
        ListInterface<Tutor> tutorList = new DoublyLinkedList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            total = (int) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return total;
        }
    }
}
