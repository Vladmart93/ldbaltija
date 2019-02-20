package company.tasks.boundary;

import company.auth.boundary.CurrentUser;
import company.tasks.model.ActivationEntity;
import company.tasks.model.ActivationStatus;
import company.tasks.model.TaskEntity;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class UserBean implements Serializable {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private CurrentUser currentUser;

    @Transactional
    public void activate(Long id) {
        TaskEntity task = em.find(TaskEntity.class, id);
        ActivationEntity activation = new ActivationEntity();
        activation.setTask(task);
        activation.setUser(currentUser.getUser());
        activation.setStatus(ActivationStatus.ACTIVE);

        em.persist(activation);
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
