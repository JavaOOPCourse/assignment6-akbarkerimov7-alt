import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> students = new HashMap<>();

        // ====================== TASK 1 ======================
        // TODO: Добавь минимум 5 студентов (ключ = ID)
        students.put("1", new Student("Ali", 3.8, 19));
        students.put("2", new Student("Akbar", 4.0, 18));
        students.put("3", new Student("Isken", 4.0, 17));
        students.put("4", new Student("Baystan", 3.9, 18));
        students.put("5", new Student("Hamza", 3.4, 20));
        // Сделай минимум два студента с одинаковым GPA (для Task 3)

        // TODO: Напечатай всех студентов (ID + объект)
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        // TODO: Найди студента по ID и выведи его
        System.out.println("ID:" + students.get("5"));

        // TODO: Удали одного студента по ID
        students.remove("5");

        // TODO: Обнови GPA у одного студента
        students.get("4").setGpa(4.0);

        // ====================== SORTING (IMPORTANT) ======================
        // TODO: Создай ArrayList из всех студентов (students.values())
        ArrayList<Student> list = new ArrayList<>(students.values());


        // TODO 6a: Отсортируй по GPA (natural ordering) и выведи
        Collections.sort(list);
        list.forEach(System.out::println);

        // TODO 6b: Отсортируй по имени (Comparator) и выведи
        list.sort(Comparator.comparing(Student::getName));
        list.forEach(System.out::println);

        // ====================== TASK 2 ======================`
        System.out.println("\n=== Task 2: Top 3 by GPA ===");
        // TODO: Создай новый список, отсортируй по GPA по убыванию, выведи первые 3
        List<Student> list2 = new ArrayList<>(students.values());
        list2.sort((a, b) -> Double.compare(b.getGpa(), a.getGpa()));

        for (int i = 0; i < 3 && i < list2.size(); i++) {
            System.out.println(list2.get(i));
        }

        // ====================== TASK 3 ======================
        System.out.println("\n=== Task 3: Students with same GPA ===");
        // TODO: Сгруппируй студентов по GPA и выведи только те, где больше 1 студента
        HashMap<Double, List<String>> gpaMap = new HashMap<>();

        for (Student s : students.values()) {
            gpaMap.putIfAbsent(s.getGpa(), new ArrayList<>());
            gpaMap.get(s.getGpa()).add(s.getName());
        }

        for (Double gpa : gpaMap.keySet()) {
            if (gpaMap.get(gpa).size() > 1) {
                System.out.println("GPA " + gpa + " -> " + gpaMap.get(gpa));
            }
        }


        // ====================== TASK 4 ======================
        System.out.println("\n=== Task 4: Courses ===");
        HashMap<Course, List<Student>> courseMap = new HashMap<>();
        // TODO: Создай 2–3 курса, добавь студентов, выведи всё
        courseMap.put(new Course("Linear Algebra"), new ArrayList<>(students.values()));
        courseMap.put(new Course("Machine Learning"), list2);

        for (Map.Entry<Course, List<Student>> entry : courseMap.entrySet()) {
            System.out.println(entry.getKey() + "\n Students: " + entry.getValue());
        }
        // ====================== TASK 5 ======================
        System.out.println("\n=== Task 5: GPA desc + Name ===");
        // TODO: Создай Comparator (GPA убывание → если равно, то имя возрастание) и отсортируй
        class GpaNameComparator implements Comparator<Student> {
            @Override
            public int compare(Student s1, Student s2) {
                int res = Double.compare(s2.getGpa(), s1.getGpa());
                if (res == 0) {
                    return s1.getName().compareTo(s2.getName());
                }
                return res;
            }
        }
        list.sort(new GpaNameComparator());
        for (Student s : list) System.out.println(s);
    }
}



