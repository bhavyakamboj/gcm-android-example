package in.codingninjas.alarms;

import android.content.Intent;
import android.content.SharedPreferences;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by bhavya on 18/7/16.
 */
public class TokenRefreshService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        SharedPreferences.Editor ed = getSharedPreferences("blah",MODE_PRIVATE).edit();
        ed.remove("gcm");
        ed.commit();
        Intent i = new Intent(this,GCMRegistrationService.class);
        startActivity(i);
    }
}
