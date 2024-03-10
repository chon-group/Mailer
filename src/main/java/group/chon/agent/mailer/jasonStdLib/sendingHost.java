package group.chon.agent.mailer.jasonStdLib;

import group.chon.agent.mailer.Mailer;
import group.chon.agent.mailer.core.Info;
//import group.chon.agent.mailer.core.MailerTS;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

public class sendingHost extends DefaultInternalAction {
    //MailerTS mailerTS = null;

    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        final Mailer mailerArch = Mailer.getMailerArch(ts.getAgArch());
        if(mailerArch != null){
            if (args.length == 3) {
                mailerArch.getEmailBridge().setSendProps(
                        args[0].toString().replaceAll("\"",""),
                        args[1].toString().replaceAll("\"",""),
                        args[2].toString().replaceAll("\"",""));
                return true;
            } else {
                ts.getLogger().warning(Info.wrongParametersAdvancedActionsERROR(this.getClass().getName()));
                return false;
            }
        }else {
            ts.getLogger().warning(Info.nonMailerAgentERROR(this.getClass().getName()));
            return false;
        }

//        if (args.length == 3) {
//            mailerTS = new MailerTS(ts.getAg(), ts.getC(), ts.getSettings(), ts.getAgArch());
//            mailerTS.getMailerArch().getEmailBridge().setSendProps(
//                    args[0].toString().replaceAll("\"",""),
//                    args[1].toString().replaceAll("\"",""),
//                    args[2].toString().replaceAll("\"",""));
//            return true;
//        } else {
//            return false;
//        }
    }
}
