package exam.page2tasks.students;

import java.util.Comparator;

public class StudentYearInCollegeComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.getYearInCollege(), s2.getYearInCollege());
    }
}