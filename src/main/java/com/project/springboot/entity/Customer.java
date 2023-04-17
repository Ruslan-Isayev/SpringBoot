package com.project.springboot.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "customer")
@Data
@DynamicInsert
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String surname;
    private Date dob;
    private String phone;
    @CreationTimestamp
    private Date dataDate;
    @ColumnDefault("1")
    private Integer active;


}
