package com.rajaraman.localnotificationsdemo;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	Button btn = (Button) findViewById(R.id.btnNotify);

	btn.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		postNotification();
	    }
	});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

    public void postNotification() {

	// Creates an explicit intent for an Activity
	Intent resultIntent = new Intent(this, ResultActivity.class);

	// The stack builder object will contain an artificial back stack for
	// the started Activity. This ensures that navigating backward from the
	// Activity leads out of your application to the Home screen.
	TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

	// Adds the back stack for the Intent (but not the Intent itself)
	stackBuilder.addParentStack(ResultActivity.class);

	// Adds the Intent that starts the Activity to the top of the stack
	stackBuilder.addNextIntent(resultIntent);

	PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
		PendingIntent.FLAG_UPDATE_CURRENT);

	// Build the notification - Builder pattern :)
	NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.ic_launcher).setContentTitle("Sample Notification")
		.setContentText("This is a sample notification");

	// Big view style notification
	NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

	inboxStyle.setBigContentTitle("Notification Details");
	inboxStyle.addLine("First Line");
	inboxStyle.addLine("Second Line");
	inboxStyle.addLine("Third Line");

	// Set the inbox style
	mBuilder.setStyle(inboxStyle);

	// Set the content's intent
	mBuilder.setContentIntent(resultPendingIntent);

	// Add an action to the "Open Me" action
	mBuilder.addAction(R.drawable.ic_launcher, "Open Me", resultPendingIntent);

	NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

	mNotificationManager.notify(1, mBuilder.build());
    }
}
