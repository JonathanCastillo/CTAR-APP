package com.jvides.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jvides.myapplication.entities.Usuarios;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Usuarios naturalPerson = (Usuarios) getIntent().getSerializableExtra("Usuarios");


        TextView fullName = (TextView) findViewById(R.id.tvFullName);
        TextView identificacionNumber = (TextView) findViewById(R.id.tvIdentificacionNumber);

        TextView taxIdentificacionNumber = (TextView) findViewById(R.id.tvTaxIdentificacionNumber);
        TextView birthDate = (TextView) findViewById(R.id.tvBirthDate);
        TextView gender = (TextView) findViewById(R.id.tvGender);
        TextView civilStatus = (TextView) findViewById(R.id.tvCivilStatus);
        TextView nationality = (TextView) findViewById(R.id.tvNationality);


        Log.i("LOGCAT", naturalPerson.getNombres());

        fullName.setText(naturalPerson.getNombres() +" "+ naturalPerson.getApellidos());
        identificacionNumber.setText(naturalPerson.getTipo_Usuario());
        taxIdentificacionNumber.setText(naturalPerson.getTipo_Usuario());

        birthDate.setText(naturalPerson.getPassword());
        gender.setText(naturalPerson.getUser_Name());
        civilStatus.setText(naturalPerson.getPassword());
        nationality.setText(naturalPerson.getApellidos());
    }
}
