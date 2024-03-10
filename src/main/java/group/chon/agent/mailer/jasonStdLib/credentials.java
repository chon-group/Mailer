package group.chon.agent.mailer.jasonStdLib;

import group.chon.agent.mailer.Mailer;
//import group.chon.agent.mailer.core.MailerTS;
import group.chon.agent.mailer.core.Info;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

public class credentials extends DefaultInternalAction {
    //MailerTS mailerTS = null;

    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        final Mailer mailerArch = Mailer.getMailerArch(ts.getAgArch());
        if(mailerArch != null){
            if(args.length == 2){
                mailerArch.getEmailBridge().setLogger(ts.getLogger());
                mailerArch.getEmailBridge().setLogin(args[0].toString().replaceAll("\"",""));
                mailerArch.getEmailBridge().setPassword(args[1].toString().replaceAll("\"",""));
                //mailerArch.getEmailBridge().setMailerName(mailerArch.getAgName());
                return true;
            }else {
                ts.getLogger().warning(Info.wrongParametersERROR(this.getClass().getName()));
                return false;
            }
        }else{
            ts.getLogger().warning(Info.nonMailerAgentERROR(this.getClass().getName()));
            return false;
        }
//        if (args.length == 2) {
//            mailerTS = new MailerTS(ts.getAg(), ts.getC(), ts.getSettings(), ts.getAgArch());
//
//            mailerTS.getMailerArch().getEmailBridge().setLogin(args[0].toString().replaceAll("\"",""));
//            mailerTS.getMailerArch().getEmailBridge().setPassword(args[1].toString().replaceAll("\"",""));
//            mailerTS.getMailerArch().getEmailBridge().setMailerName(mailerTS.getAgArch().getAgName());
//
//            return true;
//        } else {
//            return false;
//        }
    }
}
