package com.example.clearedittext;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.calendar.R;


public class ClearEditTextActivity extends AppCompatActivity implements View.OnClickListener {


    private ClearEditText username;
    private ClearEditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_edit_text);
        initView();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(username.getText())){
                    //设置晃动
//                    username.setShakeAnimation();
                    //设置提示
                    Toast.makeText(ClearEditTextActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText())){
//                    password.setShakeAnimation();
                    Toast.makeText(ClearEditTextActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }


    private void initView() {
        username = (ClearEditText) findViewById(R.id.username);
        password = (ClearEditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:

                break;
        }
    }

    private void submit() {
        // validate
        String usernameString = username.getText().toString().trim();
        if (TextUtils.isEmpty(usernameString)) {
            Toast.makeText(this, "输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwordString = password.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
