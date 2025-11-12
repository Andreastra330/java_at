package ru.stqa.mantis.manager;

import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import ru.stqa.mantis.model.MailMessage;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class MailHelper extends HelperBase{

    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String username, String password, Duration duration) throws Exception {
        var start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration.toMillis())
        {
            var inbox = getInbox(username, password);
            inbox.open(Folder.READ_ONLY);
            var messages = inbox.getMessages();

            var result = Arrays.stream(messages)
                    .map(m -> {
                        try {
                            return new MailMessage()
                                    .withFrom(m.getFrom()[0].toString())
                                    .withContent((String) m.getContent());
                        } catch (MessagingException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();

            inbox.close();
            inbox.getStore().close();
            if(!result.isEmpty()){
                return result;
            }
            Thread.sleep(1000);
        }
        throw new RuntimeException("No mail");
    }

    private static Folder getInbox(String username, String password) throws Exception {
        var session = Session.getInstance(new Properties());
        var store = session.getStore("pop3");
        store.connect("localhost", username, password);
        var inbox = store.getFolder("INBOX");
        return inbox;
    }


    public void drain(String username, String password) throws Exception {
    var inbox = getInbox(username,password);
    inbox.open(Folder.READ_WRITE);
    Arrays.stream(inbox.getMessages()).forEach(m -> {
        try {m.setFlag(Flags.Flag.DELETED,true);} catch (MessagingException e) {throw new RuntimeException(e);}});

    inbox.close();
    inbox.getStore().close();
    }

    public String extractUrl(String username, String password, Duration duration) throws  Exception{
        var messages = receive(username,password,duration);
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);

        if(matcher.find()){
            var url = text.substring(matcher.start(), matcher.end());
            return url;
        } else {
            throw new Exception("Ссылка не найдена");
        }
    }
}
