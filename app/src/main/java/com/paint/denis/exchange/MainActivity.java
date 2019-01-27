package com.paint.denis.exchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.paint.denis.exchange.CurrenciesModel.CurrenciesModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Request api;
    private Gson gson;
    private Call<CurrenciesModel> currenciesModelCall;
    private Double exchangeRate = 0.0;
    ArrayList<String> currencies = new ArrayList<>();

    Spinner spinnerUp;
    Spinner spinnerDown;
    //TextView test;
    EditText upEditText;
    TextView downEditText;

    public Request getApi() {
        return api;
    }

    public Gson getGson() {
        return gson;
    }

    public void setApi(Request api) {
        this.api = api;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerUp = (Spinner) findViewById(R.id.upSpinner);
        spinnerDown = (Spinner) findViewById(R.id.downSpinner);
        upEditText = findViewById(R.id.upEditText);
        downEditText = findViewById(R.id.downEditText);

        setApi();
        loadCurrencies();


        upEditText.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                Double result = Math.round(1000.0 * ( Double.parseDouble(c.toString()) * exchangeRate))/1000.0;
                //Math.round(1000.0 * result)/1000.0;
                downEditText.setText(result.toString());
            }
        });


        spinnerUp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                String item = String.valueOf(parent.getItemAtPosition(selectedItemPosition));
                getExchangeRates(item,getSpinnerDown());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                String item = String.valueOf(parent.getItemAtPosition(selectedItemPosition));
                getExchangeRates(getSpinnerUp(),item);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void setSpinnerUp(String key){
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerUp.setAdapter(adapter);
        // Найдем нужную валюту
        int position = adapter.getPosition(key);
        spinnerUp.setSelection(position);
    }

    private void setSpinnerDown(String key){
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerDown.setAdapter(adapter);
        // Найдем нужную валюту
        int position = adapter.getPosition(key);
        spinnerDown.setSelection(position);
    }

    public String getSpinnerUp() {
        String selected = spinnerUp.getSelectedItem().toString();
        return selected;
    }

    public String getSpinnerDown() {
        String selected = spinnerDown.getSelectedItem().toString();
        return selected;
    }


    private void setApi() {
        api = Request.Factory.createPostsApi();
        gson = new Gson();
    }

    private void loadCurrencies() {
        currenciesModelCall = api.getCurrencies();
        currenciesModelCall.enqueue(new Callback<CurrenciesModel>() {
            @Override
            public void onResponse(Call<CurrenciesModel> call, Response<CurrenciesModel> response) {
                Log.i("serviceT", "onRespone");
                if (response.isSuccessful() && !currenciesModelCall.isCanceled()) {
                    Log.i("serviceT", "response Successful");
                    CurrenciesModel currenciesModel = new CurrenciesModel();
                    currenciesModel = response.body();

                    List<Field> fieldList = new ArrayList<Field>();

                    fieldList.addAll(Arrays.asList(currenciesModel.getResults().getClass().getDeclaredFields()));
                    //for(int i=0; i < currenciesModel.getResults();){
                    Log.i("serviceT", "response Successful");
                    for(int i=0;i<fieldList.size();i++){
                        String f = fieldList.get(i).getName().toUpperCase();
                        currencies.add(f);
                        Log.i("serviceT", "response Successful");
                    }
                    setSpinnerUp("USD");
                    setSpinnerDown("RUB");
                }
            }

            @Override
            public void onFailure(Call<CurrenciesModel> call, Throwable t) {
                Log.i("serviceT", "response FAIL");
            }
        });

    }


    private void getExchangeRates(String first, String second) {
        String firstParam = first + "_" + second;
        Call<JsonObject> req=api.getExchange(firstParam,"y");
        req.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && !req.isCanceled()) {

                    String forDouble = String.valueOf(response.body().get(firstParam).getAsJsonObject().get("val"));

                    exchangeRate = Double.parseDouble(forDouble);
                    //setTest(String.valueOf(exchangeRate));
                }
                Log.i("serviceT", "response Succesful ne vajniy");
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.i("serviceT", "response FAIL vajniy");
            }
        });
    }
}