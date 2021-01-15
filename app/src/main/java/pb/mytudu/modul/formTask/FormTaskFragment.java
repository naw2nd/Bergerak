package pb.mytudu.modul.formTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;

import pb.mytudu.R;
import pb.mytudu.base.BaseFragment;
import pb.mytudu.model.Task;
import pb.mytudu.modul.listTask.ListTaskActivity;
import pb.mytudu.utils.UtilProvider;


public class FormTaskFragment extends BaseFragment<FormTaskActivity, FormTaskContract.Presenter> implements FormTaskContract.View {

    EditText etName;
    EditText etDesc;
    Button btnSave;
    Button btnDelete;
    CheckBox cbStatus;
    Button btnShare;
    String formType;
    Task sharedTask;
    int id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_form_task, container, false);
        initUIElements();

        mPresenter = new FormTaskPresenter(this, new FormTaskInteractor(UtilProvider.getSharedPreferenceUtil()));
        mPresenter.start();
        defineFormType();

        return fragmentView;
    }

    private void defineFormType() {
        Intent intent = getActivity().getIntent();
        id = intent.getIntExtra("id", -1);
        formType = intent.getStringExtra("formType");

        if(formType.equals("edit")){
            setTitle("Edit Task");
            mPresenter.requestTask(id);
            btnSave.setText("Save");
        }else {
            setTitle("Add Task");
            cbStatus.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
            btnShare.setVisibility(View.GONE);
        }
    }

    private void initUIElements() {
        etName = fragmentView.findViewById(R.id.etNameTask);
        etDesc = fragmentView.findViewById(R.id.etDescTask);
        btnSave = fragmentView.findViewById(R.id.btnSave);
        btnDelete = fragmentView.findViewById(R.id.btnDelete);
        cbStatus = fragmentView.findViewById(R.id.cbStatus);
        btnShare = fragmentView.findViewById(R.id.btnShare);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtnSaveClick();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setBtnDeleteClick();
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setBtnShareClick();
            }
        });

    }

    public void setBtnShareClick(){
        String sharedString = "Task : "+sharedTask.getTitle()+"\nDescription : "+sharedTask.getDescription()+"\nStatus : "+sharedTask.getStatus();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, sharedString);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    public void setBtnSaveClick(){
        Task task;
        String name = etName.getText().toString();
        String desc = etDesc.getText().toString();
        String status;
        if(cbStatus.isChecked()){
            status = "done";
        }else{
            status = "working";
        }

        if(formType.equals("edit")){
            task = new Task(id, name, desc, status);
            mPresenter.saveTask(id, task);
            Toast.makeText(getActivity(), "Task Saved", Toast.LENGTH_SHORT).show();
            getActivity().finish();
            startActivity(new Intent(getActivity(), ListTaskActivity.class));
        }else{
            task = new Task(name, desc, status);
            mPresenter.addTask(task);
            Toast.makeText(getActivity(), "Task Added", Toast.LENGTH_SHORT).show();
            getActivity().finish();
            startActivity(new Intent(getActivity(), ListTaskActivity.class));
        }
    }

    public void setBtnDeleteClick(){
        mPresenter.deleteTask(id);
    }

    @Override
    public void setPresenter(FormTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showTask(Task tasks) {
        sharedTask = tasks;
        etName.setText(tasks.getTitle());
        etDesc.setText(tasks.getDescription());
        if(tasks.getStatus().equals("done")){
            cbStatus.setChecked(true);
        }else{
            cbStatus.setChecked(false);
        }
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void successDelete() {
        Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
        getActivity().finish();
        startActivity(new Intent(getActivity(), ListTaskActivity.class));
    }
}
