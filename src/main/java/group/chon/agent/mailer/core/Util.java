package group.chon.agent.mailer.core;

import jason.asSyntax.ASSyntax;
import jason.asSyntax.Plan;
import jason.asSyntax.Term;
import org.jsoup.*;
import org.jsoup.nodes.*;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private String kqmlMessage;
    private String kqmlILF;

    private String sender;

    private Term term;

    private void setKqmlMessage(String kqmlMessage) throws Exception {
        if(getKqmlILF().equals("tellHow")){
            Plan p = null;
            if(kqmlMessage.startsWith("\"") && kqmlMessage.endsWith("\"")){
                p = ASSyntax.parsePlan(kqmlMessage.substring(1,kqmlMessage.length()-1));
            }else{
                p = ASSyntax.parsePlan(kqmlMessage);
            }
            this.kqmlMessage = "\""+p.toString()+"\"";
        }else{
            Term t = ASSyntax.parseTerm(kqmlMessage);
            this.kqmlMessage = t.toString();
        }
    }

    public String getKqmlMessage() {
        return kqmlMessage;
    }

    private void setKqmlILF(String kqmlILF) {
        this.kqmlILF = kqmlILF;
    }

    public String getKqmlILF() {
        return kqmlILF;
    }

    private void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    private String stripHTML(String html) {
        //String text = html.replaceAll("<[^>]*>", "");
        //text = text.replaceAll("\\s+", " ");
        //return text;
        Document doc = Jsoup.parse(html);
        return doc.text();
    }

    public Boolean isValidTerm(Object content){
        String message = null;
        try {
            message = convertToPlainText(content);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return false;
        }
        if(message == null){
            return false;
        }else{
            try{
                setKqmlMessage(message);
                return true;
            }catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
        }
    }

    public String convertToPlainText(Object content) throws MessagingException, IOException {
        if (content instanceof MimeMultipart) {
            MimeMultipart multipart = (MimeMultipart) content;
            return stripHTML(convertToPlainText(multipart.getBodyPart(0).getContent()));
        } else if (content instanceof String) {
            return stripHTML((String) content);
        } else {
            return null;
        }
    }

    public Boolean isIllocutionaryForce(String strSubjectMessage){
        String strILF = strSubjectMessage.replaceAll("[^a-zA-Z]", "").toLowerCase();

        if(strILF.equals("tell") || strILF.equals("untell") || strILF.equals("achieve")|| strILF.equals("unachieve")){
            setKqmlILF(strILF);
        }else if(strILF.equals("tellhow")){
            setKqmlILF("tellHow");
        }else if(strILF.equals("untellhow")){
            setKqmlILF("untellHow");
        }else if(strILF.equals("askone")){
            setKqmlILF("askOne");
        }else if(strILF.equals("askall")){
            setKqmlILF("askAll");
        }else if(strILF.equals("askHow")){
            setKqmlILF("askHow");
        }else{
            return false;
        }
        return true;
    }

    private String extractEmailAddress(Address[] addressesFrom) throws MessagingException {
        if (addressesFrom != null && addressesFrom.length > 0) {
            String rawFrom = addressesFrom[0].toString();
            return extractEmailAddressFromRaw(rawFrom);
        }
        return null;
    }

    private String extractEmailAddressFromRaw(String rawFrom) {
        int start = rawFrom.indexOf('<');
        int end = rawFrom.indexOf('>');
        if (start != -1 && end != -1) {
            return rawFrom.substring(start + 1, end);
        } else {
            return rawFrom;
        }
    }

    public Boolean isValidEmail(Address[] addressesFrom) {
        try{
            String strSource = extractEmailAddress(addressesFrom);
            if(isValidEmail(strSource)){
                setSender(strSource);
                return true;
            }else{
                return false;
            }
        } catch (MessagingException e) {
            //throw new RuntimeException(e);
            return false;
        }
    }

    public Boolean isValidEmail(String strSource){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}(?:\\.[a-zA-Z]{2,})?$");
        Matcher matcher = pattern.matcher(strSource);
        return  matcher.matches();
    }

    public int getRandom(){
        return ThreadLocalRandom.current().nextInt(0, 15001);
    }
}
