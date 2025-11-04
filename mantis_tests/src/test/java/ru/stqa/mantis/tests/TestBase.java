package ru.stqa.mantis.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.mantis.manager.ApplicationManager;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.initialBrowser(System.getProperty("browser", "chrome"));

    }

}
