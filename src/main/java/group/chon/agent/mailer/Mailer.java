package group.chon.agent.mailer;

import group.chon.agent.mailer.core.EMailMiddleware;
import group.chon.agent.mailer.core.Info;
import group.chon.agent.mailer.core.Util;
import jason.asSemantics.Message;
import jason.architecture.AgArch;

import java.util.ArrayList;

public class Mailer extends AgArch {
    private Util util = new Util();

    private EMailMiddleware emailBridge = null;
    public Mailer(){
        super();
        this.emailBridge = new EMailMiddleware();
    }

    @Override
    public void sendMsg(Message m){
        if(util.isValidEmail(m.getReceiver())){
            if(isOUTConfigured()){
                this.emailBridge.sendMsg(m.getReceiver(),m.getIlForce(),m.getPropCont().toString());
            }else{
                this.getTS().getLogger().warning(Info.eMailProviderConfigurationNOTFOUND("sendMsg"));
                //System.out.println(Info.eMailProviderConfigurationNOTFOUND("sendMsg"));
            }
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
        if (isINConfigured()){
            ArrayList<Message> list = this.emailBridge.checkEMail();
            if(list != null){
                for (Message item : list) {
                    this.getTS().getC().addMsg(item);
                }
            }
        }
    }

    public EMailMiddleware getEmailBridge() {
        return this.emailBridge;
    }

    public boolean isINConfigured(){
        return this.emailBridge.getLogin()!=null
                && this.emailBridge.getPassword()!=null
                && this.emailBridge.isRPropsEnable()
                && this.emailBridge.isRHostEnable();
    }

    public boolean isOUTConfigured(){
         return this.emailBridge.getLogin()!=null
                && this.emailBridge.getPassword()!=null
                && this.emailBridge.isSHostEnable()
                && this.emailBridge.isSPropsEnable();
    }
}

