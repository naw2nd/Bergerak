package pb.mytudu.modul.register;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import pb.mytudu.api_response.AuthResponse;
import pb.mytudu.callback.RequestCallback;
import pb.mytudu.model.User;
import pb.mytudu.utils.Constant;
import pb.mytudu.utils.SharedPreferenceUtil;

public class RegisterInteractor implements RegisterContract.Interactor {
    private SharedPreferenceUtil sharedPreferenceUtil;
    public RegisterInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
    }

    @Override
    public void requestRegister(String username, String email, String password, RequestCallback<AuthResponse> responseCallback) {
        JSONObject user = new JSONObject();
        try{
            user.put("username", username);
            user.put("email", email);
            user.put("password", password);
        }catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(Constant.REGISTER)
                .addJSONObjectBody(user)
                .build()
                .getAsObject(AuthResponse.class, new ParsedRequestListener<AuthResponse>() {
                    @Override
                    public void onResponse(AuthResponse response) {
                        if(response == null){
                            responseCallback.requestFailed("null response");
                        }else if(response.accessToken!=null){
                            responseCallback.requestSuccess(response);
                        }else {
                            responseCallback.requestFailed("Invalid Credential");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if(anError.getErrorCode() == 406){
                            responseCallback.requestFailed("Invalid Credential");
                        }
                        responseCallback.requestFailed("Server Offline "+anError.toString());
                    }
                });
    }

    @Override
    public void saveToken(String token) {
        sharedPreferenceUtil.setToken(token);
    }

    @Override
    public String getToken() {
        return sharedPreferenceUtil.getToken();
    }

    @Override
    public void saveUser(String email) {
        AndroidNetworking.get(Constant.BASE_URL + "users?email="+email)
                .build()
                .getAsObjectList(User.class, new ParsedRequestListener<List<User>>() {
                    @Override
                    public void onResponse(List<User> response) {
                        if(response != null){
                            Log.d("cari", response.get(0).getEmail());
                            sharedPreferenceUtil.setUser(new Gson().toJson(response.get(0)));
                        }
                        else {
                            Log.d("cari", "null response");
                            //requestCallback.requestFailed("Null Response");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("cari", anError.toString());
                        //requestCallback.requestFailed(anError.getMessage());
                    }
                });
         }
}
