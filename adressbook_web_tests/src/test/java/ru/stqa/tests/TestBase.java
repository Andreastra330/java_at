package ru.stqa.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.stqa.manager.ApplicationManager;

public class TestBase {

    protected ApplicationManager app;  // ← static убрали

    @BeforeEach
    public void setUp() throws Exception {
        app = new ApplicationManager();   // ← создаём новый AM каждое выполнение
        app.initialBrowser(System.getProperty("browser", "chrome"));
    }

    @AfterEach
    public void stopBrowser() {
        app.stopBrowser();
    }
}
