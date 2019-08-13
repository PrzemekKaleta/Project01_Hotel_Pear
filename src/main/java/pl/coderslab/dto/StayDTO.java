package pl.coderslab.dto;

import java.time.LocalDate;

public class StayDTO {

    LocalDate stayFrom;

    LocalDate stayUntil;

    int persons;

    int capacity;

    double price;

    public StayDTO(LocalDate stayFrom, LocalDate stayUntil, int persons, int capacity, double price) {
        this.stayFrom = stayFrom;
        this.stayUntil = stayUntil;
        this.persons = persons;
        this.capacity = capacity;
        this.price = price;
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

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
