package com.asosapp.phone.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asosapp.phone.R;

/**
 * Created by Leo on 2016/2/24.
 */
public class AgeChangeActivity extends Activity implements View.OnClickListener{

    private TextView keep;
    private EditText age_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_change);
        init();
    }

    private void init() {

        keep= (TextView) findViewById(R.id.keep);
        age_edit= (EditText) findViewById(R.id.age_edit);
        age_edit.setHint(getIntent().getStringExtra("oldage"));
        listener();

    }

    private void listener() {
        keep.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.keep:
                if (age_edit.getText().toString().equals("")){
                    Toast.makeText(this,"请输入年龄",Toast.LENGTH_SHORT).show();
                }else {
                    keepData();
                    Intent intent = new Intent();
                    intent.putExtra("userage", age_edit.getText().toString().trim());
                    setResult(1, intent);
                    finish();
                }
                break;
            default:
                break;
        }

    }

    /**
     *
     * 保存事件
     */
    private void keepData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", 1); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("user_age", age_edit.getText().toString());
        editor.commit();//提交修改
    }
}
