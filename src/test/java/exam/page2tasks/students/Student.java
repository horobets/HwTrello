package exam.page2tasks.students;

import java.util.HashMap;

public class Student {
    private String firstName;
    private String lastName;
    private String familyName;
    private int birthYear;
    private int yearInCollege;
    private long groupId;
    private HashMap<Subject, Integer> marks;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getYearInCollege() {
        return yearInCollege;
    }

    public void setYearInCollege(int yearInCollege) {
        this.yearInCollege = yearInCollege;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public HashMap<Subject, Integer> getMarks() {
        return marks;
    }

    public void setMarks(HashMap<Subject, Integer> marks) {
        this.marks = marks;
    }
}
