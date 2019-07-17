package exam.page2tasks.students;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StudentDemo {
    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Max", "Alber", "V", 1985, 3, Group.GROUP1, getSampleMarks()));
        studentList.add(new Student("Jack", "Hesler", "A", 1982, 5, Group.GROUP3, getSampleMarks()));
        studentList.add(new Student("Jain", "Kim", "Z", 1984, 3, Group.GROUP1, getSampleMarks()));
        studentList.add(new Student("Kate", "Ener", "G", 1955, 5, Group.GROUP1, getSampleMarks()));
        studentList.add(new Student("Elbers", "Jacob", "W", 1986, 4, Group.GROUP2, getSampleMarks()));
        studentList.add(new Student("Jim", "Ci", "F", 1989, 3, Group.GROUP1, getSampleMarks()));
        studentList.add(new Student("Donald", "Lee", "F", 1985, 1, Group.GROUP2, getSampleMarks()));
        studentList.add(new Student("Ron", "Alber", "A", 1980, 3, Group.GROUP3, getSampleMarks()));

        studentList.sort(new StudentNamesComparator());
        studentList.sort(new StudentYearInCollegeComparator());

        studentList.forEach(s -> System.out.println(s.getLastName() + " " + s.getYearInCollege()));

        Student youngestStudent = studentList.get(0);
        Student oldestStudent = studentList.get(0);
        for (Student s : studentList) {
            if (s.getBirthYear() < youngestStudent.getBirthYear())
                youngestStudent = s;
            if (s.getBirthYear() > oldestStudent.getBirthYear())
                oldestStudent = s;
        }
        System.out.println("Youngest: " + youngestStudent.getLastName() + " " + youngestStudent.getBirthYear());
        System.out.println("Oldest: " + oldestStudent.getLastName() + " " + oldestStudent.getBirthYear());


        for (Group group : Group.values()) {

        }
    }


    public static HashMap<Subject, Integer> getSampleMarks() {

        HashMap<Subject, Integer> marks = new HashMap<>();
        marks.put(Subject.values()[0], ThreadLocalRandom.current().nextInt(1, 5));
        marks.put(Subject.values()[1], ThreadLocalRandom.current().nextInt(1, 5));
        marks.put(Subject.values()[2], ThreadLocalRandom.current().nextInt(1, 5));
        marks.put(Subject.values()[3], ThreadLocalRandom.current().nextInt(1, 5));
        marks.put(Subject.values()[4], ThreadLocalRandom.current().nextInt(1, 5));
        return marks;

    }
}
