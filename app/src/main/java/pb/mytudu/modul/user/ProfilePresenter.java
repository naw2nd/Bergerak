package pb.mytudu.modul.user;


import android.util.Log;

import com.google.gson.Gson;

import pb.mytudu.api_response.AuthResponse;
import pb.mytudu.callback.RequestCallback;
import pb.mytudu.model.User;
import pb.mytudu.utils.SharedPreferenceUtil;

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View view;
    private SharedPreferenceUtil sharedPreferenceUtil;
    public ProfilePresenter(ProfileContract.View view, SharedPreferenceUtil sharedPreferenceUtil){
        this.view = view;
        this.sharedPreferenceUtil = sharedPreferenceUtil;
    }
    @Override
    public void start() {
        if (sharedPreferenceUtil.getToken() == null){
            view.redirectToLogin();
        }
    }

    @Override
    public void logout() {
        sharedPreferenceUtil.clear();
        view.redirectToLogin();
    }

    @Override
    public void getProfile() {
        User user = new Gson().fromJson(sharedPreferenceUtil.getUser(), User.class);
        view.showProfile(user);
    }
}
