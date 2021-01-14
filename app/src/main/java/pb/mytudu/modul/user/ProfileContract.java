package pb.mytudu.modul.user;

import pb.mytudu.api_response.AuthResponse;
import pb.mytudu.base.BasePresenter;
import pb.mytudu.base.BaseView;
import pb.mytudu.callback.RequestCallback;
import pb.mytudu.model.User;

public interface ProfileContract {
    interface Presenter extends BasePresenter {
        void logout();
        void getProfile();
    }

    interface View extends BaseView<ProfilePresenter> {
        void startLoading();
        void endLoading();
        void redirectToLogin();
        void showProfile(User user);
    }
}

