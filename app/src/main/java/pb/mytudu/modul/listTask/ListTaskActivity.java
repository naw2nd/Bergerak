package pb.mytudu.modul.listTask;

import android.view.View;

import pb.mytudu.base.BaseFragmentHolderActivity;


public class ListTaskActivity extends BaseFragmentHolderActivity {
    ListTaskFragment listTaskFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        listTaskFragment = new ListTaskFragment();
        setCurrentFragment(listTaskFragment, false);

    }



}
