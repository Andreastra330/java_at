package ru.stqa.mantis.manager;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import ru.stqa.mantis.config.ConfigReader;
import ru.stqa.mantis.model.IssueData;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;


public class SoapApiHelper extends HelperBase{
    MantisConnectPortType mantis;

    public SoapApiHelper(ApplicationManager manager)  {
        super(manager);
        try {
            mantis = new MantisConnectLocator().getMantisConnectPort(new URL(ConfigReader.getSoapEndpoint()));
        } catch (ServiceException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createIssue(IssueData issueData) {
        try {
            var categories = mantis.mc_project_get_categories(
                    ConfigReader.getUsername(),
                    ConfigReader.getPassword(),
                    BigInteger.valueOf(issueData.project()));

            var issue = new biz.futureware.mantis.rpc.soap.client.IssueData();
            issue.setSummary(issueData.summary());
            issue.setDescription(issueData.description());
            var projectId = new ObjectRef();
            projectId.setId(BigInteger.valueOf(issueData.project()));
            issue.setProject(projectId);
            issue.setCategory(categories[0]);
            mantis.mc_issue_add(
                    ConfigReader.getUsername(),
                    ConfigReader.getPassword(),
                    issue);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
