package ru.stqa.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.manager.ApplicationManager;

public class TestBase {
    protected static ApplicationManager app;


    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.initial(System.getProperty("browser", "chrome"));

    }


}
