package pb.mytudu.modul.formTask;

public class FormTaskPresenter implements FormTaskContract.Presenter{
    private final FormTaskContract.View view;

    public FormTaskPresenter(FormTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performSaveListTask(){
        view.redirectToListTask();
    }

}
