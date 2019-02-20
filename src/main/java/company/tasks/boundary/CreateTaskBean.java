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
public class CreateTaskBean implements Serializable {

    @PersistenceContext
    private EntityManager em;

    private TaskEntity task = new TaskEntity();
    private boolean created = false;

    @Transactional
    public String createTask(){
        em.persist(task);
        task = new TaskEntity();
        created = true;
        return null;
    }

    public boolean isCreated() {return created;}
    public TaskEntity getTask() {return task;}
    public void setTask(TaskEntity task) {this.task=task;}
}
