package entity;

import dao.TutorDAO;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author tankj
 */
public class Tutor implements Serializable {
    
    private String tutorID;
    private String tutorName;
    private int age;
    private char gender;
    private String email;
    private String contactNo;
    private static int numTutor = 1000;

    public Tutor() {
    }

    
    public Tutor(String tutorName, int age, char gender, String email, String contactNo) {
        this.tutorID = "T" + ++numTutor;
        this.tutorName = tutorName;
        this.age = age;
        this.email = email;
        this.contactNo = contactNo;
        this.gender = gender;
    }

    public Tutor(String tutorID, String tutorName, int age, char gender, String email, String contactNo) {
        this.tutorID = tutorID;
        this.tutorName = tutorName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.contactNo = contactNo;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getTutorID() {
        return tutorID;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public static int getNumTutor() {
        return numTutor;
    }

    public static void setNumTutor(int numTutor) {
        Tutor.numTutor = numTutor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.tutorID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Tutor other = (Tutor) obj;
        if (!Objects.equals(this.tutorID, other.tutorID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tutor ID              : " + tutorID
                + "\nTutor Name            : " + tutorName
                + "\nTutor Age             : " + age
                + "\nTutor Gender          : " + gender
                + "\nTutor Email           : " + email
                + "\nContact Number        : " + contactNo;
    }

}
