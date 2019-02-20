package company.tasks.model;


import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "Task")
@Table(name = "tasks")
public class TaskEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Shouldn't be empty!")
    @Size(max = 1000)
    @Column(name = "task", length = 1000)
    private String task;

    @Column(name = "date")
    private Date date;

    @Size(max = 200)
    private String adress;

    @Size(max = 100)
    private String name;

    @Size(max = 100)
    @Column(name = "header", length = 1000, nullable = false)
    private String header;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeader() { return header; }

    public void setHeader(String header) { this.header = header; }
}
