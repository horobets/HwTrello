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


        //sort by YearInCollege and names
        studentList.sort(new StudentNamesComparator());
        studentList.sort(new StudentYearInCollegeComparator());

        studentList.forEach(s -> System.out.println(s.getLastName() + " " + s.getYearInCollege()));


        // youngest and oldest student
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


        //best student in every group
        for (Group group : Group.values()) {
            List<Student> studentsFromGroup = getStudentsFromGroup(studentList, group);
            Student bestStudentInGroup = studentsFromGroup.get(0);
            for (Student student : studentsFromGroup) {
                if (student.getAverageMark() > bestStudentInGroup.getAverageMark()) {
                    bestStudentInGroup = student;
                }
            }
            System.out.printf("%nBest Student in group %s: %s ", group, bestStudentInGroup.toString());
        }
    }

    public static List<Student> getStudentsFromGroup(List<Student> studentList, Group group) {

        List<Student> studentListFromGroup = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getGroup().equals(group)) {
                studentListFromGroup.add(student);
            }
        }
        return studentListFromGroup;
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