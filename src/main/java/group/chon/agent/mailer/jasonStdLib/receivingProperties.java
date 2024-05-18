package group.chon.agent.mailer.jasonStdLib;

import group.chon.agent.mailer.Mailer;
import group.chon.agent.mailer.core.Info;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;

public class receivingProperties extends DefaultInternalAction {

    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        final Mailer mailerArch = Mailer.getMailerArch(ts.getAgArch());
        if(mailerArch != null){
            if (args.length == 5) {
                mailerArch.getEmailBridge().setLogger(ts.getLogger());
                mailerArch.getEmailBridge().setRAuth(
                        Boolean.parseBoolean(args[0].toString().replaceAll("\"","")),
                        Boolean.parseBoolean(args[1].toString().replaceAll("\"","")),
                        Boolean.parseBoolean(args[2].toString().replaceAll("\"","")),
                        args[3].toString().replaceAll("\"",""),
                        args[4].toString().replaceAll("\"",""));
                return true;
            } else {
                ts.getLogger().warning(Info.wrongParametersAdvancedActionsERROR(this.getClass().getName()));
                return false;
            }
        }else {
            ts.getLogger().warning(Info.nonMailerAgentERROR(this.getClass().getName()));
            return false;
        }
    }
}
