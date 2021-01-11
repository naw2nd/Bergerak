package pb.mytudu.modul.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import pb.mytudu.R;
import pb.mytudu.modul.listTask.ListTaskActivity;
import pb.mytudu.utils.UtilProvider;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    TextView tvRegister;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUIElements();
        setPresenter(new LoginPresenter(this, new LoginInteractor(UtilProvider.getSharedPreferenceUtil())));
        presenter.start();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    private void initUIElements(){
        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLoginLogin);
        tvRegister = findViewById(R.id.tvLoginRegister);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

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
    public void setPresenter(LoginPresenter presenter) {
        this.presenter =  presenter;
    }
}