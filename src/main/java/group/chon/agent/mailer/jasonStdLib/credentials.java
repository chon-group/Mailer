package group.chon.agent.mailer.jasonStdLib;

import group.chon.agent.mailer.core.MailerTS;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

public class credentials extends DefaultInternalAction {
    MailerTS mailerTS = null;

    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        if (args.length == 2) {
            mailerTS = new MailerTS(ts.getAg(), ts.getC(), ts.getSettings(), ts.getAgArch());

            mailerTS.getMailerArch().getEmailBridge().setLogin(args[0].toString().replaceAll("\"",""));
            mailerTS.getMailerArch().getEmailBridge().setPassword(args[1].toString().replaceAll("\"",""));
            mailerTS.getMailerArch().getEmailBridge().setMailerName(mailerTS.getAgArch().getAgName());

            return true;
        } else {
            return false;
        }
    }
}
