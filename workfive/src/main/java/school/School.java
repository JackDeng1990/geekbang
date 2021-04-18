package school;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 */
public class School implements Serializable {
    private static final long serialVersionUID = -5241363643360598901L;

    private List<MyClass> classes;

    public List<MyClass> getClasses() {
        return classes;
    }

    public void setClasses(List<MyClass> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "School{" +
                "classes=" + classes +
                '}';
    }
}
