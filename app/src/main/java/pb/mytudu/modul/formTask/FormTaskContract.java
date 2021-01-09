package pb.mytudu.modul.formTask;

import pb.mytudu.base.BasePresenter;
import pb.mytudu.base.BaseView;

public interface FormTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToListTask();
    }

    interface Presenter extends BasePresenter {
        void performSaveListTask();
    }
}