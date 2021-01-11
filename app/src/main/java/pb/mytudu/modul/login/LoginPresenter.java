package pb.mytudu.modul.login;


import pb.mytudu.api_response.LoginResponse;
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

        interactor.requestLogin(email, password, new RequestCallback<LoginResponse>() {
            @Override
            public void requestSuccess(LoginResponse data) {
                interactor.saveToken("Bearer "+data.accessToken);
                interactor.saveUser(email);

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
