package ru.stqa.mantis.manager;

import ru.stqa.mantis.config.ConfigReader;
import java.io.File;
import java.util.List;

public class JamesCliHelper extends HelperBase {
    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password) throws Exception {
        String jamesDir = ConfigReader.getJamesWorkingDir();

        ProcessBuilder builder = new ProcessBuilder(
                "java",
                "-cp",
                jamesDir + "james-server-jpa-app.lib/*",
                "org.apache.james.cli.ServerCmd",
                "AddUser",
                email,
                password
        );
        builder.directory(new File(jamesDir)).start().waitFor();
    }


    public List<String> getUserList() throws Exception {
        String jamesDir = ConfigReader.getJamesWorkingDir();

        ProcessBuilder builder = new ProcessBuilder(
                "java",
                "-cp",
                jamesDir + "james-server-jpa-app.lib/*",
                "org.apache.james.cli.ServerCmd",
                "ListUsers");
        builder.directory(new File(jamesDir));
        Process process = builder.start();

        try (var reader = process.inputReader()) {
            List<String> emails = reader.lines()
                    .filter(line -> line.contains("@"))
                    .map(String::trim)
                    .toList();

            process.waitFor();
            return emails;
        }
    }

    public void deleteUsers() throws Exception{
        String jamesDir = ConfigReader.getJamesWorkingDir();
        var emails = getUserList();
        emails.forEach(email -> {
            try {
                new ProcessBuilder(
                        "java",
                        "-cp",
                        jamesDir + "james-server-jpa-app.lib/*",
                        "org.apache.james.cli.ServerCmd",
                        "RemoveUser",
                        email)
                        .directory(new File(jamesDir))
                        .inheritIO()
                        .start()
                        .waitFor();
                } catch (Exception e) {
                e.printStackTrace();}});
    }
}
