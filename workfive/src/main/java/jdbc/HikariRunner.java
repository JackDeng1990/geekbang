package jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import school.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Component
public class HikariRunner  implements CommandLineRunner {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        String sql = "select * from student";
        List<Student> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Student.class));
        for (Student user : users) {
            System.out.println(user.getName());
        }
    }
}
