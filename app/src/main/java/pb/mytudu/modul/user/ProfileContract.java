package pb.mytudu.modul.user;

import pb.mytudu.api_response.AuthResponse;
import pb.mytudu.base.BasePresenter;
import pb.mytudu.base.BaseView;
import pb.mytudu.callback.RequestCallback;

public interface ProfileContract {
    interface Presenter extends BasePresenter {
        void logout();
    }

    interface View extends BaseView<ProfilePresenter> {
        void startLoading();
        void endLoading();
        void redirectToLogin();
    }
}

