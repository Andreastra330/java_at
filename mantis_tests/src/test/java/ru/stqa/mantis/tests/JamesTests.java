package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.utils.Utils;

public class JamesTests extends TestBase{

    @Test
    void canCreateUser() throws Exception {
        app.jamesCli().addUser(
                String.format("%s@localhost", Utils.randomString(5)),
                "password");
    }

    @Test
    void deleteAllUsers() throws Exception{
        app.jamesCli().deleteUsers();
    }
}
