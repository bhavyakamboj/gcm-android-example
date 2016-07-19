package in.codingninjas.alarms;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

public class GCMMessageReceiverService extends GcmListenerService {
    public GCMMessageReceiverService() {
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.i("message",message);
        super.onMessageReceived(from, data);
    }
}
