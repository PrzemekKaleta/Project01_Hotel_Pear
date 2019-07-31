package pl.coderslab.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.time.LocalDate;


public class ReserveAsk {

    private LocalDate dateFrom;

    private LocalDate dateUntil;

    @Range(min = 1, max = 4, message ="należy wybrać wielkość pokoju od 1 do 4 osobowego")
    private int persons;

    @AssertTrue (message = "")
    public boolean isValidRange() {
        if(dateFrom.compareTo(LocalDate.now()) < 1) {
            return false;
        }else if(dateUntil.compareTo(dateFrom) <= 0){
            return false;
        }
        return true;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateUntil() {
        return dateUntil;
    }

    public void setDateUntil(LocalDate dateUntil) {
        this.dateUntil = dateUntil;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }
}
