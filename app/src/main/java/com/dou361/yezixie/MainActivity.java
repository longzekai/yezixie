package com.dou361.yezixie;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dou361.yezixie.R;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick({R.id.btn_setting, R.id.btn_01})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_setting:
                startActivity(SettingActivity.class);
                break;
            case R.id.btn_01:
                Toast.makeText(this, "xxx", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
