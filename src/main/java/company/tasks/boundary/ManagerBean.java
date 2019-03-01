package company.tasks.boundary;

import company.auth.boundary.CurrentUser;
import company.tasks.model.ActivationEntity;
import company.tasks.model.ActivationStatus;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
@ViewScoped
@ManagedBean
public class ManagerBean implements Serializable {

    @PersistenceContext
    private EntityManager em;


    private List<ActivationEntity> activeResult;
    private List<ActivationEntity> confirmedResult;
    private List<ActivationEntity> finishedResult;
    private List<ActivationEntity> endResult;


    public void prepare() {
        activeResult = new ArrayList<>();

        List<ActivationEntity> userActivations = em.createQuery(
                "select a from Activation a where a.status = 'ACTIVE'", ActivationEntity.class)
                .getResultList();

        for (ActivationEntity a : userActivations) {
            Long activationId = a.getId();

            Optional<ActivationEntity> firstActivations = em.createQuery(
                    "select a from Activation a " +
                            "where a.task = :task and a.status <> 'END' " +
                            "order by a.created", ActivationEntity.class)
                    .setParameter("task", a.getTask())
                    .getResultStream()
                    .findFirst();

            if (firstActivations.isEmpty() || firstActivations.get().getId().equals(activationId)) {
                activeResult.add(a);
            }
        }

        confirmedResult = em.createQuery(
                "select a from Activation a " +
                        "where a.status = 'CONFIRME'", ActivationEntity.class)
                .getResultList();

        finishedResult = em.createQuery(
                "select a from Activation a " +
                        "where a.status = 'FINISH'", ActivationEntity.class)
                .getResultList();

        endResult = em.createQuery(
                "select a from Activation a " +
                        "where a.status = 'END'", ActivationEntity.class)
                .getResultList();
    }

    @Transactional
    public void confirmTask(ActivationEntity activations) {
        ActivationEntity a = em.merge(activations);
        a.setStatus(ActivationStatus.CONFIRME);
        prepare();
    }

    @Transactional
    public void finishTask(ActivationEntity activations) {
        ActivationEntity a = em.merge(activations);
        a.setStatus(ActivationStatus.FINISH);
        prepare();
    }

    @Transactional
    public void endTask(ActivationEntity activations) {
        ActivationEntity a = em.merge(activations);
        a.setStatus(ActivationStatus.END);
        prepare();
    }

    @Transactional
    public void removeActivation(long id) {
        ActivationEntity activation = em.find(ActivationEntity.class, id);
        em.remove(activation);
        prepare();
    }


    public List<ActivationEntity> getActiveResult() {
        return activeResult;
    }

    public List<ActivationEntity> getConfirmedResult() {
        return confirmedResult;
    }

    public List<ActivationEntity> getFinishedResult() {
        return finishedResult;
    }

    public List<ActivationEntity> getEndResult() {
        return endResult;
    }
}

