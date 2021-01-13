package pb.mytudu.modul.register;


import pb.mytudu.api_response.AuthResponse;
import pb.mytudu.callback.RequestCallback;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    private RegisterContract.Interactor interactor;

    public RegisterPresenter(RegisterContract.View view, RegisterContract.Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void register(String username, String email, String password) {
        view.startLoading();

        interactor.requestRegister(username, email, password, new RequestCallback<AuthResponse>() {
            @Override
            public void requestSuccess(AuthResponse data) {
                interactor.saveToken("Bearer "+data.accessToken);
                interactor.saveUser(email);

                view.endLoading();
                view.registerSuccess();
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.registerFailed(errorMessage);
            }
        });
    }

    @Override
    public void start() {
        if (interactor.getToken() != null){
            view.registerSuccess();
        }
    }
}
