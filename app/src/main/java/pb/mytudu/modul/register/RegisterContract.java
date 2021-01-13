package pb.mytudu.modul.register;

import pb.mytudu.api_response.AuthResponse;
import pb.mytudu.base.BasePresenter;
import pb.mytudu.base.BaseView;
import pb.mytudu.callback.RequestCallback;

public interface RegisterContract {
    interface Presenter extends BasePresenter {
        void register(String username, String email, String password);
    }

    interface View extends BaseView<RegisterPresenter> {
        void startLoading();
        void endLoading();
        void registerSuccess();
        void registerFailed(String message);
    }

    interface Interactor {
        void requestRegister(String username, String email, String password, RequestCallback<AuthResponse> responseCallback);
        void saveToken(String token);
        String getToken();
        void saveUser(String user);
    }
}

