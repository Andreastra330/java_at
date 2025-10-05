package ru.stqa.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.manager.ApplicationManager;

import java.util.Random;

public class TestBase {
    protected static ApplicationManager app;

    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char) ('a' + rnd.nextInt(26));
        }
        return result;
    }

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.initialBrowser(System.getProperty("browser", "chrome"));

    }

}
