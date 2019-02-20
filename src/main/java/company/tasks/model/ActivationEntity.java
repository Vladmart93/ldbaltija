package company.tasks.model;

import company.auth.model.UserEntity;

import javax.inject.Named;
import javax.persistence.*;
import javax.xml.registry.infomodel.User;
import java.time.LocalDateTime;

@Named
@Entity(name="Activation")
@Table(name="activations")
public class ActivationEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private TaskEntity task;
    @ManyToOne
    private UserEntity user;
    @Column
    @Enumerated(EnumType.STRING)
    private ActivationStatus status;

    @Column
    private LocalDateTime created;

    @PrePersist
    public void onCreate() {created=LocalDateTime.now();}

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ActivationStatus getStatus() {
        return status;
    }

    public void setStatus(ActivationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "ActivationEntity{" +
                "id=" + id +
                ", task=" + task +
                ", user=" + user +
                ", status=" + status +
                ", created=" + created +
                '}';
    }
}
