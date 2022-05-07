package ru.geekbrains.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 512)
    private String password;

    @ManyToMany
    @JoinTable(name = "users_rols",
                joinColumns = @JoinColumn(name = "users_id"),
                inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles = new HashSet<>();

    public User(Long id, String username, String email, String password,Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
