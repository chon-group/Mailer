package group.chon.agent.mailer.jasonStdLib;

import group.chon.agent.mailer.core.MailerTS;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

public class receiveEMail extends DefaultInternalAction {
    MailerTS mailerTS = null;

    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        mailerTS = new MailerTS(ts.getAg(), ts.getC(), ts.getSettings(), ts.getAgArch());
        if (args.length == 2) {
            mailerTS.getMailerArch().getEmailBridge().setLogin(args[0].toString());
            mailerTS.getMailerArch().getEmailBridge().setPassword(args[1].toString());
        }
        mailerTS.getMailerArch().getEMailMessage();
        return true;

    }
}
