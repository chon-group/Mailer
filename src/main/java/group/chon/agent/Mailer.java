package group.chon.agent;

import group.chon.agent.mailer.core.EMailMiddleware;
import group.chon.agent.mailer.core.Util;
import jason.ReceiverNotFoundException;
import jason.asSemantics.Message;
import jason.architecture.AgArch;

import java.util.ArrayList;

public class Mailer extends AgArch {

    private EMailMiddleware emailBridge = null;
    public Mailer(){
        super();
        this.emailBridge = new EMailMiddleware();
    }

    @Override
    public void sendMsg(Message m){
        Util util = new Util();
        if(util.isValidEmail(m.getReceiver())){
            this.emailBridge.sendMsg(m.getReceiver(),m.getIlForce(),m.getPropCont().toString());
        }else{
            try {
                super.sendMsg(m);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void checkMail() {
        super.checkMail();
        this.getEMailMessage();
    }



    public static Mailer getMailerArch(jason.architecture.AgArch currentArch) {
        if (currentArch == null) {
            return null;
        }
        if (currentArch instanceof Mailer) {
            return (Mailer) currentArch;
        }
        return getMailerArch(currentArch.getNextAgArch());
    }

    public void getEMailMessage() {
        if (this.emailBridge.getLogin()!=null && this.emailBridge.getPassword()!=null
                && this.emailBridge.isRPropsEnable() && this.emailBridge.isRHostEnable()){
            ArrayList<Message> list = this.emailBridge.checkEMail();
            for (Message item : list) {
                this.getTS().getC().addMsg(item);
            }
        }
    }

    public EMailMiddleware getEmailBridge() {
        return this.emailBridge;
    }
}

