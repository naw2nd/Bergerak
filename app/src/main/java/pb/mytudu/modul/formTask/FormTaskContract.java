package pb.mytudu.modul.formTask;

import java.util.List;

import pb.mytudu.api_response.ResponseMessage;
import pb.mytudu.base.BasePresenter;
import pb.mytudu.base.BaseView;
import pb.mytudu.callback.RequestCallback;
import pb.mytudu.model.Task;
import pb.mytudu.modul.listTask.ListTaskContract;

public interface FormTaskContract {
    interface View extends BaseView<FormTaskContract.Presenter> {
        void startLoading();
        void endLoading();
        void showTask(Task tasks);
        void showError(String errorMessage);
        void successDelete();
    }

    interface Presenter extends BasePresenter {
        void requestTask(int id);
        void saveTask(int id, Task task);
        void deleteTask(int id);
        void addTask(Task task);
    }
    interface Interactor{
        void requestTask(RequestCallback<Task> requestCallback, int id);
        void saveTask(RequestCallback<Task> requestCallback, int id, Task task);
        void deleteTask(RequestCallback<ResponseMessage> requestCallback, int id);
        void addTask(RequestCallback<Task> requestCallback, Task task);
    }
}