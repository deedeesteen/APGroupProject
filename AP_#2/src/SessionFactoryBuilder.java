import org.hibernate.cfg.Configuration;

import org.hibernate.SessionFactory;

public class SessionFactoryBuilder {

    private static SessionFactory factory;

    // Public method to get the SessionFactory
    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Customer.class)
                    .buildSessionFactory();
        }
        return factory;
    }

    // Public method to close the SessionFactory
    public static void closeSessionFactory() {
        if (factory != null && !factory.isClosed()) {
            factory.close();
            factory = null; // Set it to null to allow re-creation if needed
        }
    }

}
