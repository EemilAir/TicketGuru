package bugivelhot.ticketguru.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        // Ohjaa kaikki muut pyynnöt index.html-tiedostoon
        return "forward:/index.html";
    }
}
