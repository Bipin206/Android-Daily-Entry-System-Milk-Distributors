package com.example.bipinp.mithun;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Search extends AppCompatActivity {


    Button btnSearch,btnDelete;
    Spinner searchSpinner;
    String position;
    TextView balance;
    DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mydb = new DatabaseHelper(this);

        btnSearch = (Button) findViewById(R.id.btn_search_cust);
        btnDelete = (Button) findViewById(R.id.btn_delete_cust);
        searchSpinner = (Spinner) findViewById(R.id.spin_name_list);
        balance = (TextView)findViewById(R.id.balance);

        loadSpinner();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = searchSpinner.getSelectedItem().toString();
           boolean deletedata =  mydb.deleteRow(position);
                if(deletedata)
                    Toast.makeText(Search.this, "Row Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Search.this, "Delete Error", Toast.LENGTH_SHORT).show();

            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = searchSpinner.getSelectedItem().toString();
                String bal = mydb.balance(position);

                balance.setText(bal);

            }
        });

    }

    private void loadSpinner() {
        DatabaseHelper db = new DatabaseHelper(this);
        List<String> labels = db.getAllLabels();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,labels);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        searchSpinner.setAdapter(dataAdapter);
    }

}
