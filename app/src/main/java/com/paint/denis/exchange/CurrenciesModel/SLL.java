
package com.paint.denis.exchange.CurrenciesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SLL {

    @SerializedName("currencyName")
    @Expose
    private String currencyName;
    @SerializedName("id")
    @Expose
    private String id;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
