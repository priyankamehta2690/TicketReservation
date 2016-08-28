package com.example.ticketreservation;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

public class DeleteTicketActivity extends AppCompatActivity {

    //Controls
    Globals gDelete = Globals.getInstance();
    EditText passangerName, sourceCity, destinationCity, departureDate, departureTime, returnDate, returnTime;
    RadioButton oneWay, roundTrip;
    Button selectTicket, deleteTicket, cancelDelete;
    AlertDialog alert;
    String listTickets[];

    int selectedTicketNumber;

    ArrayList<Ticket> deleteTicketList= new ArrayList<Ticket>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_ticket);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        //initialize
        deleteTicketList = gDelete.getGlobalTicketList();

        selectTicket = (Button)findViewById(R.id.btn_selectTicket);
        deleteTicket = (Button)findViewById(R.id.button_deletTicket);
        cancelDelete = (Button)findViewById(R.id.button_calcelDelete);
        departureDate = (EditText) findViewById(R.id.date_departure);
        departureTime = (EditText) findViewById(R.id.editText_departureTime);
        returnDate = (EditText) findViewById(R.id.date_return);
        returnTime = (EditText)findViewById(R.id.time_return);
        sourceCity = (EditText) findViewById(R.id.editText_source);
        destinationCity = (EditText) findViewById(R.id.editText_destination);
        passangerName = (EditText) findViewById(R.id.editText_PassangerName);
        roundTrip =(RadioButton)findViewById(R.id.radioButton2_roundTrip);
        oneWay = (RadioButton) findViewById(R.id.radioButton_onWay);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

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


         //Implement Alert Dialog
        if (deleteTicketList.isEmpty() != true) {
            listTickets = new String[deleteTicketList.size()];
            for (int i = 0; i < deleteTicketList.size(); i++) {
                listTickets[i] = deleteTicketList.get(i).getPassangerName();
            }
        }
        selectTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Select a Ticket");
                builder.setItems(listTickets, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                      //  Ticket tempTicket = new Ticket();
                        for (int i = 0; i < deleteTicketList.size(); i++)
                        {
                            if (deleteTicketList.get(i).getPassangerName().equals(listTickets[which]))
                            {
                                selectedTicketNumber =  deleteTicketList.get(i).getTicketNumber();
                                if(deleteTicketList.get(i).getTypeOfTrip().equals("Round"))
                                {
                                    passangerName.setText(deleteTicketList.get(i).getPassangerName());
                                    sourceCity.setText(deleteTicketList.get(i).getSourceCity());
                                    destinationCity.setText(deleteTicketList.get(i).getDestinationCity());
                                    roundTrip.setChecked(true);
                                    oneWay.setChecked(false);
                                    returnDate.setVisibility(View.VISIBLE);
                                    returnTime.setVisibility(View.VISIBLE);
                                    departureDate.setText(deleteTicketList.get(i).getDepartureDate());
                                    departureTime.setText(deleteTicketList.get(i).getDepartureTime());
                                    returnDate.setText(deleteTicketList.get(i).getReturnDate());
                                    returnTime.setText(deleteTicketList.get(i).getReturnTime());
                                }
                                else if (deleteTicketList.get(i).getTypeOfTrip().equals("OneWay"))
                                {
                                    passangerName.setText(deleteTicketList.get(i).getPassangerName());
                                    sourceCity.setText(deleteTicketList.get(i).getSourceCity());
                                    destinationCity.setText(deleteTicketList.get(i).getDestinationCity());
                                    roundTrip.setChecked(false);
                                    oneWay.setChecked(true);
                                    returnDate.setVisibility(View.INVISIBLE);
                                    returnTime.setVisibility(View.INVISIBLE);
                                    departureDate.setText(deleteTicketList.get(i).getDepartureDate());
                                    departureTime.setText(deleteTicketList.get(i).getDepartureTime());
                                }

                            }
                        }//end for
                    }
            });
                //Call Source choice Alert Dialog on source onclick.
            alert=builder.create();
                alert.show();
            }
        });
//Implement Delete
        deleteTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0; i<deleteTicketList.size(); i++)
                {
                    if(deleteTicketList.get(i).getTicketNumber()== selectedTicketNumber)
                    {
                        deleteTicketList.remove(i);
                    }
                }

                gDelete.setGlobalTicketList(deleteTicketList);

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();


            }
        });


    cancelDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();

        }
    });


    }//end OnCreate

}

