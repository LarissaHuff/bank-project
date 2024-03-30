package com.lariss.bankproject.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatusDetail {
    NO_MOVEMENTS("Account has no movements for more than a year."),
    NO_PAYMENT("Account has a pending delayed payment.");

    private final String description;

}
