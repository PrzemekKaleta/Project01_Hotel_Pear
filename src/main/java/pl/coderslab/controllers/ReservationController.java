package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.dto.ResevAsk;

@Controller
@RequestMapping("/reserv")
public class ReservationController {

    @GetMapping("/ask")
    public String getAsk(Model model){
        model.addAttribute("reservAsk", new ResevAsk());
        return "form/reserv";
    }
}
