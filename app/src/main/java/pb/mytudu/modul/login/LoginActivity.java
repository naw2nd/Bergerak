package pb.mytudu.modul.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import pb.mytudu.R;
import pb.mytudu.modul.listTask.ListTaskActivity;
import pb.mytudu.modul.register.RegisterActivity;
import pb.mytudu.utils.SharedPreferenceUtil;
import pb.mytudu.utils.UtilProvider;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    EditText etEmail;
    EditText etPassword;
    EditText etUsername;
    Button btnLogin;
    TextView tvRegister;
    private LoginPresenter presenter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initUIElements();
        setPresenter(new LoginPresenter(this, new LoginInteractor(UtilProvider.getSharedPreferenceUtil())));
        presenter.start();

    }

    private void initUIElements(){
        etEmail = findViewById(R.id.rtAuthEmail);
        etPassword = findViewById(R.id.etAuthPassword);
        btnLogin = findViewById(R.id.btnAuth);
        tvRegister = findViewById(R.id.tvAuthRegister);
        etUsername = findViewById(R.id.etAuthUsername);
        progressBar = findViewById(R.id.pbAuth);
        etUsername.setVisibility(View.GONE);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToRegister();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    @Override
    public void startLoading() {
//        Toast.makeText(this, "Signing in", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Sign in success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        this.finish();
        startActivity(new Intent(this, ListTaskActivity.class));
    }

    @Override
    public void loginFailed(String message) {
        Toast.makeText(this, "Gagal login "+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void redirectToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
        this.finish();
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.presenter =  presenter;
    }
}