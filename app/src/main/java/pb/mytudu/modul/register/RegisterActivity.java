package pb.mytudu.modul.register;

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
import pb.mytudu.modul.listTask.ListTaskActivity;
import pb.mytudu.utils.UtilProvider;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {
    EditText etEmail;
    EditText etPassword;
    EditText etUsername;
    Button btnRegister;
    TextView tvRegister;
    ProgressBar progressBar;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initUIElements();
        setPresenter(new RegisterPresenter(this, new RegisterInteractor(UtilProvider.getSharedPreferenceUtil())));
        presenter.start();

    }

    private void initUIElements(){
        etEmail = findViewById(R.id.rtAuthEmail);
        etPassword = findViewById(R.id.etAuthPassword);
        btnRegister = findViewById(R.id.btnAuth);
        tvRegister = findViewById(R.id.tvAuthRegister);
        etUsername = findViewById(R.id.etAuthUsername);
        progressBar = findViewById(R.id.pbAuth);

        btnRegister.setText("Register");
        tvRegister.setVisibility(View.GONE);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.register(etUsername.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    @Override
    public void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
//        Toast.makeText(this, "Please wait", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void endLoading() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSuccess() {
        this.finish();
        startActivity(new Intent(this, ListTaskActivity.class));
    }

    @Override
    public void registerFailed(String message) {
        Toast.makeText(this, "Gagal Register "+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(RegisterPresenter presenter) {
        this.presenter =  presenter;
    }
}