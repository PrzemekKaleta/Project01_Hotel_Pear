package pl.coderslab.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.entity.Guest;
import pl.coderslab.entity.Stay;
import pl.coderslab.entity.StayState;
import pl.coderslab.repository.PersonRepository;
import pl.coderslab.repository.RoomRepository;
import pl.coderslab.repository.StayRepository;

import java.time.LocalDate;

@Controller
public class SimpleController {

    private final RoomRepository roomRepository;
    private final PersonRepository personRepository;
    private final StayRepository stayRepository;

    public SimpleController(RoomRepository roomRepository, PersonRepository personRepository, StayRepository stayRepository) {
        this.roomRepository = roomRepository;
        this.personRepository = personRepository;
        this.stayRepository = stayRepository;
    }

    @GetMapping("/simple")
    public void simple(){

        Stay stay = new Stay();

        stay.setStayFrom(LocalDate.now().minusDays(10));
        stay.setStayUntil(LocalDate.now().minusDays(6));
        stay.setRoom(roomRepository.findOne(3L));
        stay.setGuest((Guest) personRepository.findOne(1L));
        stay.setStayState(StayState.DONE);
        stayRepository.save(stay);

    }
}
