package todomanager.demo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

/**
 * @author Mert Batuhan UNVERDI
 * @since 26.01.2022
 */
@Service
public class TodoService {
    @Autowired
    private TodoRepository repo;

    public List<Todo> listAll(){
        return (List<Todo>) repo.findAll();
    }

    public void save(Todo todo) {
        repo.save(todo);
    }

    public void delete(int id) throws TodoNotFoundException {
        Long count =  repo.countById(id);
        if(count==null)
            throw new TodoNotFoundException("Could not find any Todo with ID"+ id);
        repo.deleteById(id);
    }
    public Todo get(int id) throws TodoNotFoundException {
        Optional<Todo> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new TodoNotFoundException("Could not find any Todo with ID"+ id);
    }

    public List<Todo> getTodos(Integer userId){
        return repo.getTodos(userId);
    }



}
