import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface iPersonService<T> {
    List<T> getAll();
    void add(T person);
}

class Teacher {
    private String name;
    private int age;

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class TeacherService implements iPersonService<Teacher> {
    private List<Teacher> teachers = new ArrayList<>();

    @Override
    public List<Teacher> getAll() {
        return teachers;
    }

    @Override
    public void add(Teacher teacher) {
        teachers.add(teacher);
    }

    public void printTeachers() {
        for (Teacher teacher : teachers) {
            System.out.println("Name: " + teacher.getName() + ", Age: " + teacher.getAge());
        }
    }
}

class PersonComparator<T extends Teacher> implements Comparable<T> {

    @Override
    public int compareTo(T o) {
        return this.getAge() - o.getAge();
    }
}

class AccountController {
    public static <T extends Person> double averageAge(List<Teacher> teachers) {
        double sum = 0;
        for (T person : teachers) {
            sum += person.getAge();
        }
        return sum / teachers.size();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TeacherService teacherService = new TeacherService();
        teacherService.add(new Teacher("John", 30));
        teacherService.add(new Teacher("Alice", 35));
        
        teacherService.printTeachers();
        
        List<Teacher> teachers = teacherService.getAll();
        Collections.sort(teachers, new PersonComparator<>());
        
        System.out.println("Average age of teachers: " + averageAge(teachers));
    }
}
