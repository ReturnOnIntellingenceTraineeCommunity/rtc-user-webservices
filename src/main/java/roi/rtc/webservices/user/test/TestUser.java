package roi.rtc.webservices.user.test;

import java.util.Date;

/**
 * Created by Pritula Dmitry on 23.03.14.
 */

public  class TestUser {

    public TestUser() {

    }

    private String fio = "Ivanov Ivan Ivanovich";

    private String phone = "+7570805555555";

    private String email = "ivanov@mail.com";

    private Date birthDate = new Date("0000-00-00");

    private String city = "Dnepr";

    private String university = "DNY";

    private String faculty = "FAM";

    private String speciality = "AM";


    private String password = "12345";

    public String getFio(){
        return this.fio;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getEmail(){
        return this.email;
    }
    public Date getBirthDate(){
        return this.birthDate;
    }
    public String getCity(){
        return this.city;
    }
    public String getUniversity(){
        return this.university;
    }
    public String getFaculty(){
        return this.faculty;
    }
    public String getSpeciality(){
        return this.speciality;
    }
    public String getPassword(){
        return this.password;
    }

}
