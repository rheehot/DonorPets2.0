package kr.hs.emirim.sookhee.redonorpets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail;
    EditText etPasswd;
    Button btnLogin;
    TextView tvGoJoin;

    String email, passwd;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = firebaseAuth.getInstance();

        etEmail = (EditText)findViewById(R.id.loginEmailEditText);
        etPasswd = (EditText)findViewById(R.id.loginPasswdEditText);
        btnLogin = (Button)findViewById(R.id.loginButton);
        tvGoJoin = (TextView)findViewById(R.id.loginGoJoinTextView);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString().trim();
                passwd = etPasswd.getText().toString().trim();

                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(getApplicationContext(), "이메일 형식을 다시 확인해주세요", Toast.LENGTH_SHORT).show();
                }
                else if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,15}.$", passwd)){
                    Toast.makeText(getApplicationContext(), "비밀번호 형식을 다시 확인해주세요", Toast.LENGTH_SHORT).show();
                }
                else{

                    firebaseAuth.signInWithEmailAndPassword(email, passwd)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
//                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                    startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "가입되지 않은 계정입니다", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        tvGoJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(intent);
            }
        });


    }
}
