package util;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import templates.ClientTemplate;
import templates.LoginTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Arrays;
import java.util.Optional;

import static util.Constants.logToFile;

public class ClientOperations {

    public static IDiscordClient pseudoCopyDcClient(IDiscordClient originalClient) {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(originalClient.getToken());
        return clientBuilder.build();
    }
    public static Optional<LoginTemplate> loginTemplateFromFile(String filepath) {
        try {
            JAXBContext context = JAXBContext.newInstance(ClientTemplate.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return Optional.of((LoginTemplate) unmarshaller.unmarshal(new File(filepath)));
        } catch (JAXBException jaxbExc) {
            Constants.logToAll(String.format("Could not unmarshal login config-file from path %s (%s):\n%s",
                    filepath, jaxbExc.getMessage(), Arrays.toString(jaxbExc.getStackTrace())));
            return Optional.empty();
        }
    }
    public static Optional<IDiscordClient> clientFromLoginTemplate(LoginTemplate loginTemplate) {
        try {
            Constants.logToAll(String.format("Attempted IDiscordClient login for token \'%s\'.",
                    loginTemplate.getToken()));
            ClientBuilder clientBuilder = new ClientBuilder();
            clientBuilder.withToken(loginTemplate.getToken())
                    .withPingTimeout(loginTemplate.getPingTimeOut())
                    .setDaemon(loginTemplate.isDaemon())
                    .withShards(loginTemplate.getShardCount())
                    .withRecommendedShardCount(loginTemplate.hasRecommendedShardCount())
                    .setMaxReconnectAttempts(loginTemplate.getMaxReconnectAttempts())
                    .setMaxMessageCacheCount(loginTemplate.getMaxMessageCacheCount())
                    .set5xxRetryCount(loginTemplate.getNumberOf5xxRetries())
                    .setPresence(loginTemplate.getInitialStatus(), loginTemplate.getInitialActivity(), loginTemplate.getInitialText());
            return Optional.of(clientBuilder.build());
        } catch(Exception | Error e) {
            Constants.logToAll(String.format("Unable to read IDiscordClient from LoginTemplate \'%s\' (%s):\n%s",
                    loginTemplate.toString(), e.getMessage(), Arrays.toString(e.getStackTrace())));
            return Optional.empty();
        }
    }
    public static Optional<ClientTemplate> clientTemplateFromFile(String filepath) {
        try {
            JAXBContext context = JAXBContext.newInstance(ClientTemplate.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return Optional.of((ClientTemplate) unmarshaller.unmarshal(new File(filepath)));
        } catch (JAXBException jaxbExc) {
            Constants.logToAll(String.format("Could not unmarshal client config-file from path %s (%s):\n%s",
                    filepath, jaxbExc.getMessage(), Arrays.toString(jaxbExc.getStackTrace())));
            return Optional.empty();
        }
    }

    public static Optional<IDiscordClient> withNewListener(IDiscordClient sourceClient, Object newListener) {
        try {
            IDiscordClient tempClient =  pseudoCopyDcClient(sourceClient);
            EventDispatcher dispatcher = tempClient.getDispatcher();
            dispatcher.registerListener(newListener);
            return Optional.of(tempClient);
        } catch(Exception | Error e) {
            logToFile(String.format("Unable to attach new Listener %s to Client %s: %s",
                    newListener.toString(), sourceClient.getApplicationClientID(), Arrays.toString(e.getStackTrace())));
        }
        return Optional.empty();
    }
}
