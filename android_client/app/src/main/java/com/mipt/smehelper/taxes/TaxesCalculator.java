package com.mipt.smehelper.taxes;

import com.mipt.smehelper.models.Transaction;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;


public final class TaxesCalculator {

    final List<Transaction> transactionHistory;
    final List<DateTime> transactionsDateTime;
    final boolean isSmallUsn;

    private static final int OPS_VALUE = 29354;
    private static final int OMS_VALUE = 6884;
    private static final DateTime QUARTER1_DATE = new DateTime("04-25-2019T0:0:0");
    private static final DateTime QUARTER2_DATE = new DateTime("06-25-2019T0:0:0");
    private static final DateTime QUARTER3_DATE = new DateTime("10-25-2019T0:0:0");
    private static final DateTime QUARTER4_DATE = new DateTime("12-31-2019T0:0:0");

    public static TaxesCalculator create(List<Transaction> transactionsHistory, boolean isSmallUsn) {
        List<DateTime> transactionsDateTime = new ArrayList<>();
        for (Transaction t : transactionsHistory) {
            transactionsDateTime.add(new DateTime(t.getDate()));
        }
        return new TaxesCalculator(transactionsHistory, isSmallUsn, transactionsDateTime);
    }

    public TaxesCalculator(List<Transaction> transactionsHistory, boolean isSmallUsn, List<DateTime> transactionsDateTime) {
        this.transactionHistory = transactionsHistory;
        this.transactionsDateTime = transactionsDateTime;
        this.isSmallUsn = isSmallUsn;
    }

    public List<Tax> calculate() {
        return null;
    }
}
