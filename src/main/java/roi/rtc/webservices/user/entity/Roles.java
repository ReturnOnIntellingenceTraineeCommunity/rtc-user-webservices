package roi.rtc.webservices.user.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vladislav Pikus
 */
@XmlRootElement
public enum Roles {
    ROLE_USER, ROLE_ADMIN, ROLE_EXPERT;
}
