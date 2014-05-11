package net.github.rtc.micro.user.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Vladislav Pikus
 */
@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@XmlRootElement
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    private RoleType name;

    @Column
    @Enumerated(EnumType.STRING)
    public RoleType getName() {
        return name;
    }
    public void setName(RoleType name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Role{");
        sb.append("name='").append(name);
        sb.append('}');
        return sb.toString();
    }

    public Role() {}

    public Role(RoleType name) {
        this.name = name;
    }
}
