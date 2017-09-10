package com.dou361.yezixie;

import android.os.Bundle;
import android.view.View;

import com.dou361.yezixie.R;
import com.dou361.yezixie.utils.LocaleLanguageUtils;

import java.util.Locale;

import butterknife.OnClick;

public class LanguageSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_language);
    }

    @OnClick({R.id.btn_auto, R.id.btn_english, R.id.btn_simplified_chinese, R.id.btn_traditional_chinese, R.id.btn_taiwan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_auto:
                LocaleLanguageUtils.changeLanguage(this, Locale.getDefault());
                recreate();
                break;
            case R.id.btn_english:
                LocaleLanguageUtils.changeLanguage(this, Locale.ENGLISH);
                recreate();
                break;
            case R.id.btn_simplified_chinese:
                LocaleLanguageUtils.changeLanguage(this, Locale.SIMPLIFIED_CHINESE);
                recreate();
                break;
            case R.id.btn_traditional_chinese:
                LocaleLanguageUtils.changeLanguage(this, Locale.TRADITIONAL_CHINESE);
                recreate();
                break;
            case R.id.btn_taiwan:
                LocaleLanguageUtils.changeLanguage(this, Locale.TAIWAN);
                recreate();
                break;
        }
    }
}
