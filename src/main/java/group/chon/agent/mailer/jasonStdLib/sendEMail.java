package group.chon.agent.mailer.jasonStdLib;

import group.chon.agent.mailer.core.MailerTS;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

public class sendEMail extends DefaultInternalAction {
    MailerTS mailerTS = null;

    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        if (args.length == 3) {
            mailerTS = new MailerTS(ts.getAg(), ts.getC(), ts.getSettings(), ts.getAgArch());
            mailerTS.getMailerArch().getEmailBridge().sendMsg(
                    args[0].toString().replaceAll("\"",""),
                    args[1].toString(),
                    args[2].toString());
            return true;
        } else return false;

    }


}
