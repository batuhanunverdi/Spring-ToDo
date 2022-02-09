package todomanager.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @author Mert Batuhan UNVERDI
 * @since 25.01.2022
 */
@Controller
public class UserController {

    private final UserService service;
    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping("")
    public String getHomePage(Model model){
        User user = new User();
        model.addAttribute("user",user );
        return "index";
    }

    @GetMapping("/signUp")
    public String showSignUpPage(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "signUp";
    }
    @PostMapping("/homepage")
    public String logIn(User user,RedirectAttributes ra, HttpSession session){

        if(service.isRegisteredByUsername(user.getUsername())){
            User serviceUser = service.getUserByUsername(user.getUsername());
            if(serviceUser.getPassword().equals(user.getPassword())){
                session.setAttribute("username",user.getUsername());
                session.setAttribute("userId",serviceUser.getId());
                return "redirect:/homepage";
            }
            else{
                ra.addFlashAttribute("message","Email or password not correct");
                session.removeAttribute("username");
                session.removeAttribute("userId");
                return "redirect:/";
            }
        }
        else{
            ra.addFlashAttribute("message","User not found");
            return "index";
        }

    }
    @PostMapping("/signUp/save")
    public String saveUser(User user, RedirectAttributes ra){
        try {
            if(!service.isRegistered(user.getEmail()))
            {
               if(!service.isRegisteredByUsername(user.getUsername())){
                   if(user.getPassword().equals(user.getConfirmPassword()))
                   {
                       service.save(user);
                       ra.addFlashAttribute("message","The user has been saved successfully");
                       return "redirect:/";
                   }
                   else{
                       ra.addFlashAttribute("message","Password does not match.");
                       return "redirect:/signUp";
                   }
               }
               else{
                   ra.addFlashAttribute("message","The username has already registered.");
                   return "redirect:/signUp";
               }
            }
        }
        catch(Exception e){
            ra.addFlashAttribute("message","Email has already registered.");
            return "redirect:/signUp";
        }
        return "redirect:/signUp";
    }
}
