package info.cafeit.hibernateonetoone;

import info.cafeit.hibernateonetoone.model.UserProfiles;
import info.cafeit.hibernateonetoone.model.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class HibernateOneToOneApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HibernateOneToOneApplication.class, args);
    }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Users user = new Users("longtran", "tranhuulongcit@gmail.com", "Long", "Tran");
        UserProfiles profile = new UserProfiles("123 Ngu Hanh Son", "Da Nang", "0123456675", "no desc");
        profile.setUser(user);

        //lưu vào database
        entityManager.persist(user);
        //lưu profile vào db với đối tượng user tham chiếu đến profile
        entityManager.persist(profile);

        //select profile và lấy ra tham chiếu của user
        UserProfiles p = entityManager.find(UserProfiles.class, 1l);

        System.out.println(p);
        System.out.println(p.getUser());
    }
}
