package com.example.ticketreservation;

import android.Manifest;
import android.app.ActionBar;
import android.bluetooth.le.AdvertiseData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    Globals gMain = Globals.getInstance();

    Button createTicket, editTicket, deleteTicket, viewTicket, finishTicket;
    TextView customerCare;
    ArrayList<Ticket> finalTicketList = new ArrayList<Ticket>();

    Ticket currentTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //g.setGlobalTicketList();
        setContentView(R.layout.activity_main);
//finalTicketList = gMain.getGlobalTicketList;
        //set actionbar icon
        // android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);


        //button initialization
        createTicket = (Button) findViewById(R.id.button_create);
        editTicket = (Button) findViewById(R.id.button_edit);
        deleteTicket = (Button) findViewById(R.id.button_delete);
        viewTicket = (Button) findViewById(R.id.button_view);
        finishTicket = (Button) findViewById(R.id.button_finish);
        customerCare = (TextView) findViewById(R.id.textView_customerCare);
        currentTicket = new Ticket();
        //Get Intent
        if (getIntent().getExtras() != null) {
//            Intent i = getIntent();
//            currentTicket = (Ticket) i.getSerializableExtra("CURRENT_TICKET");
//            finalTicketList.add(finalTicketList.size(),currentTicket);

           // Intent i = getIntent();
            //DataWrapper data = (DataWrapper)getIntent().getSerializableExtra("TICKET_LIST");
            //finalTicketList.addAll(finalTicketList.size(),data.getTickets());
 //          currentTicket = (Ticket) i.getSerializableExtra("CURRENT_TICKET");
//            finalTicketList = data.getTickets();
//            for (int i = 0; i < finalTicketList.size(); i++)
//                Log.d("Main Activity: ", "Starting" + finalTicketList.iterator().next().toString());
        }



        createTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, CreateTicketActivity.class);
                 startActivity(i);
                finish();
                  //    currentTicketList = gCreate.getGlobalTicketList();

               // i.putExtra("TICKET_LIST", new DataWrapper(finalTicketList));

                //finish();
            }
        });

        editTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(gMain.getGlobalTicketList()!= null)
                {
                    Intent i = new Intent(MainActivity.this, EditTicketActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"There are no Booked Tickets.",Toast.LENGTH_LONG).show();
            }
        });

        deleteTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(gMain.getGlobalTicketList()!= null)
                {
                    Intent i = new Intent(MainActivity.this, DeleteTicketActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"There are no Booked Tickets.",Toast.LENGTH_LONG).show();
            }
        });

        viewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(gMain.getGlobalTicketList()!= null)
                {
                    Intent i = new Intent(MainActivity.this, ViewTicketActivity.class);
                    i.putExtra("TICKET_LIST", new DataWrapper(gMain.getGlobalTicketList()));
                    startActivity(i);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"There are no Booked Tickets.",Toast.LENGTH_LONG).show();
            }

        });

       finishTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        customerCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:999-999-9999"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });
    }

}



