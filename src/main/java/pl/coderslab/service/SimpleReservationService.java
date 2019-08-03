package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.dto.ReserveAsk;

@Service
public class SimpleReservationService extends ReservationService {

    int i=0;

    @Override
    public ReserveAsk canReserve(ReserveAsk reserveAsk) {

        while(reserveAsk.getPersons()<5) {
            if (findRoom(reserveAsk)) {
                reserveAsk.setPossible(true);
                return reserveAsk;
            } else {
                reserveAsk.setPersons(reserveAsk.getPersons() + 1);
            }
        }
        reserveAsk.setPossible(false);
        return reserveAsk;
    }

    @Override
    public boolean findRoom(ReserveAsk reserveAsk) {

        i++;
        System.out.println("liczba to " + i);
        if(i%2==0){
            System.out.println(i%2);
            return false;
        }
        System.out.println(i%2);
        return true;
    }

    @Override
    public void reserv() {
        System.out.println("zarezewowałem");

    }

    @Override
    public double givePrice(ReserveAsk reserveAsk) {
        int days = reserveAsk.getDateUntil().compareTo(reserveAsk.getDateFrom());
        return days*100.00*reserveAsk.getPersons();
    }
}