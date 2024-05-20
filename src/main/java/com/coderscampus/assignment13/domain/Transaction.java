package com.coderscampus.assignment13.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private LocalDateTime transactionDate;
    private Double amount;
    @Column(length = 1)
    private String type;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}