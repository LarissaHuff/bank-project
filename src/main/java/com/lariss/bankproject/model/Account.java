package com.lariss.bankproject.model;

import com.lariss.bankproject.enumeration.AccountStatus;
import com.lariss.bankproject.enumeration.AccountStatusDetail;
import com.lariss.bankproject.enumeration.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private LocalDate createDate;
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    private AccountStatusDetail statusDetail;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Movement> movements;

}
