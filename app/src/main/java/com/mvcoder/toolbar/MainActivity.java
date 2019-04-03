package com.mvcoder.toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mvcoder.tytoolbar.TYSegmentToolBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TYSegmentToolBar tyToolBar;

    private String[] titles = new String[]{"单控","集控"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tyToolBar = findViewById(R.id.toolbar);
        initToolbar();
    }

    private void initToolbar() {
        tyToolBar.setSegmentTabData(titles);
        tyToolBar.setOnTabSelectListener(new com.flyco.tablayout.listener.OnTabSelectListener() {
            @Override
            public void onTabSelect(int i) {
                showToast("select tab :" + i);
            }

            @Override
            public void onTabReselect(int i) {

            }
        });
        tyToolBar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("back");
            }
        });
        tyToolBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("right");
            }
        });
        tyToolBar.setSecondRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("second right");
            }
        });
        setSupportActionBar(tyToolBar);
    }

    private void showToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
