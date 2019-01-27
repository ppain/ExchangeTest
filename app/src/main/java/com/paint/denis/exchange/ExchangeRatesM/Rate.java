package com.paint.denis.exchange.ExchangeRatesM;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rate {

        @SerializedName("val")
        @Expose
        private String val;

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

}
