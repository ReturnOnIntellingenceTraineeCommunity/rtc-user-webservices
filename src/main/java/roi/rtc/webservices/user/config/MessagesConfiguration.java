package roi.rtc.webservices.user.config;

import javax.validation.constraints.NotNull;

/**
 * Created by Eugene on 19.03.14.
 */
public class MessagesConfiguration {
    @NotNull
    private String hello;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

}
