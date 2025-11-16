package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.utils.Utils;

public class IssueCreationTests extends TestBase{


    @Test
    void canCreateIssue(){
        app.rest().createIssue(new IssueData()
                .withSummary(Utils.randomString(10))
                .withDescription(Utils.randomString(10))
                .withProject(1L));
    }

    @Test
    void canCreateIssueSOAP(){
        app.soap().createIssue(new IssueData()
                .withSummary(Utils.randomString(10))
                .withDescription(Utils.randomString(10))
                .withProject(1L));
    }
}
