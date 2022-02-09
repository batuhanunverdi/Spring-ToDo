package todomanager.demo;


import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author Mert Batuhan UNVERDI
 * @since 25.01.2022
 */
public class MainController {
    @GetMapping("")
    public String showHomePage(){
        return "index";
    }

}
