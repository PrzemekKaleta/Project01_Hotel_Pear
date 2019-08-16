package pl.coderslab.dto;

import pl.coderslab.entity.StayState;

public class ShortStayDTO {

    private String name;

    private String email;

    private StayState stayState;

    private Long stay_ID;

    public ShortStayDTO(String name, String email, StayState stayState, Long stay_ID) {
        this.name = name;
        this.email = email;
        this.stayState = stayState;
        this.stay_ID = stay_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StayState getStayState() {
        return stayState;
    }

    public void setStayState(StayState stayState) {
        this.stayState = stayState;
    }

    public Long getStay_ID() {
        return stay_ID;
    }

    public void setStay_ID(Long stay_ID) {
        this.stay_ID = stay_ID;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ShortStayDTO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", stayState=").append(stayState);
        sb.append(", stay_ID=").append(stay_ID);
        sb.append('}');
        return sb.toString();
    }
}
