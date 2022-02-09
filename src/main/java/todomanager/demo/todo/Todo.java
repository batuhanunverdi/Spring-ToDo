package todomanager.demo.todo;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Mert Batuhan UNVERDI
 * @since 26.01.2022
 */
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 15)
    private Integer userId;

    @Column(length = 25)
    private String title;

    @Column(length = 45)
    private String description;

    @Column(length = 15)
    private String date;

    @Column(length = 15)
    private String priority;

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
    public String getPriority() {
        return priority;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
