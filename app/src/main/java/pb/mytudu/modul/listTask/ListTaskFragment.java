package pb.mytudu.modul.listTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import pb.mytudu.R;
import pb.mytudu.base.BaseFragment;
import pb.mytudu.model.DB;
import pb.mytudu.model.Task;
import pb.mytudu.modul.formTask.FormTaskActivity;
import pb.mytudu.utils.TasksAdapter;


public class ListTaskFragment extends BaseFragment<ListTaskActivity, ListTaskContract.Presenter> implements ListTaskContract.View {

    Button btnAddTask;
    ArrayList<Task> listTask = new ArrayList<>();
    ListView lvTask;
    DB db;
    TasksAdapter adapter;

    public ListTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_list_task, container, false);
        mPresenter = new ListTaskPresenter(this);
        mPresenter.start();

        lvTask = fragmentView.findViewById(R.id.lvListTask);
        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                Task task = (Task) parent.getAdapter().getItem(pos);
                int taskId = task.getId();
                setBtnFormTask(2, pos, taskId);
            }
        });

        btnAddTask = fragmentView.findViewById(R.id.createTaskBtn);
        btnAddTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "hey", Toast.LENGTH_SHORT).show();
                setBtnFormTask(1, 0, -1);
            }
        });
        setTitle("My Task List");
        showListTask();

        return fragmentView;
    }

    public void showListTask(){
        db = DB.getInstance();
//        db.initiateData();
        listTask = db.getListTask();
//        Intent intent = getActivity().getIntent();
//        if(intent.getStringArrayListExtra("listTask") != null)
//            listTask = intent.getStringArrayListExtra("listTask");
//        ArrayAdapter<Task> adapter = new ArrayAdapter<Task>(getActivity(), R.layout.task, listTask);
        adapter = new TasksAdapter(getActivity(), listTask);
        lvTask.setAdapter(adapter);
    }

    public void setBtnFormTask(int type, int pos, int id){mPresenter.performFormTask(type, pos, id);}

    @Override
    public void setPresenter(ListTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToFormTask(int type, int pos, int id) {
        Intent intent = new Intent(activity, FormTaskActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("formType", type);
        intent.putExtra("position", pos);
        startActivity(intent);
        activity.finish();
    }
}
