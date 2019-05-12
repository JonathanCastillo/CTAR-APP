package com.jvides.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jvides.myapplication.entities.NaturalPerson;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        NaturalPerson naturalPerson = (NaturalPerson) getIntent().getSerializableExtra("NaturaPerson");


        TextView fullName = (TextView) findViewById(R.id.tvFullName);
        TextView identificacionNumber = (TextView) findViewById(R.id.tvIdentificacionNumber);

        TextView taxIdentificacionNumber = (TextView) findViewById(R.id.tvTaxIdentificacionNumber);
        TextView birthDate = (TextView) findViewById(R.id.tvBirthDate);
        TextView gender = (TextView) findViewById(R.id.tvGender);
        TextView civilStatus = (TextView) findViewById(R.id.tvCivilStatus);
        TextView nationality = (TextView) findViewById(R.id.tvNationality);


        Log.i("LOGCAT", naturalPerson.getFirstName());

        fullName.setText(naturalPerson.getFirstName() +" "+ naturalPerson.getMiddleName() +" "+ naturalPerson.getLastName() );
        identificacionNumber.setText(naturalPerson.getIdentificationNumber());
        taxIdentificacionNumber.setText(naturalPerson.getIdentificationNumber());

        birthDate.setText(naturalPerson.getBirthDate());
        gender.setText(naturalPerson.getGender().getGender());
        civilStatus.setText(naturalPerson.getCivilStatus().getCivilStatus());
        nationality.setText(naturalPerson.getNationality().getNationality());
    }
}
