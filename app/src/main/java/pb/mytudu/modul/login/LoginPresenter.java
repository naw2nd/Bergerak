package pb.mytudu.modul.login;


import android.util.Log;

import pb.mytudu.api_response.AuthResponse;
import pb.mytudu.callback.RequestCallback;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private LoginContract.Interactor interactor;

    public LoginPresenter(LoginContract.View view, LoginContract.Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void login(String email, String password) {
        view.startLoading();

        interactor.requestLogin(email, password, new RequestCallback<AuthResponse>() {
            @Override
            public void requestSuccess(AuthResponse data) {
                interactor.saveToken("Bearer "+data.accessToken);
                interactor.saveUser(email);
                Log.d("cari cuk", "iki ya mulai");

                view.endLoading();
                view.loginSuccess();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.loginFailed(errorMessage);
            }
        });
    }

    @Override
    public void start() {
        if (interactor.getToken() != null){
            view.loginSuccess();
        }
    }
}
