package todomanager.demo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Mert Batuhan UNVERDI
 * @since 26.01.2022
 */
@Controller
public class TodoController {
    private final TodoService service;
    @Autowired
    public TodoController(TodoService service){
        this.service = service;
    }
    @GetMapping("/homepage")
    public String showTodoList(Model model, HttpSession session){
        try {
            String username = session.getAttribute("username").toString();
            int userId = (int) session.getAttribute("userId");
            List<Todo> todoList = service.getTodos(userId);
            model.addAttribute("listTodos", todoList);
            model.addAttribute("username", username);
            Todo todo = new Todo();
            model.addAttribute("userid",userId);
            model.addAttribute("todo",todo);
            return "homepage";
        }
        catch(Exception e){
            return "index";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("username");
        session.removeAttribute("userId");
        return "redirect:/";
    }
    @PostMapping("/add")
    public String addTodo(Todo todo){
        service.save(todo);
        return "redirect:/homepage";
    }
    @GetMapping("/homepage/delete/{id}")
    public String deleteTodo(@PathVariable("id") Integer id) throws TodoNotFoundException {
        service.delete(id);
        return "redirect:/homepage";
    }
    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model,HttpSession session)
    {
        try{
            Todo todo = service.get(id);
            model.addAttribute("todo",todo);
            Integer userId = (int) session.getAttribute("userId");
            model.addAttribute("userid",userId);
            return "edit";
        }
        catch(TodoNotFoundException e){
            return "redirect:/edit/{id}";
        }
    }
}
