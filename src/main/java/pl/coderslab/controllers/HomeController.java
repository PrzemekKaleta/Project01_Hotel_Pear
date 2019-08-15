package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.repository.PersonRepository;
import pl.coderslab.session.PersonSession;

@Controller
public class HomeController {

    private final PersonSession personSession;

    public HomeController(PersonSession personSession) {
        this.personSession = personSession;
    }

    @GetMapping("/")
    public String home(Model model){

        if(personSession.getEmail()==null){
            model.addAttribute("log", false);
        }else{
            model.addAttribute("log", true);
        }


        return "home";
    }
}
