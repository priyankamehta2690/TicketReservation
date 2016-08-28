package com.example.ticketreservation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ListIterator;

@SuppressWarnings("ALL")
public class EditTicketActivity extends AppCompatActivity {

    Globals gEdit = Globals.getInstance();
    private static final int Date_id = 0;
    private static final int Time_id = 1;

    EditText passangerName, sourceCity, destinationCity, departureDate, departureTime, returnDate, returnTime;
    Button save, selectCity, selectTicket;
    RadioButton oneWay, roundTrip;
    RadioGroup tripType;

    ArrayList<Ticket> editTicketList = new ArrayList<Ticket>();
    String listTickets[];
    String listCity[] = {"Albany,NY", "Atlanta, GA", "Boston, MA", "Charlotte, NC", "Chicago, IL", "Greenville, SC", "Houston, TX", "Las Vegas, NV", "Los Angeles, CA", "Miami, FL", "Myrtle Beach, SC", "New York, NY", "Portland, OR", "Raleigh, NC", "San Jose, CA", "Washington, DC"};
    Ticket currentTicket;

    AlertDialog alert;
    AlertDialog cityAlert;
    AlertDialog.Builder builder, cityBuilder;
    DateFormat format;
    Calendar calender;
    String dateFlag;
    String  timeFlag;
    String cityFlag;
    int selectTicketIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ticket);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        editTicketList = gEdit.getGlobalTicketList();

        departureDate = (EditText) findViewById(R.id.date_departure);
        departureTime = (EditText) findViewById(R.id.editText_departureTime);
        returnDate = (EditText) findViewById(R.id.date_return);
        returnTime = (EditText) findViewById(R.id.time_return);
        sourceCity = (EditText) findViewById(R.id.editText_source);
        destinationCity = (EditText) findViewById(R.id.editText_destination);
        passangerName = (EditText) findViewById(R.id.editText_PassangerName);
        roundTrip = (RadioButton) findViewById(R.id.radioButton2_roundTrip);

        roundTrip.setChecked(true);

        oneWay = (RadioButton) findViewById(R.id.radioButton_onWay);
        save = (Button) findViewById(R.id.button_saveEdit);
        selectTicket = (Button) findViewById(R.id.btn_selectTicket);
        tripType = (RadioGroup)findViewById(R.id.radiaGrp_tripOption);

        calender = Calendar.getInstance();
        format = DateFormat.getDateInstance();
        builder = new AlertDialog.Builder(EditTicketActivity.this);
        cityBuilder = new AlertDialog.Builder(EditTicketActivity.this);
        currentTicket = new Ticket();

        cityFlag = "";
        dateFlag= "";
        timeFlag="";

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
        if (editTicketList.isEmpty() != true) {
            listTickets = new String[editTicketList.size()];
            for (int i = 0; i < editTicketList.size(); i++) {
                listTickets[i] = editTicketList.get(i).getPassangerName();
            }
        }


        selectTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Select a Ticket");
                builder.setItems(listTickets, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        for (int i = 0; i < editTicketList.size(); i++)
                        {
                            if (editTicketList.get(i).getPassangerName().equals(listTickets[which]))
                            {
                                selectTicketIndex =  editTicketList.get(i).getTicketNumber();
                                if(editTicketList.get(i).getTypeOfTrip().equals("Round"))
                                {
                                    passangerName.setText(editTicketList.get(i).getPassangerName());
                                    sourceCity.setText(editTicketList.get(i).getSourceCity());
                                    destinationCity.setText(editTicketList.get(i).getDestinationCity());
                                    roundTrip.setChecked(true);
                                    oneWay.setChecked(false);
                                    returnDate.setVisibility(View.VISIBLE);
                                    returnTime.setVisibility(View.VISIBLE);
                                    departureDate.setText(editTicketList.get(i).getDepartureDate());
                                    departureTime.setText(editTicketList.get(i).getDepartureTime());
                                    returnDate.setText(editTicketList.get(i).getReturnDate());
                                    returnTime.setText(editTicketList.get(i).getReturnTime());
                                }
                                else if (editTicketList.get(i).getTypeOfTrip().equals("OneWay"))
                                {
                                    passangerName.setText(editTicketList.get(i).getPassangerName());
                                    sourceCity.setText(editTicketList.get(i).getSourceCity());
                                    destinationCity.setText(editTicketList.get(i).getDestinationCity());
                                    roundTrip.setChecked(false);
                                    oneWay.setChecked(true);
                                    returnDate.setVisibility(View.INVISIBLE);
                                    returnTime.setVisibility(View.INVISIBLE);
                                    departureDate.setText(editTicketList.get(i).getDepartureDate());
                                    departureTime.setText(editTicketList.get(i).getDepartureTime());
                                }

                            }
                        }







                        //Find the selected Ticket
//                        Ticket tempTicket = new Ticket();
//                        for (int i = 0; i < editTicketList.size(); i++)
//                        {
//                            if(editTicketList.get(i).getPassangerName().equals(listTickets[which]))
//                                selectTicketIndex = editTicketList.get(i).getTicketNumber();
//                                tempTicket = editTicketList.get(i);
//                        }
//
//                       if(tempTicket.getTypeOfTrip().equals("Round"))
//                       {
//                           passangerName.setText(tempTicket.getPassangerName());
//                           sourceCity.setText(tempTicket.getSourceCity());
//                           destinationCity.setText(tempTicket.getDestinationCity());
//                           //String tripOption = ((RadioButton)findViewById(tripType.getCheckedRadioButtonId())).getText().toString();
//                           roundTrip.setChecked(true);
//                           oneWay.setChecked(false);
//                           departureDate.setText(tempTicket.getDepartureDate());
//                           departureTime.setText(tempTicket.getDepartureTime());
//                           returnDate.setText(tempTicket.getReturnDate());
//                           returnDate.setVisibility(View.VISIBLE);
//                           returnTime.setText(tempTicket.getReturnTime());
//                           returnTime.setVisibility(View.VISIBLE);
//                       }
//                        else if(tempTicket.getTypeOfTrip().equals("OneWay"))
//                       {
//                           passangerName.setText(tempTicket.getPassangerName());
//                           sourceCity.setText(tempTicket.getSourceCity());
//                           destinationCity.setText(tempTicket.getDestinationCity());
//                           roundTrip.setChecked(false);
//                           oneWay.setChecked(true);
//                           departureDate.setText(tempTicket.getDepartureDate());
//                           departureTime.setText(tempTicket.getDepartureTime());
//                           returnTime.setVisibility(View.INVISIBLE);
//                           returnDate.setVisibility(View.INVISIBLE);
//                       }
                        ///Brackets end
                    }
                });
                //Call Source choice Alert Dialog on source onclick.
                alert=builder.create();
                alert.show();
            }
        });

        cityBuilder.setTitle("Source");
        cityBuilder.setItems(listCity, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (cityFlag.equals("source")) {
                    if (destinationCity.getText().toString().equals(listCity[which]))
                        Toast.makeText(EditTicketActivity.this, "Source and destination city can not be same.", Toast.LENGTH_SHORT).show();
                    else
                        sourceCity.setText(listCity[which]);
                } else if (cityFlag.equals("destination")) {
                    if (sourceCity.getText().toString().equals(listCity[which]))
                        Toast.makeText(EditTicketActivity.this, "Source and destination city can not be same.", Toast.LENGTH_SHORT).show();
                    else
                        destinationCity.setText(listCity[which]);
                }


            }
        });
        cityAlert = cityBuilder.create();

//Date Picker
        sourceCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityFlag = "source";
                cityAlert.show();
            }
        });

        destinationCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityFlag = "destination";
                cityAlert.show();
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

        //Save implementation
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for(int i=0; i<editTicketList.size(); i++)
                {
                    if(editTicketList.get(i).getTicketNumber()== selectTicketIndex)
                    {
                        currentTicket = editTicketList.get(i);
                        if(oneWay.isChecked())
                        {
                            editTicketList.get(i).setPassangerName(passangerName.getText().toString());
                            editTicketList.get(i).setSourceCity(sourceCity.getText().toString());
                            editTicketList.get(i).setDestinationCity(destinationCity.getText().toString());
                            editTicketList.get(i).setTypeOfTrip("OneWay");
                            editTicketList.get(i).setDepartureDate(departureDate.getText().toString());
                            editTicketList.get(i).setDepartureTime(departureTime.getText().toString());
                        }
                        else if(roundTrip.isChecked())
                        {
                            editTicketList.get(i).setPassangerName(passangerName.getText().toString());
                            editTicketList.get(i).setSourceCity(sourceCity.getText().toString());
                            editTicketList.get(i).setDestinationCity(destinationCity.getText().toString());
                            editTicketList.get(i).setTypeOfTrip("Round");
                            editTicketList.get(i).setDepartureDate(departureDate.getText().toString());
                            editTicketList.get(i).setDepartureTime(departureTime.getText().toString());
                            editTicketList.get(i).setReturnDate(returnDate.getText().toString());
                            editTicketList.get(i).setReturnTime(returnTime.getText().toString());
                        }

                    }
                }

               // editTicketList.remove(selectTicketIndex);
                //editTicketList.add(selectTicketIndex,editedTicket);
                gEdit.setGlobalTicketList(editTicketList);
//                for (int i = 0; i < editTicketList.size(); i++)
//                {
//                    if (editTicketList.get(i).getPassangerName().equals(passangerName.getText().toString()))
//                        // finalTicketList.remove(i);
//
//                        editTicketList.add(editedTicket);
//                }


                Intent i = new Intent(EditTicketActivity.this,PrintTicketActivity.class);
                i.putExtra("CURRENT_TICKET", currentTicket);
                startActivity(i);
                finish();

            }
        });



    }//end of Oncreate


    protected Dialog onCreateDialog(int id) {

        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);
        int hour = calender.get(Calendar.HOUR_OF_DAY);
        int minute = calender.get(Calendar.MINUTE);
        int am_pm = calender.get(Calendar.AM_PM);

        switch (id) {
            case Date_id:
                return new DatePickerDialog(EditTicketActivity.this,date_listener,year,month,day);
            case Time_id:
                return new TimePickerDialog(EditTicketActivity.this, time_listener, hour,minute,true);
        }
        return null;
    }

    // Date picker dialog
    DatePickerDialog.OnDateSetListener date_listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {

            int setMonth = month+1;
            String date1 = String.valueOf(setMonth) + "/" + String.valueOf(day)
                    + "/" + String.valueOf(year);
            if(dateFlag.equals("departure"))
                departureDate.setText(setMonth+"/"+day+"/"+year);
            if(dateFlag.equals("return"))
                returnDate.setText(date1);
        }
    };

    TimePickerDialog.OnTimeSetListener time_listener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            // store the data in one string and set it to text
            String time1;
            if(hour>12)
            {
                hour = hour-12;
                time1 = String.valueOf(hour) + ":" + String.valueOf(minute)+" PM";
            }
            else
                time1 = String.valueOf(hour) + ":" + String.valueOf(minute)+" AM";
            if(timeFlag.equals("departure"))
                departureTime.setText(time1);
            if(timeFlag.equals("return"))
                returnTime.setText(time1);
        }
    };

}