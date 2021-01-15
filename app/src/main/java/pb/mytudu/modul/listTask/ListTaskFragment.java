package pb.mytudu.modul.listTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
    TextView tvIsEmpty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_list_task, container, false);
        initUIElements();
        mPresenter = new ListTaskPresenter(this, new ListTaskInteracator(UtilProvider.getSharedPreferenceUtil()));
        mPresenter.requestListTask();
        mPresenter.start();

        return fragmentView;
    }

    private void initUIElements() {
        btnAddTask = fragmentView.findViewById(R.id.createTaskBtn);
        lvTask = fragmentView.findViewById(R.id.lvListTask);
        tvIsEmpty = fragmentView.findViewById(R.id.tvIsEmpty);

        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task item = (Task) adapter.getItem(i);
                Intent intent = new Intent(getActivity(), FormTaskActivity.class);
                intent.putExtra("id", item.getId());
                intent.putExtra("formType", "edit");
                getActivity().finish();
                startActivity(intent);
            }
        });
        btnAddTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FormTaskActivity.class);
                intent.putExtra("formType", "add");
                getActivity().finish();
                startActivity(intent);
            }
        });

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
        if(tasks.size()==0){
            tvIsEmpty.setVisibility(View.VISIBLE);
        }
        adapter = new TasksAdapter(getActivity(), (ArrayList<Task>) tasks);
        lvTask.setAdapter(adapter);
    }

    @Override
    public void showError(String errorMessage) {

    }
}
