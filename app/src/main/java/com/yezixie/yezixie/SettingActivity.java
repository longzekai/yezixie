package com.yezixie.yezixie;

import android.os.Bundle;

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
