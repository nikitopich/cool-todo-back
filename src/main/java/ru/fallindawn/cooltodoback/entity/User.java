package ru.fallindawn.cooltodoback.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import ru.fallindawn.cooltodoback.entity.security.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "users_id_generator", sequenceName = "sq_users_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_generator")
    private Long id;

    @NaturalId
    private String login;

    private String hashPassword;

    @Column(unique = true, nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(String login) {
        this.login = login;
    }

    public User(String login, String hashPassword, String email) {
        this.login = login;
        this.email = email;
        this.hashPassword = hashPassword;
    }
}
