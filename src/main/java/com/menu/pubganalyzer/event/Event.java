package com.menu.pubganalyzer.event;

import org.slf4j.MDC;

public abstract class Event {
    public String getTransactionId() {
        return MDC.get("transactionId");
    }
}
