package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author chervinko <br>
 * 27.07.2021
 */
@Setter
@Getter
@Entity
@Table(name = "driver")
@EntityListeners(AuditingEntityListener.class)
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_gen")
    @SequenceGenerator(name = "driver_gen", sequenceName = "driver_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", length = 150, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 150)
    private String middleName;

    @Column(name = "last_name", length = 150, nullable = false)
    private String lastName;

    @Column(name = "mobile_phone", length = 50, nullable = false)
    private String mobilePhone;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "driver",  fetch = FetchType.LAZY)
    private List<Car> cars;
}
