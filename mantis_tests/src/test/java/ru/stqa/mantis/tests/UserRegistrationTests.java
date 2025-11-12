package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.UserData;
import ru.stqa.mantis.utils.Utils;
import java.time.Duration;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser() throws Exception {
        var email = String.format("%s@localhost",Utils.randomString(5));
        var login = Utils.randomString(5);
        //создать пользователя с (адрес) на почтовом сервере JamesHelper
        app.jamesCli().addUser(email, "password");
        //заполняем форму создания и отправляем  Browser
        app.session().register(login,email);
        //Ждем почту, извлекаем ссылку, проходим по ссылке и завершаем регистрацию пользователя   Browser
        app.goTo(app.mail().extractUrl(email,"password",Duration.ofSeconds(60)));
        //обновляем данные пользователя
        app.session().updateAccountAfterRegister(Utils.randomString(10),"root");
        //проверяем что пользователь может залогиниться HttpSessionHelper
        app.http().login(login,"root");
        Assertions.assertTrue(app.http().isLoggedIn());

    }

    @Test
    void canRegisterUserApi() throws Exception {
        var login = Utils.randomString(10);
        var email = login+"@localhost";

        //создать пользователя с (адрес) на почтовом сервере JamesHelper
        app.jamesApi().addUser(email, "password");
        //Создаем пользователя через Api Mantis
        app.rest().createUser(new UserData()
                .withEmail(email)
                .withUsername(login)
                .withRealName(Utils.randomString(10)));
       //Ждем почту, извлекаем ссылку, проходим по ссылке и завершаем регистрацию пользователя   Browser
       app.goTo(app.mail().extractUrl(email,"password",Duration.ofSeconds(60)));
        //обновляем данные пользователя
       app.session().updateAccountAfterRegister(Utils.randomString(10),"password");
        //проверяем что пользователь может залогиниться HttpSessionHelper
        app.http().login(login,"password");
        Assertions.assertTrue(app.http().isLoggedIn());

    }

    @Test
    void canDeleteAllUsersWithApi() throws Exception {
        app.rest().deleteAllUsers();
    }
}
