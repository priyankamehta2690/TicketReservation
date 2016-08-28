package com.example.ticketreservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class ViewTicketActivity extends AppCompatActivity {

    Globals gView = Globals.getInstance();
    EditText passangerName, sourceCity, destinationCity, departureDate, departureTime, returnDate, returnTime;
    RadioButton oneWay, roundTrip;
    Button finishBtn;
    ImageButton firstBtn, lastBtn, nextBtn, previousBtn;
    int trackTicketNumber = 1;

   ArrayList<Ticket> viewTicketList= new ArrayList<Ticket>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket);
        //viewTicketList = gView.getGlobalTicketList()
        // ;
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);


        finishBtn = (Button) findViewById(R.id.button_finish);
        firstBtn = (ImageButton) findViewById(R.id.btn_first);
        lastBtn = (ImageButton) findViewById(R.id.btn_last);
        nextBtn = (ImageButton) findViewById(R.id.btn_next);
        previousBtn = (ImageButton) findViewById(R.id.btn_previous);

        departureDate = (EditText) findViewById(R.id.date_departure);
        departureTime = (EditText) findViewById(R.id.editText_departureTime);
        returnDate = (EditText) findViewById(R.id.date_return);
        returnTime = (EditText) findViewById(R.id.time_return);
        sourceCity = (EditText) findViewById(R.id.editText_source);
        destinationCity = (EditText) findViewById(R.id.editText_destination);
        passangerName = (EditText) findViewById(R.id.editText_PassangerName);
        roundTrip = (RadioButton) findViewById(R.id.radioButton2_roundTrip);
        oneWay = (RadioButton) findViewById(R.id.radioButton_onWay);


        //=======Setup keyListner=======
        oneWay.setChecked(true);
        passangerName.setKeyListener(null);
        sourceCity.setKeyListener(null);
        destinationCity.setKeyListener(null);
        roundTrip.setKeyListener(null);
        oneWay.setKeyListener(null);
        departureDate.setKeyListener(null);
        departureTime.setKeyListener(null);
        returnDate.setKeyListener(null);
        returnTime.setKeyListener(null);

        //======== Setup end===============
        //Get Intent
        if (getIntent().getExtras() != null) {
            DataWrapper data = (DataWrapper) getIntent().getSerializableExtra("TICKET_LIST");
            viewTicketList = data.getTickets();
//            for (int i = 0; i < viewTicketList.size(); i++)
//                Log.d("Delete Activity: ", "Starting" + viewTicketList.iterator().next().toString());
        }



        setFields(1);

        firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackTicketNumber = 1;
                setFields(trackTicketNumber);

            }
        });

        lastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                trackTicketNumber = viewTicketList.size();
                setFields(trackTicketNumber);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i =0; i<viewTicketList.size();i++) {
//                    if (viewTicketList.get(i).getPassangerName().equals(passangerName.getText().toString())) {
//                        if (i == 0)
//                          trackTicketNumber =1;
//                        else if(i==1)
//                            trackTicketNumber = 2;
//                        else
                         trackTicketNumber = trackTicketNumber +1;

                        setFields(trackTicketNumber);
                   // }

                }
//                if(viewTicketList.size()==1)
//                    Toast.makeText(getApplicationContext(),"You have booked only one Ticket",Toast.LENGTH_LONG).show();
//                else

            }
        });

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(viewTicketList.size()==1)
                    Toast.makeText(getApplicationContext(),"You have booked only one Ticket",Toast.LENGTH_LONG).show();
                else
                {
                    trackTicketNumber= trackTicketNumber-1;
                    setFields(trackTicketNumber);
                }

            }
        });


       finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent i= new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
//                finish();
            }
        });


    }
//public methods


    public void setFields(int ticketId)
    {

        for(int i =0; i<viewTicketList.size();i++) {
            if (viewTicketList.get(i).getTicketNumber() == ticketId)
            {
                if(viewTicketList.get(i).getTypeOfTrip().equals("Round"))
                {
                    passangerName.setText(viewTicketList.get(i).getPassangerName());
                    sourceCity.setText(viewTicketList.get(i).getSourceCity());
                    destinationCity.setText(viewTicketList.get(i).getDestinationCity());
                    roundTrip.setChecked(true);
                    oneWay.setChecked(false);
                    returnDate.setVisibility(View.VISIBLE);
                    returnTime.setVisibility(View.VISIBLE);
                    departureDate.setText(viewTicketList.get(i).getDepartureDate());
                    departureTime.setText(viewTicketList.get(i).getDepartureTime());
                    returnDate.setText(viewTicketList.get(i).getReturnDate());
                    returnTime.setText(viewTicketList.get(i).getReturnTime());
                }
                else if (viewTicketList.get(i).getTypeOfTrip().equals("OneWay"))
                {
                    passangerName.setText(viewTicketList.get(i).getPassangerName());
                    sourceCity.setText(viewTicketList.get(i).getSourceCity());
                    destinationCity.setText(viewTicketList.get(i).getDestinationCity());
                    roundTrip.setChecked(false);
                    oneWay.setChecked(true);
                    returnDate.setVisibility(View.INVISIBLE);
                    returnTime.setVisibility(View.INVISIBLE);
                    departureDate.setText(viewTicketList.get(i).getDepartureDate());
                    departureTime.setText(viewTicketList.get(i).getDepartureTime());
                }
            }
        }

        }//end setup view


    }




