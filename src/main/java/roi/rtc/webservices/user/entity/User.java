package roi.rtc.webservices.user.entity;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import roi.rtc.webservices.user.test.TestUser;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Chernichenko Bogdan on 18.03.14.
 */
@Entity
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;

    private String fio;

    private String phone;

    private String email;

    private Date birthDate;

    private String city;

    private String university;

    private String faculty;

    private String speciality;

    @ElementCollection
    private List<String> technologies;

    private Integer writtenEng;

    private Integer oralEng;
    private String note;
    private String password;

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

    public String getMajor() {
        return speciality;
    }

    public void setMajor(String speciality) {
        this.speciality = speciality;
    }

    //maybe this getter maybe should be changed
    //I have no idea how to pass several technologies from a reg.form to a controller and user

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
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

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
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

    public User(String fio, String phone, String email, Date birthDate, String city, String university, String faculty, String major, List<String> technologies, Integer writtenEng, Integer oralEng, String note, String password) {
        this.fio = fio;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
        this.city = city;
        this.university = university;
        this.faculty = faculty;
        this.speciality = major;
        this.technologies = technologies;
        this.writtenEng = writtenEng;
        this.oralEng = oralEng;
        this.note = note;
        this.password = password;


    }
    public User(String fio, String phone, String email, String city, String university, String faculty, String password)
    {
        this.fio = fio;
        this.phone = phone;
        this.email = email;
        this.birthDate = new Date();
        this.city = city;
        this.university = university;
        this.faculty = faculty;
        this.speciality = "bb";
        this.technologies = null;
        this.writtenEng = 1;
        this.oralEng = 2;
        this.note = "df";
        this.password = password;
    }
    public User(TestUser testUser)
    {
        this.fio = testUser.getFio();
        this.phone = testUser.getPhone();
        this.email = testUser.getEmail();
        this.birthDate = testUser.getBirthDate();
        this.city = testUser.getCity();
        this.university = testUser.getUniversity();
        this.faculty = testUser.getFaculty();
        this.speciality = testUser.getSpeciality();
        this.technologies = null;
        this.writtenEng = 1;
        this.oralEng = 2;
        this.note = "df";
        this.password = testUser.getPassword();
    }
}
