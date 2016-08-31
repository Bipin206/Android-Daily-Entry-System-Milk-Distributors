package com.example.bipinp.mithun;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public  class DailyEntry extends AbstractActivity implements TextWatcher {
    private Button btnsbmtentry;
    private Spinner spiner2;
    private EditText milkcaret,amount,amountp,prevbal,currentBal,totalBal;
    DatabaseHelper mydb;
    int amnt,amntp,sub,currB,prevB,totalB;
    String sAmnt,sAmntp,subvalue,sCurrBal,sPrevBal,stotalBal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_entry);
        mydb = new DatabaseHelper(this);


        btnsbmtentry=(Button)findViewById(R.id.btnsubmitentry);
        spiner2=(Spinner)findViewById(R.id.spinner2);
        milkcaret=(EditText)findViewById(R.id.txt1_de);
        amount=(EditText)findViewById(R.id.txt2_de);
        amountp=(EditText)findViewById(R.id.txt3_de);
        prevbal = (EditText)findViewById(R.id.txt4_de);

        totalBal = (EditText)findViewById(R.id.txt4_deTotBal);
        amount.addTextChangedListener(this);
        amountp.addTextChangedListener(this);
        prevbal.addTextChangedListener(this);


        adddata2();
        loadspinner();
        spiner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pos = parent.getItemAtPosition(position).toString();
                String bale = mydb.balance(pos);
                prevbal.setText(bale);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void loadspinner() {
        DatabaseHelper db = new DatabaseHelper(this);
        List<String> labels = db.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,labels);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spiner2.setAdapter(dataAdapter);
    }

    private void adddata2() {
        btnsbmtentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean insertdata2 = mydb.insertdata2(spiner2.getSelectedItem().toString(),milkcaret.getText().toString(),amount.getText().toString(),amountp.getText().toString(),totalBal.getText().toString());
                if (insertdata2)
                    Toast.makeText(DailyEntry.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DailyEntry.this, "Databse Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        currentBal = (EditText)findViewById(R.id.txt4_deCurrB);


        sAmnt = amount.getText().toString();
        sAmntp = amountp.getText().toString();
        sCurrBal = currentBal.getText().toString();
        sPrevBal = prevbal.getText().toString();
        try {
            amnt = Integer.parseInt(sAmnt);
            amntp = Integer.parseInt(sAmntp);
            sub = amnt-amntp;
            currB = Integer.parseInt(sCurrBal);
            prevB = Integer.parseInt(sPrevBal);
            totalB = currB+prevB;
        }catch (NumberFormatException e){

        }

        subvalue = String.valueOf(sub);
        currentBal.setText(subvalue);
        stotalBal = String.valueOf(totalB);
        totalBal.setText(stotalBal);

    }
}
