package com.menu.pubganalyzer.domain.model.enums;

public enum DeleteCondition {
    EQ("="),
    LT("<"),
    LE("<="),
    GT(">"),
    GE(">="),
    ;

    private final String operator;

    DeleteCondition(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
