package com.example.downloadfile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DownloadManager dm;
DownloadManager.Request request;
Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button)findViewById(R.id.button1);
        dm=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        //Broadcast message for user
        BroadcastReceiver myreceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(getApplicationContext(),"Download Completed ",Toast.LENGTH_SHORT).show();
            }
        };
        registerReceiver(myreceiver,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        //onclick event
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //request from url
                //request=new DownloadManager.Request(Uri.parse("https://icons.iconarchive.com/icons/hopstarter/sleek-xp-basic/256/Download-icon.png"));
                //request=new DownloadManager.Request(Uri.parse("https://images.unsplash.com/photo-1580600301354-0ce8faef576c?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&dl=zane-lee-F3MmVPH9iLY-unsplash.jpg"));
                request=new DownloadManager.Request(Uri.parse("https://www.gtu.ac.in/syllabus/MCA/Effective%20from%202018/4649303.pdf"));
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                //setting download title and desc.
                request.setTitle("My Data Download");
                request.setDescription("Download Started.");
                //Notify User for Successful Download
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
                //specify your location of download
                request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_PICTURES,"myimage.png");
                dm.enqueue(request);


            }
        });
    }
}