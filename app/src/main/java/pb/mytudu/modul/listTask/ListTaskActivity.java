package pb.mytudu.modul.listTask;

import android.content.Intent;
import android.view.View;

import pb.mytudu.base.BaseFragmentHolderActivity;
import pb.mytudu.modul.user.ProfileActivity;


public class ListTaskActivity extends BaseFragmentHolderActivity {
    ListTaskFragment listTaskFragment;

    @Override
    protected void initializeFragment() {
        initializeView();
        setTitle("My Task List");

        listTaskFragment = new ListTaskFragment();
        setCurrentFragment(listTaskFragment, false);
        btnProfileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });
    }



}
