package com.paint.denis.exchange;

public class Param {
    private String firstCurrency = "";
    private String secondCurrency = "";
    private double sum;

    public String getFirstCurrency() {
        return firstCurrency;
    }


    public void setFirstCurrency(String firstCurrency) {
        this.firstCurrency = firstCurrency;
    }

    public String getSecondCurrency() {
        return secondCurrency;
    }

    public void setSecondCurrency(String secondCurrency) {
        this.secondCurrency = secondCurrency;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
