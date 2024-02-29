package com.pfe.entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password ;
    private Long fonction_id;
    private String firstName;
    private String lastName;
    private String adress;
    private Integer tel ;
    private String email;
    @ManyToOne
    private Role role;

}
