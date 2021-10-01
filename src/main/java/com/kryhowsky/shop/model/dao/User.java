package com.kryhowsky.shop.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data //gettery, settery, equals, hashCode, toString dla wszystkich pól w obiekcie + wieloargumentowy konstruktor dla finalnych zmiennych
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor // wszystkie pola
@Table(indexes = @Index(columnList = "email", unique = true))
public class User { // obiekty używane do komunikacji z warstwą danych i klientem; Data Access Object

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity - autoincrement dla pola
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
