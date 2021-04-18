package school;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 */
public class MyClass implements Serializable {
    private static final long serialVersionUID = -4013040324007947129L;

    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "students=" + students +
                '}';
    }
}
