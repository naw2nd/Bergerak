package pb.mytudu.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UtilProvider {
    private static SharedPreferenceUtil sharedPreferenceUtil;

    public static void initialize(Context context){
        initSharedPreferenceUtil(context);
    }

    public static void initSharedPreferenceUtil(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
        sharedPreferenceUtil = new SharedPreferenceUtil(sharedPreferences);
    }

    public static SharedPreferenceUtil getSharedPreferenceUtil() {
        return sharedPreferenceUtil;
    }
}
