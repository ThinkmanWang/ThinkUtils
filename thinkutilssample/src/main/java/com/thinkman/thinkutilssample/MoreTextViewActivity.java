package com.thinkman.thinkutilssample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thinkman.thinkutils.view.MoreTextView;

import butterknife.ButterKnife;

public class MoreTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_text_view);

        ButterKnife.bind(this);
        MoreTextView text1 = ((MoreTextView) findViewById(R.id.tv_more));
        text1.setText(getResources().getString(R.string.text));
        text1.refreshText();
        text1.refreshText();
    }
}
