package random;

import com.github.javafaker.Faker;
import user.User;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        User user = User.builder()
                .name(faker.name().name())
                .password(faker.internet().password(6, 20))
                .email(faker.internet().safeEmailAddress())
                .build();

        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
    }
}
