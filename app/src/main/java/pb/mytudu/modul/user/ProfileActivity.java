package pb.mytudu.modul.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pb.mytudu.R;
import pb.mytudu.model.User;
import pb.mytudu.modul.listTask.ListTaskActivity;
import pb.mytudu.modul.login.LoginActivity;
import pb.mytudu.modul.register.RegisterActivity;
import pb.mytudu.utils.UtilProvider;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.View {
    TextView tvUsername;
    TextView tvEmail;
    Button btnLogout;
    ProgressBar progressBar;
    private ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        initUIElements();
        setPresenter(new ProfilePresenter(this, UtilProvider.getSharedPreferenceUtil()));
        presenter.start();
        presenter.getProfile();
    }

    private void initUIElements(){
        tvUsername = findViewById(R.id.tvProfileUsernameValue);
        tvEmail = findViewById(R.id.tvProfileEmailValue);
        btnLogout = findViewById(R.id.btnProfileLogout);
        progressBar = findViewById(R.id.pbProfile);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                presenter.logout();
            }
        });
    }

    @Override
    public void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void redirectToLogin() {
        this.finishAffinity();
        startActivity(new Intent(this, LoginActivity.class));
        Toast.makeText(this, "Logout success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProfile(User user) {
        tvUsername.setText(user.getUsername());
        tvEmail.setText(user.getEmail());
    }

    @Override
    public void setPresenter(ProfilePresenter presenter) {
        this.presenter =  presenter;
    }
}