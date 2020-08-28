package ru.fallindawn.cooltodoback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "users_id_generator", sequenceName = "sq_users_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_generator")
    private Long id;

    @Column
    private String name;

    @Column
    private String login;

    @Column
    private String email;
}
