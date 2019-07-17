package exam.page2tasks.students;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String firstName;
    private String lastName;
    private String familyName;
    private int birthYear;
    private int yearInCollege;
    private Group group;
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

    @Override
    public String toString() {
        return "Student{" + firstName + '\'' + lastName + '\'' + familyName + '\'' + birthYear + yearInCollege + group + marks +
                '}';
    }

    public double getAverageMark() {
        long marksSum = 0;
        for (Map.Entry<Subject, Integer> entry : marks.entrySet()) {
            marksSum += entry.getValue();
        }
        return marksSum / marks.size();
    }
    public Student() {
    }

    public Student(String firstName, String lastName, String familyName, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.familyName = familyName;
        this.birthYear = birthYear;
    }

    public HashMap<Subject, Integer> getMarks() {
        return marks;
    }

    public void setMarks(HashMap<Subject, Integer> marks) {
        this.marks = marks;
    }

    public Student(String firstName, String lastName, String familyName, int birthYear, int yearInCollege, Group group, HashMap<Subject, Integer> marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.familyName = familyName;
        this.birthYear = birthYear;
        this.yearInCollege = yearInCollege;
        this.group = group;
        this.marks = marks;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
