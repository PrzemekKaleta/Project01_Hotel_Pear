package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.dto.ShortStayDTO;
import pl.coderslab.entity.Stay;
import pl.coderslab.entity.StayState;
import pl.coderslab.repository.RoomRepository;
import pl.coderslab.repository.StayRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationsScheduleService {

    private final StayRepository stayRepository;
    private final RoomRepository roomRepository;


    public ReservationsScheduleService(StayRepository stayRepository, RoomRepository roomRepository) {
        this.stayRepository = stayRepository;
        this.roomRepository = roomRepository;
    }

    public Map<Long,Map<LocalDate, ShortStayDTO>> fullMapRoomsShortStay(LocalDate startDate, LocalDate stopDate){

        List<Long> idRooms = roomRepository.getAllId();

        if(idRooms.isEmpty()){
            return null;
        }

        Map<Long,Map<LocalDate, ShortStayDTO>> fullMap = new HashMap<>();

        for(int i = 0 ; i < idRooms.size() ; i++){

            fullMap.put(idRooms.get(i) , mapRoomShortStay(startDate, stopDate, idRooms.get(i)));

            System.out.println(idRooms.get(i));
        }

       // fullMap.keySet()

        return fullMap;

    }


    public Map<LocalDate, ShortStayDTO> mapRoomShortStay(LocalDate startDate, LocalDate stopDate, Long roomID){

        Map<LocalDate, ShortStayDTO> shortStayDTOMap = new HashMap<>();


        List<Stay> stayList = stayRepository.findAllByStayStatesAndRoomId(StayState.CURRENT, StayState.RESERVED, roomID);

        int fullPeriod = stopDate.compareTo(startDate);

        for(int i = 0; i < fullPeriod; i++){
            shortStayDTOMap.put(startDate.plusDays(i), null);
        }
        if(stayList.isEmpty()){
            return shortStayDTOMap;
        }

        for(int i = 0; i < stayList.size(); i++){

            ShortStayDTO shortStayDTO = new ShortStayDTO(
                    stayList.get(i).getGuest().getSurname(),
                    stayList.get(i).getGuest().getEmail(),
                    stayList.get(i).getStayState(),
                    stayList.get(i).getId());

            long start = startDate.toEpochDay();
            long stop = stopDate.toEpochDay();

            LocalDate fromDate = stayList.get(i).getStayFrom();
            LocalDate untilDate = stayList.get(i).getStayUntil();

            long from = fromDate.toEpochDay();
            long until = untilDate.toEpochDay();

            LocalDate firstDate = null;
            LocalDate lastDate = null;


            if(until <= start || stop < from ){

            }else{
                if(from <= start && until > stop){
                    firstDate = startDate;
                    lastDate = stopDate;
                    System.out.println("1 if");
                }else if(from < start && until <= stop){
                    firstDate = startDate;
                    lastDate = untilDate;
                    System.out.println("2 if");
                }else if(from < stop && until > stop){
                    firstDate = fromDate;
                    lastDate = startDate;
                    System.out.println("3 if");
                }else if(from >= start && until <= stop){
                    firstDate = fromDate;
                    lastDate = untilDate;
                    System.out.println("4 if");
                }

                int adaptedPeriod = lastDate.compareTo(firstDate);

                for(int j = 0 ; j < adaptedPeriod ; j++ ){
                    shortStayDTOMap.put(firstDate.plusDays(j),shortStayDTO);
                }

            }

        }

        shortStayDTOMap.entrySet().stream().forEach(System.out :: println);

        return shortStayDTOMap;

    }


}
