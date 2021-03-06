package in.codingninjas.alarms;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class GCMRegistrationService extends IntentService {

    public GCMRegistrationService() {
        super("GCMRegistrationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String token = getSharedPreferences("blah", MODE_PRIVATE).getString("gcm", null);
        if (token != null)
            return;
        InstanceID id = InstanceID.getInstance(this);
        try {
            token = id.getToken("<Your project number>", GoogleCloudMessaging.INSTANCE_ID_SCOPE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;

        if (token != null) {
            SharedPreferences.Editor sp = getSharedPreferences("blah", MODE_PRIVATE).edit();
            sp.putString("gcm", token);
            sp.commit();
            sendTokenToServer(token);
            subsribeTopics(token);
        }
    }

    private void subsribeTopics(String token)  {
        GcmPubSub pubsub = GcmPubSub.getInstance(this);
        try {
            pubsub.subscribe(token, "/topics/global", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendTokenToServer(String token) {

    }
}
