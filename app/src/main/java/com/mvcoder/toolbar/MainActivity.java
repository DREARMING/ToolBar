package com.mvcoder.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mvcoder.tytoolbar.TYToolBar;

public class MainActivity extends AppCompatActivity {

    private TYToolBar tyToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tyToolBar = findViewById(R.id.toolbar);
        initToolbar();
    }

    private void initToolbar() {
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
