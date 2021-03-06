package in.codingninjas.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
	import android.widget.Button;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Button ab;
    Button cab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ab = (Button) findViewById(R.id.alarmButton);
        Intent i = new Intent(this, GCMRegistrationService.class);
        startService(i);
        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
               //
//                GregorianCalendar c = new GregorianCalendar();
//                c.setTimeZone(TimeZone.getTimeZone("IST"));
//                c.set(2016, 8, 10, 10, 15);
//
                Intent i = new Intent(MainActivity.this, AlarmActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, i, 0);
                am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, pi);

                Intent i2 = new Intent(MainActivity.this, AlarmReceiver.class);
                PendingIntent pi2 = PendingIntent.getBroadcast(MainActivity.this, 0, i2, 0);
                am.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 15000, pi2);
            }
        });

        cab = (Button) findViewById(R.id.cancelAlarmButton);
        cab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, MyService.class);
                startService(i);

                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                //am.cancel();
            }
        });
    }



}
