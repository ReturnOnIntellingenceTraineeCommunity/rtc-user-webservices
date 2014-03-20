package roi.rtc.webservices.user.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public class MainServiceConfiguration extends Configuration {

    @Valid
    private MessagesConfiguration messages;

    public MessagesConfiguration getMessages() {
        return messages;
    }

    public void setMessages(MessagesConfiguration messages) {
        this.messages = messages;
    }

    @Valid
    @NotNull
    @JsonProperty
    private DatabaseConfiguration database = new DatabaseConfiguration();

    public DatabaseConfiguration getDatabase() {
        return database;
    }

}
