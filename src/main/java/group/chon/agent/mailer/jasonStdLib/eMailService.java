package group.chon.agent.mailer.jasonStdLib;

import group.chon.agent.mailer.core.MailerTS;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

public class eMailService extends DefaultInternalAction {
    MailerTS mailerTS = null;

    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        mailerTS = new MailerTS(ts.getAg(), ts.getC(), ts.getSettings(), ts.getAgArch());
        if (args.length == 2) {
            String[] input = parseString(args[0].toString());
            String[] output = parseString(args[1].toString());

            if(input[1].equals("imaps")){
                mailerTS.getMailerArch().getEmailBridge().setRAuth(
                        false,
                        true,
                        false,
                        "null",
                        "null");
                mailerTS.getMailerArch().getEmailBridge().setReceiverProps(
                        input[0].toString(),
                        input[1].toString(),
                        "993");
            }else{
                return false;
            }

            if(output[1].equals("smtpOverTLS")){
                mailerTS.getMailerArch().getEmailBridge().setSendAuth(
                        true,
                        true,
                        false,
                        "null",
                        "null");
                mailerTS.getMailerArch().getEmailBridge().setSendProps(
                        output[0].toString(),
                        "smtp",
                        "465"
                );
            }else{
                return false;
            }
            return true;
        } else {
            return false;
        }
    }


    private String[] parseString(String input) {

        input = input.substring(1, input.length() - 1);

        String[] parts = input.split(",");
        parts[0] = parts[0].replaceAll("\"", "").trim();
        parts[1] = parts[1].replaceAll("\"", "").trim();

        return parts;
    }
}
