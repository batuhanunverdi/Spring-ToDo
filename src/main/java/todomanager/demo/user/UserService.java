package todomanager.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

/**
 * @author Mert Batuhan UNVERDI
 * @since 25.01.2022
 */
@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public void delete(int id) throws UserNotFoundException {
        Long count =  repo.countById(id);
        if(count==null)
            throw new UserNotFoundException("Could not find any users with ID"+ id);
        repo.deleteById(id);
    }
    public User get(int id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID"+ id);
    }
    public boolean isRegistered(String email){
        User user = getUserByEmail(email);
        return user != null;
    }
    public User getUserByEmail(String email){
        return repo.getUserByEmail(email);
    }

    public User getUserByUsername(String username){
        return repo.getUserByUsername(username);
    }
    public boolean isRegisteredByUsername(String username){
        User user = getUserByUsername(username);
        return user!=null;
    }
}
