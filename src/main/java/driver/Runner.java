package driver;

import listeners.MessageGuard;
import sx.blah.discord.api.IDiscordClient;
import templates.LoginTemplate;
import util.ClientOperations;
import templates.ClientTemplate;
import util.Constants;

import java.util.Optional;

public class Runner {

    public static void main(String[] args) {
        /*
         * Startup / Launch
         */
        if(args.length != 2) {
            // Blabla throw smth
        }
        Optional<LoginTemplate> loginTemplateOpt = ClientOperations.loginTemplateFromFile(args[0]);
        if(!loginTemplateOpt.isPresent()) {
            Constants.logToStdOut("Startup interrupted.");
            throw new IllegalStateException("LoginTemplate not present.");
        }
        Optional<IDiscordClient> clientOpt = ClientOperations.clientFromLoginTemplate(loginTemplateOpt.get());
        if(!clientOpt.isPresent()) {
            Constants.logToStdOut(("Startup interrupted."));
            throw new IllegalStateException("IDiscordClient not present.");
        }
        Optional<ClientTemplate> mainTemplateOpt = ClientOperations.clientTemplateFromFile(args[1]);
        if(!mainTemplateOpt.isPresent()) {
            Constants.logToStdOut(("Startup interrupted."));
            throw new IllegalStateException("ClientTemplate not present.");
        }
        IDiscordClient mainClient = clientOpt.get();
        /*
         * Main BI
         */
        Optional<IDiscordClient> listeningClient = ClientOperations.withNewListener(mainClient, new MessageGuard());
        Constants.logToStdOut("Successful launch!");

    }
}
