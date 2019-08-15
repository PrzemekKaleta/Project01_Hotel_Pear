package pl.coderslab.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.dto.PersonDTO;
import pl.coderslab.entity.Guest;
import pl.coderslab.repository.PersonRepository;
import pl.coderslab.session.PersonSession;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final PersonSession personSession;

    public PersonController(PersonRepository personRepository, PasswordEncoder passwordEncoder, PersonSession personSession) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.personSession = personSession;
    }

    @GetMapping("/register")
    public String registerGet(Model model){
        model.addAttribute("guest", new Guest());
        return "form/registration";
    }

    @PostMapping("/register")
    public String registerPost(@Valid Guest guest, BindingResult result, HttpServletResponse response){
        if(result.hasErrors()){
            return "form/registration";
        }
        if(personRepository.existsPersonByEmail(guest.getEmail())){
            result.addError(new FieldError("guest", "email", "email już jest zajęty"));
            return "form/registration";
        }

        String encodedPassword = passwordEncoder.encode(guest.getPassword());
        guest.setPassword(encodedPassword);
        personRepository.save(guest);
        personSession.setEmail(guest.getEmail());

        Cookie cookie = new Cookie("log","Guest");
        cookie.setMaxAge(60*60);
        cookie.setPath("/");
        response.addCookie(cookie);

        if(!(personSession.getReserveAsk()==null)){
            return "redirect:../reserv/ask";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginGet(Model model){
        model.addAttribute("personDTO", new PersonDTO());
        return "form/login";
    }
    @PostMapping("/login")
    public String loginPost(@Valid PersonDTO personDTO, BindingResult result, HttpServletResponse response){

        if(result.hasErrors()){
            return "form/login";
        }
        String codedPassword = personRepository.findPasswordByEmail(personDTO.getEmail());
        if(passwordEncoder.matches(personDTO.getPassword(),codedPassword)){
            personSession.setEmail(personDTO.getEmail());

            Cookie cookie = new Cookie("log",personRepository.findPersonByEmail(personDTO.getEmail()).getClass().getSimpleName());
            cookie.setMaxAge(60*60);
            cookie.setPath("/");
            response.addCookie(cookie);

            if(!(personSession.getReserveAsk()==null)) {


                return "redirect:../reserv/ask";
            }
            return "redirect:/";
        }else{
            result.addError(new ObjectError("personDTO", "Podano nieprawidłowy adres lub hasło"));
        }

        return "form/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        personSession.setEmail(null);
        personSession.setReserveAsk(null);
        Cookie[] cookies = request.getCookies();
        for(Cookie c:cookies){
            System.out.println(c.getName() + " " + c.getValue() + " " + c.getMaxAge());
        }
        String cookieName = "log";
        for (Cookie c : cookies) {
            if (cookieName.equals(c.getName())) {
                c.setPath("/");
                c.setValue("false");
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }


        return "redirect:/";
    }

}
