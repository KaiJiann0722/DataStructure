
package dao;

import entity.Tutor;
import adt.*;

/**
 *
 * @author Tan Kai Jiann
 */
public class TutorInitializer {

    private TutorDAO dao = new TutorDAO();
    
    public ListInterface<Tutor> initializeTutors() {
        ListInterface<Tutor> tList = new DoublyLinkedList<>();
        tList.add(new Tutor("Tan Yi Kian", 60, 'M', "james@tarc.edu.my", "0123456789"));
        tList.add(new Tutor("Tan Ah Li", 53, 'F', "tanal@tarc.edu.my", "0190810510"));
        tList.add(new Tutor("Lee Wak Yong", 42, 'M', "leewk@tarc.edu.my", "0128912763"));
        tList.add(new Tutor("Lee Chong Wei", 58, 'M', "leecw@tarc.edu.my", "0179124761"));
        tList.add(new Tutor("Chong Jia Rong", 35, 'F', "chongjr@tarc.edu.my", "0186572314"));
        tList.add(new Tutor("Foong Lok Yan", 32, 'F', "foongly@tarc.edu.my", "0116573297"));
        tList.add(new Tutor("Foong Zhi Hen", 67, 'F', "foongzh@tarc.edu.my", "0161278906"));
        tList.add(new Tutor("Loh Voon Zai", 38, 'M', "lohvz@tarc.edu.my", "0131097862"));
        tList.add(new Tutor("Yap Rou Wei", 45, 'F', "yaprw@tarc.edu.my", "0156709123"));
        tList.add(new Tutor("Tan Ah Kai", 47, 'M', "tanak@tarc.edu.my", "0126501983"));
        dao.saveToFile(tList);
        dao.saveToFile1(Tutor.getNumTutor());
        return tList;
    }

    public static void main(String[] args) {
        TutorInitializer p = new TutorInitializer();
        ListInterface<Tutor> tutorList = p.initializeTutors();
        System.out.println("\nTutors:\n" + tutorList);
    }

}
