package pb.mytudu.modul.formTask;

import android.view.View;

import pb.mytudu.base.BaseFragmentHolderActivity;


public class FormTaskActivity extends BaseFragmentHolderActivity {
    FormTaskFragment formTaskFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.VISIBLE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        formTaskFragment = new FormTaskFragment();
        setCurrentFragment(formTaskFragment, false);
    }

}
