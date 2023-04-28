package com.project.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
@DynamicInsert
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "dt_account_id")
    private Account dtAccount;
    private String crAccount;
    private Double amount;
    private String currency;
    @CreationTimestamp
    private Date trDate;
    @ColumnDefault("1")
    private Integer active;
}
