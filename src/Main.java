import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory factory = SessionFactoryBuilder.getSessionFactory();

        User user = new User(factory);

        new createAccountForm();

        // Equipment equipment = new Equipment();
        // equipment.EquipmentForm();
    }

}
