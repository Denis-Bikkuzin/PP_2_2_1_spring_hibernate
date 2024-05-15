package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Denis", "Bikkuzin", "denisbikkuzin@mail.ru");
        User user2 = new User("Oleg", "Barbashin", "barbashin1998@gmail.com");
        User user3 = new User("Vadim", "Yapiev", "vyapiev@yandex.ru");
        User user4 = new User("Slava", "Starikov", "starikovsva@inbox.ru");

        user1.setCar(new Car("BMW", 7));
        user2.setCar(new Car("LADA", 2109));
        user3.setCar(new Car("FORD", 99));
        user4.setCar(new Car("VOLVO", 9));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
                System.out.println("Car Model = " + user.getCar().getModel());
                System.out.println("Car Series  = " + user.getCar().getSeries());
            }
            System.out.println();
        }
        List<User> user = userService.getUserByModelAndSeriesCar("BMW", 7);
        if (user != null) {
            for (User userTest : user) {
                System.out.println("Id = " + userTest.getId());
                System.out.println("First Name = " + userTest.getFirstName());
                System.out.println("Last Name = " + userTest.getLastName());
                System.out.println("Email = " + userTest.getEmail());
            }
        } else {
            System.out.println("No user found with the specified model and series.");
        }


    }
}
