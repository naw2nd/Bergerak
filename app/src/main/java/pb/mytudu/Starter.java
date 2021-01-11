package pb.mytudu;

import android.app.Application;
import com.androidnetworking.AndroidNetworking;
import pb.mytudu.utils.UtilProvider;

public class Starter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        UtilProvider.initialize(getApplicationContext());
    }
}
