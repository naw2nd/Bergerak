package pb.mytudu.modul.login;

import pb.mytudu.api_response.AuthResponse;
import pb.mytudu.base.BasePresenter;
import pb.mytudu.base.BaseView;
import pb.mytudu.callback.RequestCallback;

public interface LoginContract {
    interface Presenter extends BasePresenter {
        void login(String email, String password);
    }

    interface View extends BaseView<LoginPresenter> {
        void startLoading();
        void endLoading();
        void loginSuccess();
        void loginFailed(String message);
        void redirectToRegister();
    }

    interface Interactor {
        void requestLogin(String email, String password, RequestCallback<AuthResponse> responseCallback);
        void saveToken(String token);
        String getToken();
        void saveUser(String user);
    }
}

