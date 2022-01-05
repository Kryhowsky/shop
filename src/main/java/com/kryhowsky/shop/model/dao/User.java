package com.kryhowsky.shop.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = @Index(name = "idx_email", columnList = "email", unique = true))
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @NotAudited
    private String password;
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(name = "user_role", inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
