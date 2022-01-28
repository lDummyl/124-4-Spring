package com.example.demo.db.ent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "public")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String superName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<CarEntity> carEntityList;

}
