package com.pfe.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Role {

    @Id
    private Long id;
    private String label ;
    private String descrip;


}
