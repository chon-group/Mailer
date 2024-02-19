package group.chon.agent.mailer.jasonStdLib;

import group.chon.agent.mailer.core.MailerTS;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

public class sendingProperties extends DefaultInternalAction {
    MailerTS mailerTS = null;

    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        if (args.length == 5) {
            mailerTS = new MailerTS(ts.getAg(), ts.getC(), ts.getSettings(), ts.getAgArch());
            mailerTS.getMailerArch().getEmailBridge().setSendAuth(
                    Boolean.parseBoolean(args[0].toString().replaceAll("\"","")),
                    Boolean.parseBoolean(args[1].toString().replaceAll("\"","")),
                    Boolean.parseBoolean(args[2].toString().replaceAll("\"","")),
                    args[3].toString().replaceAll("\"",""),
                    args[4].toString().replaceAll("\"",""));
            return true;
        } else {
            return false;
        }
    }
}
