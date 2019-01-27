package com.paint.denis.exchange;

import android.os.Build;
import android.widget.Spinner;

import org.json.JSONException;

import java.io.IOException;
//import java.util.Scanner;

public class Form {

    public void begin() throws IOException, JSONException {

        Param param = new Param();



        //Scanner input = new Scanner(System.in);

        //System.out.println("    Доступные валюты:");
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        //    (Currencies.getCurrencies()).forEach(curr -> System.out.print(curr + " "));
        //}

        //System.out.println("\n    Первая валюта:");
        //param.setFirstCurrency(input.next());
        param.setFirstCurrency("USD");

        //System.out.println("    Вторая валюта:");
        //param.setSecondCurrency(input.next());
        param.setSecondCurrency("RUB");

        //System.out.println("    Сумма первой валюты:");
        //param.setSum(input.nextLong());
        param.setSum(10.3);

        //double responce = ExchangeRate.getExchange(param.getFirstCurrency(), param.getSecondCurrency(), param.getSum());
        //System.out.println("    Результат конвертирования:\n" + responce);
    }


}
