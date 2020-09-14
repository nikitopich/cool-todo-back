package ru.fallindawn.cooltodoback.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @SequenceGenerator(name = "tasks_id_generator", sequenceName = "sq_tasks_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_id_generator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @Column(unique = true, nullable = false)
    private String name;

    private LocalDateTime deadline;

    private boolean ready;


}
