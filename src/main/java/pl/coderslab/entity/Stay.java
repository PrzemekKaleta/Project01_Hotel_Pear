package pl.coderslab.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Stay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate stayFrom;

    private LocalDate stayUntil;

    private StayState stayState;

    @ManyToOne
    @NotNull
    private Room room;

    @ManyToOne
    @NotNull
    private Guest guest;

    private int residents;

    private double cost;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public StayState getStayState() {
        return stayState;
    }


    public int getResidents() {
        return residents;
    }

    public void setResidents(int residents) {
        this.residents = residents;
    }

    public void setStayState(StayState stayState) {
        this.stayState = stayState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStayFrom() {
        return stayFrom;
    }

    public void setStayFrom(LocalDate stayFrom) {
        this.stayFrom = stayFrom;
    }

    public LocalDate getStayUntil() {
        return stayUntil;
    }

    public void setStayUntil(LocalDate stayUntil) {
        this.stayUntil = stayUntil;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Stay{");
        sb.append("id=").append(id);
        sb.append(", stayState=").append(stayState);
        sb.append(", room=").append(room);
        sb.append('}');
        return sb.toString();
    }
}
