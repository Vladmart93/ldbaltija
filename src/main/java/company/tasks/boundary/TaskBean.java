package company.tasks.boundary;

import company.tasks.model.TaskEntity;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;

@ViewScoped
@Named
public class TaskBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    private TaskEntity task = new TaskEntity();
    private boolean created = false;


    public void openTask() {
        task = em.find(TaskEntity.class, id);
    }


    @Transactional
    public void changeTask() {
        TaskEntity task = em.find(TaskEntity.class, id);
        task.setHeader(this.task.getHeader());
        task.setName(this.task.getName());
        task.setDate(this.task.getDate());
        task.setAdress(this.task.getAdress());
        task.setTask(this.task.getTask());
        created = true;
    }

    @Transactional
    public void removeTask(long id) {
        TaskEntity task = em.find(TaskEntity.class, id);
        em.remove(task);
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public boolean isCreated() {
        return created;
    }

}
