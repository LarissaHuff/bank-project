package com.lariss.bankproject.model;

import com.lariss.bankproject.enumeration.MovementType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table
@Entity
@Getter
@Setter
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MovementType type;

    private BigDecimal amount;

    private LocalDate movementDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
