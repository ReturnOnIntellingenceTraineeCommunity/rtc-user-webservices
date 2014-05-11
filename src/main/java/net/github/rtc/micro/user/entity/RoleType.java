package net.github.rtc.micro.user.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vladislav Pikus
 */
@XmlRootElement
public enum RoleType {
    ROLE_USER, ROLE_ADMIN, ROLE_EXPERT;
}
