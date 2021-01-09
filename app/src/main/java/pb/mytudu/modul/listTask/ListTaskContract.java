package pb.mytudu.modul.listTask;


import pb.mytudu.base.BasePresenter;
import pb.mytudu.base.BaseView;

public interface ListTaskContract {
    interface View extends BaseView<Presenter> {
//        void redirectToAddTask();
        void redirectToFormTask(int type, int pos, int id);
    }

    interface Presenter extends BasePresenter {
//        void performAddTask();
        void performFormTask(int type, int pos, int id);
    }
}
