package com.dou361.yezixie;

import android.os.Bundle;

import com.dou361.yezixie.R;

import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @OnClick(R.id.btn_multilingual)
    public void onViewClicked() {
        startActivity(LanguageSettingActivity.class);
    }
}
