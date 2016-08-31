package com.example.bipinp.mithun;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewCust extends AppCompatActivity {

    private Button btnSbmtCreate;
    private EditText custName;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cust);

        mydb = new DatabaseHelper(this);
        btnSbmtCreate = (Button)findViewById(R.id.sbmtcustname);

        custName = (EditText)findViewById(R.id.edtcustname);
        adddata();
    }

    private void adddata() {
        btnSbmtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean insertdata = mydb.insertdata(custName.getText().toString());
                if (insertdata)
                    Toast.makeText(NewCust.this, "Database Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(NewCust.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
