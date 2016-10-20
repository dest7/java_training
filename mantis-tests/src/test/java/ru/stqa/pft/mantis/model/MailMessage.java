package ru.stqa.pft.mantis.model;

/**
 * Created by Edward on 20.10.2016.
 */
public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
