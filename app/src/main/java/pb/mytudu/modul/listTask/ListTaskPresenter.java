package pb.mytudu.modul.listTask;

public class ListTaskPresenter implements ListTaskContract.Presenter{
    private final ListTaskContract.View view;

    public ListTaskPresenter(ListTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performFormTask(int type, int pos, int id) {
        view.redirectToFormTask(type, pos, id);
    }
}
