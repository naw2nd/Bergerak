package pb.mytudu.modul.listTask;

import java.util.ArrayList;
import java.util.List;

import pb.mytudu.callback.RequestCallback;
import pb.mytudu.model.Task;

public class ListTaskPresenter implements ListTaskContract.Presenter{
    private final ListTaskContract.View view;
    private final ListTaskContract.Interactor interactor;

    public ListTaskPresenter(ListTaskContract.View view, ListTaskContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void requestListTask() {
        view.startLoading();
        interactor.requestListTask(new RequestCallback<List<Task>>() {
            @Override
            public void requestSuccess(List<Task> data) {
                view.endLoading();
                view.showListTask(data);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void start() {

    }
}
