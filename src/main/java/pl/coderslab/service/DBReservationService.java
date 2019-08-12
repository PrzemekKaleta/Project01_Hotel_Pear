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

        //zapisujemy stałe dane: pojemność pokojów które będą przeglądane, liczby reprezentujące zapytanie od kiedy do kiedy ma być szukane miejsce
        int roomCapacity = reserveAsk.getPersons();
        long askFromNumber = reserveAsk.getDateFrom().toEpochDay();
        long askUntilNumber = reserveAsk.getDateUntil().toEpochDay();

        //definiujemy listę do której będziemy zapisywać id pokojów które będa spełniać warunki oraz stopień dopasowania (czym niższa wartość tym lepiej)
        Map<Long, Double> fitMap = new HashMap<>();

        //defiujemy listę pokojów które spełniły warunek pojemności
        List<Room>rooms = roomRepository.findAllByCapacity(roomCapacity);

        //i-terójemy po liście szukając tych odpowiednich
        for(int i = 0 ; i < rooms.size() ; i++){

            //w pierwszej kolejności tworzymy listę pobytów które albo są aktualne albo są zarezerwowane (tj są aktywne, aby nie ściągać historycznych danych)
            List<Stay>stays = stayRepository.findAllByStayStatesAndRoomId(StayState.CURRENT, StayState.RESERVED, rooms.get(i).getId());

            //tworzymy boolean który będzie przechowywał czy dany pokój może być zarezerwowany, na początku dajemy true

            boolean canReserveRoom = true;

            //jeżeli lista jest pusta to oznacza że nie ma jeszcze aktywnych pobytów i odrazu możemy zapisać pokój do MAPY fitMap, nadając liczbę fit 10
            if(stays.isEmpty()){

                fitMap.put(rooms.get(i).getId(), 10.0);

            }else{

                //jeżeli lista nie była pusta to tworzymy dwie wartości będące składową późniejszej liczby FIT
                long holeBefore = 10;
                long holeAfter = 10;

                //j-terójemy po liście pobytów dla danego pokoju
                for(int j = 0; j < stays.size(); j++){

                    //zapisujemy wartości w postaci liczbowej dla dnia rozpoczęcia i zakończenia pobytu, do którego będziemy dopasowywać zapytanie
                    long stayFromNumber = stays.get(j).getStayFrom().toEpochDay();
                    long stayUntilNumber = stays.get(j).getStayUntil().toEpochDay();

                    //tworzymy dwie liczby będące różnicą pomiędzy zakończeniem pobytu i rozpoczęciem zapytania oraz zakończeniem zapytania i rozpoczeciem pobytu
                    long differenceAskFromToStayUntil = askFromNumber - stayUntilNumber;
                    long differenceStayFromToAskUntil = stayFromNumber - askUntilNumber;

                    System.out.println("różnica: " + differenceAskFromToStayUntil + " pomiędzy AskFrom " + askFromNumber + ", a StayUntil " + stayUntilNumber);
                    System.out.println("różnica: " + differenceStayFromToAskUntil + " pomiędzy StayFrom " + stayFromNumber + ", a AskUntil " + askUntilNumber);

                    //sprawdzenie warunków obie liczby "różnice" powinny mieć inny znak
                    if ((differenceAskFromToStayUntil >= 0 && differenceStayFromToAskUntil <= 0) || (differenceAskFromToStayUntil <= 0 && differenceStayFromToAskUntil >= 0)) {

                        //sprawdzamy czy różnica przed jest dodatnia lub zerowa, jeżeli tak to czy jest też mniejsza od dotychczasowej wartości bo...
                        //...jeżeli będziemy i-terować to kolejne pobyty mogą być bliżej najszego zapytania a wiec stopień dopasowania będzie lepszy...
                        //...jeżeli tego nie dodamy tego warunku to możemy nadpisać lepsze dopasowanie gorszym
                        if (differenceAskFromToStayUntil >= 0 && differenceAskFromToStayUntil < holeBefore) {
                            holeBefore = differenceAskFromToStayUntil;
                        }
                        // podobnie jak poprzednio sprawdzamy dopasowanie po (tutaj pierwszy warunek powinien być już spełniony z automatu - jeżeli wykożysta się 'else if'
                        if (differenceStayFromToAskUntil >= 0 && differenceStayFromToAskUntil < holeAfter) {
                            holeAfter = differenceStayFromToAskUntil;
                        }

                    }else{
                        //jeżeli warunek nie jest spełniony oznacza to że conajmniej jeden pobyt koliduje z zapytaniem
                        canReserveRoom = false;

                    }
                }

                if(canReserveRoom){

                    double fitNumber = Math.sqrt(holeBefore) * Math.sqrt(holeAfter);

                    fitMap.put(rooms.get(i).getId(), fitNumber);
                }
            }

        }

        fitMap.entrySet().stream().forEach(System.out::println);

        if(fitMap.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}
