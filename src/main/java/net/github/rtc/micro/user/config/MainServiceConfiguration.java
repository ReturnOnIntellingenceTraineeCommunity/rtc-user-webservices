package net.github.rtc.micro.user.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;


public class MainServiceConfiguration extends Configuration {

    public DataSourceFactory getDatabase() {
        return database;
    }

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @JsonProperty
    private HashMap<String,String> quartzSettings = new HashMap<String, String>();
}
