package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.dto.ReserveAsk;
import pl.coderslab.entity.Room;
import pl.coderslab.entity.Stay;
import pl.coderslab.entity.StayState;
import pl.coderslab.pojo.RoomFitObject;
import pl.coderslab.repository.RoomRepository;
import pl.coderslab.repository.StayRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DBReservationService extends ReservationService {

    public final RoomRepository roomRepository;
    public final StayRepository stayRepository;

    public DBReservationService(RoomRepository roomRepository, StayRepository stayRepository) {
        this.roomRepository = roomRepository;
        this.stayRepository = stayRepository;
    }

    private final int MAXCAPACITY = 4;

    @Override
    public ReserveAsk canReserve(ReserveAsk reserveAsk) {


        while(reserveAsk.getPersons()<MAXCAPACITY) {
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
    public double givePrice(ReserveAsk reserveAsk) {
        return 0;
    }

    @Override
    public void reserv() {

    }

    @Override
    public boolean findRoom(ReserveAsk reserveAsk) {

        int roomCapacity = reserveAsk.getPersons();
        long askFromNumber = reserveAsk.getDateFrom().toEpochDay();
        long askUntilNumber = reserveAsk.getDateUntil().toEpochDay();

        Map<Long, Double> fitMap = new HashMap<>();

        List<Room>rooms = roomRepository.findAllByCapacity(roomCapacity);

        for(int i = 0 ; i < rooms.size() ; i++){

            List<Stay>stays = stayRepository.findAllByStayStatesAndRoomId(StayState.CURRENT, StayState.RESERVED, rooms.get(i).getId());

            if(stays.isEmpty()){

                fitMap.put(rooms.get(i).getId(), 10.0);

            }else{

                for(int j = 0 ; j < stays.size() ; j ++){

                    long stayFromNumber = stays.get(j).getStayFrom().toEpochDay();
                    long stayUntilNumeber = stays.get(j).getStayUntil().toEpochDay();

                    




                }


            }

            stays.stream().forEach(System.out::println);

        }


        return false;
    }
}
