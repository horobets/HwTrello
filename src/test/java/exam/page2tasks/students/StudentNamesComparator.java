package exam.page2tasks.students;

import java.util.Comparator;

public class StudentNamesComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getLastName().compareTo(s2.getLastName());
    }
}