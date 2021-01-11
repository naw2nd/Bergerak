package pb.mytudu.modul.listTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import pb.mytudu.R;
import pb.mytudu.base.BaseFragment;
import pb.mytudu.model.Task;
import pb.mytudu.modul.formTask.FormTaskActivity;
import pb.mytudu.utils.TasksAdapter;
import pb.mytudu.utils.UtilProvider;


public class ListTaskFragment extends BaseFragment<ListTaskActivity, ListTaskContract.Presenter> implements ListTaskContract.View {

    Button btnAddTask;
    ListView lvTask;
    TasksAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_list_task, container, false);
        mPresenter = new ListTaskPresenter(this, new ListTaskInteracator(UtilProvider.getSharedPreferenceUtil()));
        mPresenter.requestListTask();
        mPresenter.start();

        lvTask = fragmentView.findViewById(R.id.lvListTask);
        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task item = (Task) adapter.getItem(i);
//                Toast.makeText(getActivity(), "Terpilih list ke "+item.getCode(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), FormTaskActivity.class);
                intent.putExtra("id", item.getId());
                intent.putExtra("formType", "edit");
                getActivity().finish();
                startActivity(intent);
            }
        });

        btnAddTask = fragmentView.findViewById(R.id.createTaskBtn);
        btnAddTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FormTaskActivity.class);
                intent.putExtra("formType", "add");
                getActivity().finish();
                startActivity(intent);
            }
        });
        setTitle("My Task List");

        return fragmentView;
    }
    @Override
    public void setPresenter(ListTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showListTask(List<Task> tasks) {
        adapter = new TasksAdapter(getActivity(), (ArrayList<Task>) tasks);
        lvTask.setAdapter(adapter);
    }

    @Override
    public void showError(String errorMessage) {

    }
}
