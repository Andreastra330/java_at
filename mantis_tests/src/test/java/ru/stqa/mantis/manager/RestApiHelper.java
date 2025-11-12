package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import io.swagger.client.model.User;
import io.swagger.client.model.UserAddResponse;
import org.openqa.selenium.By;
import ru.stqa.mantis.config.ConfigReader;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;
import java.util.ArrayList;


public class RestApiHelper extends HelperBase{

    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(ConfigReader.getMantisApiKey());
    }

    public void createIssue(IssueData issueData) {
        IssuesApi apiInstance = new IssuesApi();
        Issue issue = new Issue(); // Issue | The issue to add.
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            new RuntimeException(e);
        }
    }

    public void createUser(UserData userData){
        UserApi apiInstance = new UserApi();
        User user = new User(); // User | The user to add.
        user.setEmail(userData.email());
        user.setPassword(userData.password());
        user.setUsername(userData.username());
        user.setRealName(userData.realName());

        try {
            UserAddResponse result = apiInstance.userAdd(user);
            System.out.println(result);
        } catch (ApiException e) {
            new RuntimeException(e);
        }
    }

    public void deleteAllUsers() throws Exception {
        manager.session().login(ConfigReader.getUsername(),ConfigReader.getPassword());
        Thread.sleep(2000);
        manager.goTo(ConfigReader.getBaseUrl()+"manage_user_page.php");
        var links = manager.findElements(By.cssSelector("a[href*='user_id=']"),5);
        var ids = new ArrayList<>();
        links.forEach(link -> {
            var s = link.getAttribute("href");
            assert s != null;
            String[] part =  s.split("=");
            ids.add(part[1]);
        });

        UserApi apiInstance = new UserApi();
        ids.forEach(id ->{
            if(!id.equals("1"))
            {
                try {
                    Long userId = Long.parseLong((String) id);
                    apiInstance.userDelete(userId);
                } catch (ApiException e) {
                    new RuntimeException(e);
                }
            }
        });

    }
}
