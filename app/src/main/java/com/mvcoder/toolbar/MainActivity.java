package com.mvcoder.toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mvcoder.tytoolbar.TYSegmentToolBar;
import com.mvcoder.tytoolbar.flycotablayout.listener.OnTabSelectListener;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TYSegmentToolBar tyToolBar;

    private String[] titles = new String[]{"单控","集控"};

    private Button btMain;
    private Button btControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tyToolBar = findViewById(R.id.toolbar);
        initToolbar();
        initview();
    }

    private void initview() {
        btMain = findViewById(R.id.btMain);
        btControl = findViewById(R.id.btControl);
        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tyToolBar.setMode(1);
                tyToolBar.setTyMainTitle("首页");
                tyToolBar.setTyRightText("二维码", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("二维码");
                    }
                });
                tyToolBar.setLeftText("");
                tyToolBar.setMainTitleColor(getResources().getColor(R.color.toolbarIndicatorColor));
            }
        });
        btControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tyToolBar.setMode(2);
                tyToolBar.setLeftText("场景");
                tyToolBar.setTyRightText("地点", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("场景");

                    }
                });
            }
        });
    }

    private void initToolbar() {
        tyToolBar.setSegmentTabData(titles);
        tyToolBar.setOnTabSelectListener(new OnTabSelectListener() {
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
