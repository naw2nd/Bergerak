package pb.mytudu.modul.formTask;

import pb.mytudu.callback.RequestCallback;
import pb.mytudu.model.Task;

public class FormTaskPresenter implements FormTaskContract.Presenter{
    private final FormTaskContract.View view;
    private final FormTaskContract.Interactor interactor;

    public FormTaskPresenter(FormTaskContract.View view, FormTaskContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }
    @Override
    public void start() {}

    @Override
    public void requestTask(int id) {
        view.startLoading();
        interactor.requestTask(new RequestCallback<Task>() {
            @Override
            public void requestSuccess(Task task) {
                view.endLoading();
                view.showTask(task);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        }, id);
    }

    @Override
    public void saveTask(int id, Task task) {
        view.startLoading();
        interactor.saveTask(new RequestCallback<Task>() {
            @Override
            public void requestSuccess(Task task) {
                view.endLoading();
                view.showTask(task);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        }, id, task);
    }

    @Override
    public void deleteTask(int id) {
        view.startLoading();
        interactor.deleteTask(id);
        view.endLoading();

    }

    @Override
    public void addTask(Task task) {
        view.startLoading();
        interactor.addTask(new RequestCallback<Task>() {
            @Override
            public void requestSuccess(Task task) {
                view.endLoading();
                view.showTask(task);
            }

            @Override
            public void requestFailed(String errorMessage) {
                view.endLoading();
                view.showError(errorMessage);
            }
        }, task);
    }
}
