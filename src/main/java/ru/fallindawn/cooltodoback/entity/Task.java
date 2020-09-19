package ru.fallindawn.cooltodoback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {
    @Id
    @SequenceGenerator(name = "tasks_id_generator", sequenceName = "sq_tasks_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_id_generator")
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private String name;

    private LocalDateTime deadline;

    private boolean ready;

    public Task(User user, String name, LocalDateTime deadline, boolean ready) {
        this.user = user;
        this.name = name;
        this.deadline = deadline;
        this.ready = ready;
    }
}
