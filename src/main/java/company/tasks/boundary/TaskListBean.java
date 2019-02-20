package company.tasks.boundary;

import company.tasks.model.ActivationEntity;
import company.tasks.model.TaskEntity;
import jdk.jfr.Name;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TaskListBean implements Serializable {

    @PersistenceContext
    private EntityManager em;
    private String term;

    public List<TaskEntity> getTasks() {
        if (term == null) {
            return em.createQuery("select t from Task t", TaskEntity.class)
                    .getResultList();
        } else {
            return em.createQuery("select t from Task t where lower(t.date) like :term", TaskEntity.class)
                    .setParameter("term", "%" + term.toLowerCase() + "%")
                    .getResultList();
        }
    }

        public String getTerm() {return term;}
        public void setTerm(String term) {this.term = term;}
}



