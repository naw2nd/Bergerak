package pb.mytudu.modul.formTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import pb.mytudu.R;
import pb.mytudu.base.BaseFragment;
import pb.mytudu.model.DB;
import pb.mytudu.model.Task;
import pb.mytudu.modul.listTask.ListTaskActivity;


public class FormTaskFragment extends BaseFragment<FormTaskActivity, FormTaskContract.Presenter> implements FormTaskContract.View {

    EditText etName;
    EditText etDesc;
    Button btnSave;
    Button btnCancel;
    Button btnDelete;
    int typeForm;
    int pos;
    int id;
    DB db;

    public FormTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_form_task, container, false);
        mPresenter = new FormTaskPresenter(this);
        mPresenter.start();

        db = DB.getInstance();

        etName = fragmentView.findViewById(R.id.etNameTask);
        etDesc = fragmentView.findViewById(R.id.etDescTask);
        btnSave = fragmentView.findViewById(R.id.btnSave);
        btnCancel = fragmentView.findViewById(R.id.btnCancel);
        btnDelete = fragmentView.findViewById(R.id.btnDelete);

        Intent intent = getActivity().getIntent();
        id = intent.getIntExtra("id", -1);
        defineFormType(intent);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnSaveClick();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToListTask();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setBtnDeleteClick();
            }
        });

        return fragmentView;
    }

    public void defineFormType(Intent intent){
        typeForm = intent.getIntExtra("formType", 1);
        pos = intent.getIntExtra("position", -1);
        if(typeForm == 1){
            setTitle("Create a New Task");
            btnDelete.setVisibility(View.GONE);
        }else{
            setTitle("Edit this Task");
            Task task = db.getTaskById(id);
            etName.setText(task.getTitle());
            etDesc.setText(task.getDescription());
            btnSave.setText("Save");
        }
    }

    public void setBtnSaveClick(){
        Task task;
        String name = etName.getText().toString();
        String desc = etDesc.getText().toString();
        if(typeForm == 1){
            task = new Task(db.getListTask().size(), name, desc);
            db.getListTask().add(task);
//            listTask.add(name);
        }else{
            task = new Task(id, name, desc);
            db.setTaskById(id, task);
//            listTask.set(pos, name);
        }
//        mPresenter.performSaveListTask(listTask);
        mPresenter.performSaveListTask();
    }

    public void setBtnDeleteClick(){
        if(typeForm == 2){
            db.removeById(id);
//            listTask.remove(pos);
        }
        mPresenter.performSaveListTask();
    }

    @Override
    public void setPresenter(FormTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToListTask() {
            Intent intent = new Intent(activity, ListTaskActivity.class);
            startActivity(intent);
            activity.finish();
    }
}
