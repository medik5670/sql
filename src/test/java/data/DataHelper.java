package data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }
    public static AuthInfo getAuthInfoWithTestData() {
        return new AuthInfo("vasya","qwerty123", "c690a106-a570-4aa6-a19d-bde280489511");
    }
    private static String generateRandomLogin() {
        return faker.name().username();
    }
    private static String generatePassword() {
        return faker.internet().password();
    }
    private static String generateId(){
        return faker.internet().password();
    }
    public static AuthInfo generateRandomUser() {
        return new AuthInfo(generateRandomLogin(), generatePassword(),generateId());
    }

    public static VerificationCode generateRandomVerificationCode() {
        return new VerificationCode(faker.numerify("######"));
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
        String id;

    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerificationCode {
        String code;
    }


}
