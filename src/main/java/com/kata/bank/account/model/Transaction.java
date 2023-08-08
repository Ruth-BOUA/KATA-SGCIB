package com.kata.bank.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Date;

/**
 * The type Transaction.
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTIONS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_generator")
    @SequenceGenerator(name = "transactions_generator", sequenceName = "TRANSACTIONS_SEQ", allocationSize = 1)
    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "operation", nullable = false)
    @Enumerated(EnumType.STRING)
    private Operation operation;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "execution_date", nullable = false)
    private Date executionDate;


}