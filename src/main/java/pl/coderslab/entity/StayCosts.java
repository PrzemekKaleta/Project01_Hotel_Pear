package pl.coderslab.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class StayCosts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    int roomCapacity;

    int residentsNumber;

    double price;

    LocalDate lastChange;

    @PreUpdate
    public void preUpdate() {
        lastChange = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public int getResidentsNumber() {
        return residentsNumber;
    }

    public void setResidentsNumber(int residentsNumber) {
        this.residentsNumber = residentsNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getLastChange() {
        return lastChange;
    }

    public void setLastChange(LocalDate lastChange) {
        this.lastChange = lastChange;
    }
}
