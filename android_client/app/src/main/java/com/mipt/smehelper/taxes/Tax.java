package com.mipt.smehelper.taxes;

import org.joda.time.DateTime;

public final class Tax {

    private DateTime timeToPay;
    private TaxType taxType;
    private double value;

    public Tax(DateTime timeToPay, double value, TaxType taxType) {
        this.timeToPay = timeToPay;
        this.value = value;
        this.taxType = taxType;
    }

    public DateTime getTimeToPay() {
        return timeToPay;
    }

    public void setTimeToPay(DateTime timeToPay) {
        this.timeToPay = timeToPay;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }

}
