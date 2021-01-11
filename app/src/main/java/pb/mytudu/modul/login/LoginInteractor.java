package pb.mytudu.modul.login;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import pb.mytudu.api_response.LoginResponse;
import pb.mytudu.callback.RequestCallback;
import pb.mytudu.model.User;
import pb.mytudu.utils.Constant;
import pb.mytudu.utils.SharedPreferenceUtil;

public class LoginInteractor implements LoginContract.Interactor {
    private SharedPreferenceUtil sharedPreferenceUtil;
    public LoginInteractor(SharedPreferenceUtil sharedPreferenceUtil){
        this.sharedPreferenceUtil = sharedPreferenceUtil;
    }

    @Override
    public void requestLogin(String email, String password, RequestCallback<LoginResponse> responseCallback) {
        JSONObject user = new JSONObject();
        try{
            user.put("email", email);
            user.put("password", password);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post(Constant.BASE_URL+"login")
                .addJSONObjectBody(user)
                .build()
                .getAsObject(LoginResponse.class, new ParsedRequestListener<LoginResponse>() {
                    @Override
                    public void onResponse(LoginResponse response) {
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
                        responseCallback.requestFailed("Server Offline");
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
