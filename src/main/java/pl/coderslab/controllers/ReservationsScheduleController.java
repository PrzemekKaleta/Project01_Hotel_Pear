package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.service.ReservationsScheduleService;
import java.time.LocalDate;

@Controller
public class ReservationsScheduleController {

    private final ReservationsScheduleService reservationsScheduleService;

    public ReservationsScheduleController(ReservationsScheduleService reservationsScheduleService) {
        this.reservationsScheduleService = reservationsScheduleService;
    }

    @GetMapping("/reservations")
    String getReservations(Model model){

        LocalDate a = LocalDate.now().plusDays(5);
        LocalDate z = LocalDate.now().plusDays(15);

      //  model.addAttribute("map",reservationsScheduleService.mapRoomShortStay(a, z, 5L));



        model.addAttribute("ooo","ccco");
        model.addAttribute("fullmap",reservationsScheduleService.fullMapRoomsShortStay(a, z));



        return "reservations";
    }
}
