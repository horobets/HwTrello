package exam.page2tasks.students;

import java.util.HashMap;
import java.util.Map;

public enum Subject {
    ART("Art"),
    MUSIC("Music"),
    MATHEMATICS("Mathematics"),
    LEADERSHIP("Leadership"),
    SPEECH("Speech");

    private static final Map<String, Subject> lookup = new HashMap<>();

    static {
        for (Subject subject : Subject.values()) {
            lookup.put(subject.toString(), subject);
        }
    }

    private String description;

    Subject(String description) {
        this.description = description;
    }

    public static Subject get(String description) {
        return lookup.get(description);
    }

    @Override
    public String toString() {
        return description;
    }
}
