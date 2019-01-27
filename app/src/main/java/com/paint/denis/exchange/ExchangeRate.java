package com.paint.denis.exchange;

public class ExchangeRate {
    public static double getExchange(double first, double exchangeRate) {

        //Double result = Double.valueOf(substring(11).replace("}","")) *  exchangeRate;
        //rounding up 100
        Double result = 1.0;
        result = Math.round(1000.0 * result)/1000.0;

        return result;
    }
}
