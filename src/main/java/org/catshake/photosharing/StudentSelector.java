package org.catshake.photosharing;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StudentSelector {
    private static final List<String> students = List.of(
            "Андрей П.",
            "Алексей",
            "Андрей З.",
            "Вика",
            "Екатерина",
            "Катя",
            "Людмила",
            "Михаил",
            "Степан",
            "Эмин"
    );

    public static void main(String[] args) {
        System.out.println(students.get(ThreadLocalRandom.current().nextInt(students.size())));
    }
}
