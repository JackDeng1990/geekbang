package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {

    @Autowired
    private MyArticle article;


    public User(MyArticle myArticle) {

    }
}
