package templates;

import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.handle.obj.StatusType;
import sx.blah.discord.util.cache.ICacheDelegateProvider;

public class LoginTemplate {
    private String token;
    private int pingTimeOut;
    private boolean isDaemon;
    private int shardCount;
    private boolean hasRecommendedShardCount;
    private int maxReconnectAttempts;
    private int maxMessageCacheCount;
    private int numberOf5xxRetries;
    private StatusType initialStatus;
    private ActivityType initialActivity;
    private String initialText;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public int getPingTimeOut() {
        return pingTimeOut;
    }
    public void setPingTimeOut(int pingTimeOut) {
        this.pingTimeOut = pingTimeOut;
    }

    public boolean isDaemon() {
        return isDaemon;
    }
    public void setDaemon(boolean daemon) {
        isDaemon = daemon;
    }

    public int getShardCount() {
        return shardCount;
    }
    public void setShardCount(int shardCount) {
        this.shardCount = shardCount;
    }

    public boolean hasRecommendedShardCount() {
        return hasRecommendedShardCount;
    }
    public void setHasRecommendedShardCount(boolean hasRecommendedShardCount) {
        this.hasRecommendedShardCount = hasRecommendedShardCount;
    }

    public int getMaxReconnectAttempts() {
        return maxReconnectAttempts;
    }
    public void setMaxReconnectAttempts(int maxReconnectAttempts) {
        this.maxReconnectAttempts = maxReconnectAttempts;
    }

    public int getMaxMessageCacheCount() {
        return maxMessageCacheCount;
    }
    public void setMaxMessageCacheCount(int maxMessageCacheCount) {
        this.maxMessageCacheCount = maxMessageCacheCount;
    }

    public int getNumberOf5xxRetries() {
        return numberOf5xxRetries;
    }
    public void setNumberOf5xxRetries(int numberOf5xxRetries) {
        this.numberOf5xxRetries = numberOf5xxRetries;
    }

    public StatusType getInitialStatus() {
        return initialStatus;
    }
    public void setInitialStatus(StatusType initialStatus) {
        this.initialStatus = initialStatus;
    }

    public ActivityType getInitialActivity() {
        return initialActivity;
    }
    public void setInitialActivity(ActivityType initialActivity) {
        this.initialActivity = initialActivity;
    }

    public String getInitialText() {
        return initialText;
    }
    public void setInitialText(String initialText) {
        this.initialText = initialText;
    }
}
