package roi.rtc.webservices.user.entity;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created by Chernichenko Bogdan on 18.03.14.
 * 
 * @author Vladislav Pikus
 * @author Chernichenko Bogdan
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@XmlRootElement
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;

    private String surname;

    private String name;

    private String middleName;

    private String phone;

    private String email;

    private Date birthDate;

    private String city;

    private String university;

    private String faculty;

    private String speciality;

      private String gender;
    
    private String progLanguages;
    
    private String english;

    
    private Integer writtenEng;

    private Integer oralEng;

    private String note;

    private String password;

    public void setGender(String gender)
    {
        this.gender=gender;
    }
    public String getGender()
    {
        return this.gender;
    }
    public void setProgLanguages(String progLanguages)
    {
        this.progLanguages=progLanguages;
    }
    public String getProgLanguages()
    {
        return this.progLanguages;
    }
    public void setEnglish(String english)
    {
        this.english=english;
    }
    public String getEnglish()
    {
        return this.english;
    }
    
    /* Spring Security fields*/
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name="users_roles_refs",
            joinColumns={@JoinColumn(name="ROLE_ID")},
            inverseJoinColumns={@JoinColumn(name="id")})
    private List<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public List<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /*************************************/

    /*@JsonIgnore
    private Date regDate;
    @JsonIgnore
    private Date editDate;*/

   /* public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }*/
    public String getMiddleName() {return middleName;}

    public void setMiddleName(String middleName) {this.middleName = middleName; }
    public String getName() {return name; }

    public void setName(String name) {this.name = name;}
    public String getSurname() {return surname; }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getWrittenEng() {
        return writtenEng;
    }

    public void setWrittenEng(Integer writtenEng) {
        this.writtenEng = writtenEng;
    }

    public Integer getOralEng() {
        return oralEng;
    }

    public void setOralEng(Integer oralEng) {
        this.oralEng = oralEng;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }



    public User(){

    }

    public User(String name,String surname,String middleName, String email, String password) {
        this.name = name;
        this.surname=surname;
        this.middleName=middleName;
        this.email = email;
        this.password = password;
    }

    public User(String name,String surname,String middleName, String phone, String email, Date birthDate, String city, String university, String faculty, String speciality, Integer writtenEng, Integer oralEng, String note, String password) {
        this.name = name;
        this.surname=surname;
        this.middleName=middleName;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
        this.city = city;
        this.university = university;
        this.faculty = faculty;
        this.speciality = speciality;
        this.writtenEng = writtenEng;
        this.oralEng = oralEng;
        this.note = note;
        this.password = password;


    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", city='").append(city).append('\'');
        sb.append(", university='").append(university).append('\'');
        sb.append(", faculty='").append(faculty).append('\'');
        sb.append(", speciality='").append(speciality).append('\'');
         sb.append(", gender='").append(gender).append('\'');
        sb.append(", writtenEng=").append(writtenEng);
        sb.append(", oralEng=").append(oralEng);
        sb.append(", note='").append(note).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", authorities=").append(authorities);
        sb.append(", accountNonExpired=").append(accountNonExpired);
        sb.append(", accountNonLocked=").append(accountNonLocked);
        sb.append(", credentialsNonExpired=").append(credentialsNonExpired);
        sb.append(", enabled=").append(enabled).append('\'');
        sb.append(", english=").append(english);
        sb.append('}');
        return sb.toString();
    }
}
