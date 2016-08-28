package com.example.ticketreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PrintTicketActivity extends AppCompatActivity {

    // public static final String PASSTICKET = "TICKET";

    TextView pName, pSource, pDestination, pDeparture, pReturn, returnDetailsLabel;
    Button finish;

    Ticket currentTicket;
    ArrayList<Ticket> currentTicketList;// = new ArrayList<Ticket>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_ticket);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);


        pName = (TextView) findViewById(R.id.tV_passangerName);
        pSource = (TextView) findViewById(R.id.tV_passangerSource);
        pDestination = (TextView) findViewById(R.id.tV_passangerDesination);
        pDeparture = (TextView) findViewById(R.id.tV_passangerDeparture);
        pReturn = (TextView) findViewById(R.id.tV_passangerReturn);
        returnDetailsLabel = (TextView) findViewById(R.id.tV_return);
        finish = (Button) findViewById(R.id.btn_finish);
        currentTicket = new Ticket();
        currentTicketList= new ArrayList<>();

        //get Intent
        if (getIntent().getExtras() != null) {
            Intent i = getIntent();
            // DataWrapper data = (DataWrapper)getIntent().getSerializableExtra("TICKET_LIST");
            //finalTicketList = data.getTickets();
            currentTicket = (Ticket) i.getSerializableExtra("CURRENT_TICKET");

//            for(int x =0; x<finalTicketList.size(); x++)
//                Log.d("Create Print: ", "Starting" + finalTicketList.iterator().next().toString());
//        }


            if (currentTicket.typeOfTrip.equals("Round")) {
                returnDetailsLabel.setVisibility(View.VISIBLE);
                pName.setText(currentTicket.getPassangerName());
                pSource.setText(currentTicket.getSourceCity());
                pDestination.setText(currentTicket.getDestinationCity());
                pDeparture.setText(currentTicket.getDepartureDate() + ", " + currentTicket.getDepartureTime());
                pReturn.setText(currentTicket.getReturnDate() + ", " + currentTicket.getReturnTime());
            } else {
                pName.setText(currentTicket.getPassangerName());
                pSource.setText(currentTicket.getSourceCity());
                pDestination.setText(currentTicket.getDestinationCity());
                pDeparture.setText(currentTicket.getDepartureDate() + ", " + currentTicket.getDepartureTime());
                returnDetailsLabel.setVisibility(View.INVISIBLE);

            }

            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Ticket currentticket = new Ticket();
                    ArrayList<String> dpdetails = new ArrayList();
                    ArrayList<String> rdetails = new ArrayList();
                    String departure = pDeparture.getText().toString();
                    String returnDetails = pReturn.getText().toString();
                    StringTokenizer dd = new StringTokenizer(departure, ",");
                    StringTokenizer rd = new StringTokenizer(returnDetails, ",");
                    while (dd.hasMoreTokens()) {
                        dpdetails.add(dd.nextToken());
                    }
                    while (rd.hasMoreTokens()) {
                        rdetails.add(rd.nextToken());
                    }
                    if (currentTicket.getTypeOfTrip().equals("Round")) {
                        currentticket.setPassangerName(pName.getText().toString());
                        currentticket.setSourceCity(pSource.getText().toString());
                        currentticket.setDestinationCity(pDestination.getText().toString());
                        currentticket.setTypeOfTrip("Round");
                        currentticket.setDepartureDate(dpdetails.get(0));
                        currentticket.setDepartureTime(dpdetails.get(1));
                        currentticket.setReturnDate(rdetails.get(0));
                        currentticket.setReturnTime(rdetails.get(1));
                    } else {
                        currentticket.setPassangerName(pName.getText().toString());
                        currentticket.setSourceCity(pSource.getText().toString());
                        currentticket.setDestinationCity(pDestination.getText().toString());
                        currentticket.setTypeOfTrip("OneWay");
                        currentticket.setDepartureDate(dpdetails.get(0));
                        currentticket.setDepartureTime(dpdetails.get(1));
                    }
//                    Log.d("Demo: ", "currentticket: " + currentticket.toString());
//                    finalTicketList.add(finalTicketList.size(), currentticket);
//
//                    for (int x = 0; x < finalTicketList.size(); x++)
//                        Log.d("Create Print: ", "Ending: " + "id " + x + "/  " + finalTicketList.iterator().next().toString());
                   // currentTicketList.add(currentticket);
                    Intent i = new Intent(PrintTicketActivity.this, MainActivity.class);
                   // i.putExtra("TICKET_LIST", new DataWrapper(currentTicketList));
                   // i.putExtra("CURRENT_TICKET", currentTicketList);
                    startActivity(i);
                    finish();
                }
            });


        }


    }//end of onCreate
}
