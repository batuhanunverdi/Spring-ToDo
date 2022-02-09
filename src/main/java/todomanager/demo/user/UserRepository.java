package todomanager.demo.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



/**
 * @author Mert Batuhan UNVERDI
 * @since 25.01.2022
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    Long countById(Integer id);
    @Query(value = "SELECT * FROM users WHERE email=?1", nativeQuery = true)
    User getUserByEmail(String email);

    @Query(value ="SELECT * FROM users WHERE username=?1",nativeQuery = true)
    User getUserByUsername( String username);


}
