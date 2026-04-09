package random;


import com.github.javafaker.Faker;
import user.User;


public class UserFactory { private static final Faker FAKER = new Faker();

    public static User createRandom() {
        return User.builder()
                .name(FAKER.name().name())
                .email(FAKER.internet().safeEmailAddress())
                .password(FAKER.internet().password(6, 20))
                .negativePassword(FAKER.internet().password(1,3))
                .build();
    }
}

