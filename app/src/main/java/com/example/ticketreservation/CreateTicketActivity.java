package com.example.ticketreservation;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;

@SuppressWarnings("ALL")
public class CreateTicketActivity extends AppCompatActivity {

    Globals gCreate = Globals.getInstance();
    public static final String PASSTICKET = "TICKET";
    private static final int Date_id = 0;
    private static final int Time_id = 1;

    Button printSummary;
    EditText passangerName, sourceCity, destinationCity, departureDate, departureTime, returnDate, returnTime;
    RadioButton oneWay, roundTrip;
    TextView intentName;

    ArrayList<Ticket> currentTicketList;//= new ArrayList<Ticket>();

    String listCity[] = {"Albany,NY", "Atlanta, GA", "Boston, MA", "Charlotte, NC", "Chicago, IL", "Greenville, SC", "Houston, TX", "Las Vegas, NV", "Los Angeles, CA", "Miami, FL", "Myrtle Beach, SC", "New York, NY", "Portland, OR", "Raleigh, NC", "San Jose, CA", "Washington, DC"};
    AlertDialog alert;
    AlertDialog.Builder builder;
    DateFormat format;
    Calendar calender;

    String cityFlag;
    String dateFlag;
    String timeFlag;
    int currentTicketIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket);
        if (gCreate.getGlobalTicketList() != null)
            currentTicketList = gCreate.getGlobalTicketList();
        else
            currentTicketList = new ArrayList<>();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        printSummary = (Button) findViewById(R.id.button_printSummary);
        departureDate = (EditText) findViewById(R.id.date_departure);
        departureTime = (EditText) findViewById(R.id.editText_departureTime);
        returnDate = (EditText) findViewById(R.id.date_return);
        returnTime = (EditText) findViewById(R.id.time_return);
        sourceCity = (EditText) findViewById(R.id.editText_source);
        destinationCity = (EditText) findViewById(R.id.editText_destination);
        passangerName = (EditText) findViewById(R.id.editText_PassangerName);
        roundTrip = (RadioButton) findViewById(R.id.radioButton2_roundTrip);
        oneWay = (RadioButton) findViewById(R.id.radioButton_onWay);
        oneWay.setChecked(true);

        calender = Calendar.getInstance();
        format = DateFormat.getDateInstance();
        builder = new AlertDialog.Builder(CreateTicketActivity.this);

        cityFlag = "";
        dateFlag = "";
        timeFlag = "";

        oneWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnDate.setVisibility(View.INVISIBLE);
                oneWay.setChecked(true);
                roundTrip.setChecked(false);
                returnTime.setVisibility(View.INVISIBLE);
            }
        });

        roundTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnDate.setVisibility(View.VISIBLE);
                roundTrip.setChecked(true);
                oneWay.setChecked(false);
                returnTime.setVisibility(View.VISIBLE);
            }
        });

        //Implement Alert Dialog
        builder.setTitle("Select a city");
        builder.setItems(listCity, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (cityFlag.equals("source")) {
                    if (destinationCity.getText().toString().equals(listCity[which]))
                        Toast.makeText(CreateTicketActivity.this, "Source and destination city can not be same.", Toast.LENGTH_SHORT).show();
                    else
                        sourceCity.setText(listCity[which]);
                } else if (cityFlag.equals("destination")) {
                    if (sourceCity.getText().toString().equals(listCity[which]))
                        Toast.makeText(CreateTicketActivity.this, "Source and destination city can not be same.", Toast.LENGTH_SHORT).show();
                    else
                        destinationCity.setText(listCity[which]);
                }
            }
        });

        //Call Source choice Alert Dialog on source onclick.
        alert = builder.create();
        sourceCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityFlag = "source";
                alert.show();
            }
        });

        destinationCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityFlag = "destination";
                alert.show();
            }
        });

        //Round Trip radio button Listener
        roundTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnDate.setVisibility(View.VISIBLE);
                returnTime.setVisibility(View.VISIBLE);
            }
        });

        //departure date onclick listner
        departureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateFlag = "departure";
                showDialog(Date_id);

            }
        });

        //returnDate onclickListner
        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateFlag = "return";
                showDialog(Date_id);

            }
        });

        departureTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeFlag = "departure";
                showDialog(Time_id);
            }
        });

        returnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeFlag = "return";
                showDialog(Time_id);
            }
        });

        printSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validatedForm() == true) {
                    Ticket currentticket = new Ticket();
                    //int ticketNumber;
                    if (gCreate.getGlobalTicketList() == null)
                        currentTicketIndex = 1;
                    else
                        currentTicketIndex = gCreate.getGlobalTicketList().size() + 1;

                    if (roundTrip.isChecked()) {
                        //ticket = new Ticket(passangerName.getText().toString(),sourceCity.getText().toString(),destinationCity.getText().toString(),departureDate.getText().toString(),departureTime.getText().toString(),returnDate.getText().toString(),returnTime.getText().toString(),"Round");
                        currentticket.setTicketNumber(currentTicketIndex);
                        currentticket.setPassangerName(passangerName.getText().toString());
                        currentticket.setSourceCity(sourceCity.getText().toString());
                        currentticket.setDestinationCity(destinationCity.getText().toString());
                        currentticket.setTypeOfTrip("Round");
                        currentticket.setDepartureDate(departureDate.getText().toString());
                        currentticket.setDepartureTime(departureTime.getText().toString());
                        currentticket.setReturnDate(returnDate.getText().toString());
                        currentticket.setReturnTime(returnTime.getText().toString());
                    } else {
                        //currentticket = new Ticket(passangerName.getText().toString(),sourceCity.getText().toString(),destinationCity.getText().toString(),departureDate.getText().toString(),departureTime.getText().toString(),"","","OneWay");
                        currentticket.setTicketNumber(currentTicketIndex);
                        currentticket.setPassangerName(passangerName.getText().toString());
                        currentticket.setSourceCity(sourceCity.getText().toString());
                        currentticket.setDestinationCity(destinationCity.getText().toString());
                        currentticket.setTypeOfTrip("OneWay");
                        currentticket.setDepartureDate(departureDate.getText().toString());
                        currentticket.setDepartureTime(departureTime.getText().toString());
                    }

                    currentTicketList.add(currentticket);
                    gCreate.setGlobalTicketList(currentTicketList);
                    Log.d("Global Ticket in Create", ": " + gCreate.getGlobalTicketList() + "\n");

//                for(int i=0; i<finalTicketList.size(); i++)
//                    Log.d("Create Activit: ","Ending"+finalTicketList.iterator().next().toString());

                    Intent i = new Intent(CreateTicketActivity.this, PrintTicketActivity.class);
                    // i.putExtra("TICKET_LIST", new DataWrapper(finalTicketList));
                    i.putExtra("CURRENT_TICKET", currentticket);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "All fields are Mandatory. Please Fill all required information.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }//end onCreate


    protected Dialog onCreateDialog(int id) {

        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);
        int hour = calender.get(Calendar.HOUR_OF_DAY);
        int minute = calender.get(Calendar.MINUTE);
        int am_pm = calender.get(Calendar.AM_PM);

        switch (id) {
            case Date_id:
                return new DatePickerDialog(CreateTicketActivity.this, date_listener, year, month, day);
            case Time_id:
                return new TimePickerDialog(CreateTicketActivity.this, time_listener, hour, minute, true);
        }
        return null;
    }

    // Date picker dialog
    DatePickerDialog.OnDateSetListener date_listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {

            int setMonth = month + 1;
            String date1 = String.valueOf(setMonth) + "/" + String.valueOf(day)
                    + "/" + String.valueOf(year);
            if (dateFlag.equals("departure"))
                departureDate.setText(setMonth + "/" + day + "/" + year);
            if (dateFlag.equals("return"))
                returnDate.setText(date1);
        }
    };

    TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            // store the data in one string and set it to text
            String time1;
            if (hour > 12) {
                hour = hour - 12;
                time1 = String.valueOf(hour) + ":" + String.valueOf(minute) + " PM";
            } else
                time1 = String.valueOf(hour) + ":" + String.valueOf(minute) + " AM";
            if (timeFlag.equals("departure"))
                departureTime.setText(time1);
            if (timeFlag.equals("return"))
                returnTime.setText(time1);
        }
    };


    public  boolean validatedForm (){

        boolean flag = false;
        if(roundTrip.isChecked())
        {
         if (!passangerName.getText().toString().isEmpty() && !sourceCity.getText().toString().isEmpty() && !destinationCity.getText().toString().isEmpty() && !departureDate.getText().toString().isEmpty() && !departureTime.getText().toString().isEmpty() && !destinationCity.getText().toString().isEmpty() && !returnDate.getText().toString().isEmpty() && !returnTime.getText().toString().isEmpty())
             flag = true;
       }
        else if(oneWay.isChecked())
        {
            if (!passangerName.getText().toString().isEmpty() && !sourceCity.getText().toString().isEmpty() && !destinationCity.getText().toString().isEmpty() && !departureDate.getText().toString().isEmpty() && !departureTime.getText().toString().isEmpty() && !destinationCity.getText().toString().isEmpty())
                flag = true;
        }

        return flag;
    };


}



