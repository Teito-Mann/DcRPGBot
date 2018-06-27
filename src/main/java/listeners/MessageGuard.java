package listeners;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import util.Constants;

public class MessageGuard {

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {
        Constants.logToStdOut("I just received event " + event.toString() + ".");
    }
}
