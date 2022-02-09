package todomanager.demo.todo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

/**
 * @author Mert Batuhan UNVERDI
 * @since 26.01.2022
 */
public interface TodoRepository extends CrudRepository<Todo, Integer> {
    Long countById(Integer id);
    @Query(value = "SELECT * FROM todos WHERE user_id=?1", nativeQuery = true)
    List<Todo> getTodos(@Param("userid") Integer userid);
}
