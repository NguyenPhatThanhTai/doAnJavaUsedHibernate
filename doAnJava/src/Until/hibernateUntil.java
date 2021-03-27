package Until;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernateUntil {
    public static Session getSession(){
        SessionFactory sf;
        sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session s;
        s = sf.openSession();
        return s;
    }
}
