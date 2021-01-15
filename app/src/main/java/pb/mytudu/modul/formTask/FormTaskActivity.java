package pb.mytudu.modul.formTask;

import android.content.Intent;
import android.view.View;

import pb.mytudu.R;
import pb.mytudu.base.BaseFragmentHolderActivity;
import pb.mytudu.modul.listTask.ListTaskActivity;


public class FormTaskActivity extends BaseFragmentHolderActivity {
    FormTaskFragment formTaskFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        btnProfileMenu.setVisibility(View.GONE);
        ivIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit));

        formTaskFragment = new FormTaskFragment();
        setCurrentFragment(formTaskFragment, false);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        startActivity(new Intent(this, ListTaskActivity.class));
    }
}
