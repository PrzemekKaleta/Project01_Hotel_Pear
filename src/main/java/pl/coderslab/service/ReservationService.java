package pl.coderslab.service;

import pl.coderslab.dto.ReserveAsk;

public abstract class ReservationService {

    public abstract ReserveAsk canReserve(ReserveAsk reserveAsk);

    public abstract double givePrice(ReserveAsk reserveAsk);

    public abstract void reserv();

    public abstract boolean findRoom(ReserveAsk reserveAsk);
}
