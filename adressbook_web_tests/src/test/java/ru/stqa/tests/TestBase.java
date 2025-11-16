package ru.stqa.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.stqa.manager.ApplicationManager;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() throws Exception {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.initialBrowser(System.getProperty("browser", "chrome"));

    }


    @AfterEach
    public void stopBrowser() {
        app.stopBrowser();
    }
//    @AfterEach
//    void checkDatabaseConsistency(){
//        app.jdbc().consistency();
//    }
}
