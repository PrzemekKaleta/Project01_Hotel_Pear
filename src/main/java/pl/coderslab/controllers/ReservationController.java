package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.dto.ReserveAsk;
import pl.coderslab.service.ReservationService;
import pl.coderslab.service.SimpleReservationService;
import pl.coderslab.session.PersonSession;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/reserv")
public class ReservationController {

    private final ReservationService reservationService;
    private final PersonSession personSession;

    public ReservationController(SimpleReservationService reservationService, PersonSession personSession) {
        this.reservationService = reservationService;
        this.personSession = personSession;
    }

    @GetMapping("/ask")
    public String getAsk(Model model){
        if(!(personSession.getReserveAsk()==null)){
            model.addAttribute("reserveAsk", personSession.getReserveAsk());
        }else {
            model.addAttribute("reserveAsk", new ReserveAsk());
        }
        return "form/reserve";
    }

    @PostMapping("/ask")
    public String postAsk(Model model, @Valid ReserveAsk reserveAsk, BindingResult result){
        if(result.hasErrors()){
                 result.addError(new FieldError("reserveAsk", "dateFrom", "Nieprawidłowe daty"));
                 return "form/reserve";
        }

        if(reservationService.canReserve(reserveAsk).isPossible()){

            model.addAttribute("reply", true);

            model.addAttribute("cost",reservationService.givePrice(reserveAsk));

            model.addAttribute("emptyLog", personSession.getEmail()==null);

            personSession.setReserveAsk(reserveAsk);

        }else{
            model.addAttribute("reply", false);

        }

        return "reply";
    }




    @ModelAttribute("roomsCapasity")
    public List<Integer> roomsCapasity() {
        Integer[] capasity = new Integer[] {1,2,3,4};
        return Arrays.asList(capasity);
    }

    @ModelAttribute("dateFrom")
    public List<LocalDate> dateFrom() {

        List <LocalDate> from = new ArrayList<>();
        for(int i = 1; i < 60 ; i++){
            from.add(LocalDate.now().plusDays(i));
        }
        return from;
    }
    @ModelAttribute("dateUntil")
    public List<LocalDate> dateUntil() {

        List <LocalDate> until = new ArrayList<>();
        for(int i = 2; i < 90 ; i++){
            until.add(LocalDate.now().plusDays(i));
        }
        return until;
    }

}
