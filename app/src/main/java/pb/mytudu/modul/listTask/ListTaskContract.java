package pb.mytudu.modul.listTask;


import java.util.List;

import pb.mytudu.base.BasePresenter;
import pb.mytudu.base.BaseView;
import pb.mytudu.callback.RequestCallback;
import pb.mytudu.model.Task;

public interface ListTaskContract {
    interface View extends BaseView<Presenter> {
        void startLoading();
        void endLoading();
        void showListTask(List<Task> tasks);
        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter {
        void requestListTask();
    }
    interface Interactor{
        void requestListTask(RequestCallback<List<Task>> requestCallback);
    }
}
