package pl.coderslab.service;

import pl.coderslab.dto.ReserveAsk;
import pl.coderslab.entity.Stay;

import java.util.Map;

public abstract class ReservationService {

    public abstract ReserveAsk canReserve(ReserveAsk reserveAsk);

    public abstract double givePrice(ReserveAsk reserveAsk);

    public abstract Stay reserv(ReserveAsk reserveAsk);

    public abstract Map<Long, Double> findRoom(ReserveAsk reserveAsk);
}
