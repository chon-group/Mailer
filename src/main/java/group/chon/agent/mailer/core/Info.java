package group.chon.agent.mailer.core;

public class Info {
    private static final String WIKI_URL = "https://github.com/chon-group/Mailer/wiki";
    private static final String WIKI_InternalActions = "https://github.com/chon-group/Mailer/wiki/Internal-Actions";
    private static final String WIKI_AdvancedActions = "https://github.com/chon-group/Mailer/wiki/Advanced-Internal-Actions";

    public static String nonMailerAgentERROR(String action){
        return "[WARNING] It was not possible to call the internal action "+action+" because this agent is not an Mailer agent.\n"+
                "[INFO] See at " + WIKI_URL;
    }

    public static String wrongParametersERROR(String action){
        return "[WARNING] It was not possible to execute the internal action "+action+" because the number of parameters is wrong.\n"+
                "[INFO] See at " + WIKI_InternalActions;
    }

    public static String wrongParametersAdvancedActionsERROR(String action){
        return "[WARNING] It was not possible to execute the internal action "+action+" because the number of parameters is wrong.\n"+
                "[INFO] See at " + WIKI_AdvancedActions;
    }

    public static String eMailServiceActionProtocolNOTFOUND(String protocol){
        return "[WARNING] It was not possible to execute the internal action .eMailService because "+protocol+" protocol is not supported.\n"+
                "[INFO] Try use .sendingHost and .sendingProperties internal actions.\n"+
                "\t \t See more at "+WIKI_AdvancedActions;
    }

    public static String eMailProviderConfigurationNOTFOUND(String action){
        return "[WARNING] It was not possible to execute the internal action "+action+" because there is no Email Service Provider configurations.\n"+
                "[INFO] Try use .eMailService internal action.\n"+
                "\t \t See more at "+WIKI_InternalActions;
    }

    public static String emailINVALID(String action){
        return "[WARNING] It was not possible to execute the internal action "+action+" because the destination e-mail address appears wrong.\n"+
                "[INFO] See more at "+WIKI_InternalActions;
    }
}
