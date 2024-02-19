package group.chon.agent.mailer.core;

import group.chon.agent.Mailer;
import jason.asSemantics.Agent;
import jason.asSemantics.Circumstance;
import jason.runtime.Settings;

public class MailerTS extends jason.asSemantics.TransitionSystem{

    public MailerTS(Agent a, Circumstance c, Settings s, jason.architecture.AgArch ar) {
        super(a, c, s, ar);
    }

    public Mailer getMailerArch() {
        return (Mailer) super.getAgArch();
    }
}
