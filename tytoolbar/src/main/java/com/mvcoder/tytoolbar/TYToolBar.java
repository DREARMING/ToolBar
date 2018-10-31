package com.mvcoder.tytoolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


/**
 * Created by mvcoder on 2017/8/7.
 */

public class TYToolBar extends Toolbar {

    private int backgroundColor;
    private String mainTitle;
    private String subTitle;
    private String rightText;
    private String secondRightStr;
    private Drawable leftDrawable;
    private Drawable rightDrawable;
    private Drawable secondRightDrawable;

    private TextView tyMainTitle;
    private TextView tySubTitle;
    private TextView tyRightText;
    private TextView tyLeftText;
    private TextView secondRightText;
    private OnClickListener righListener;
    private OnClickListener leftListener;
    private OnClickListener secondRightListener;

    public TYToolBar(Context context) {
        this(context, null);
    }

    public TYToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TYToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TYToolBar, defStyleAttr, 0);
        int n = a.getIndexCount();
        backgroundColor = getResources().getColor(R.color.colorPrimary);
        mainTitle = "";
        subTitle = "";
        for(int i = 0; i<n; i++){
            int index = a.getIndex(i);
            if(index == R.styleable.TYToolBar_toolbarBackground){
                backgroundColor = a.getColor(index, backgroundColor);
            }else if(index == R.styleable.TYToolBar_toolbarMainTitle){
                mainTitle = a.getString(index);
            }else if(index == R.styleable.TYToolBar_toolbarSubTitle){
                subTitle = a.getString(index);
            }else if(index == R.styleable.TYToolBar_toolbarRightText){
                rightText = a.getString(index);
            }else if(index == R.styleable.TYToolBar_toolbarLeftDrawable){
                leftDrawable = a.getDrawable(index);
                leftDrawable.setBounds(0,0,leftDrawable.getIntrinsicWidth(),leftDrawable.getIntrinsicHeight());
            }else if(index == R.styleable.TYToolBar_toolbarSecondDrawable){
                secondRightDrawable = a.getDrawable(index);
                secondRightDrawable.setBounds(0,0,secondRightDrawable.getIntrinsicWidth(),secondRightDrawable.getIntrinsicHeight());
            }else if(index == R.styleable.TYToolBar_toolbarSecondRightText){
                secondRightStr = a.getString(index);
            }else if(index == R.styleable.TYToolBar_toolbarRightDrawable){
                rightDrawable = a.getDrawable(index);
                rightDrawable.setBounds(0,0,rightDrawable.getIntrinsicWidth(),rightDrawable.getIntrinsicHeight());
            }
        }
        a.recycle();
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_tytoolbar, this);
        tyMainTitle = view.findViewById(R.id.tyMainTitle);
        tySubTitle = view.findViewById(R.id.tySubtitle);
        tyLeftText = view.findViewById(R.id.tyLeftText);
        tyRightText = view.findViewById(R.id.tyRightText);
        secondRightText = view.findViewById(R.id.tySecondRightText);

        tyMainTitle.setText(mainTitle);
        if(!TextUtils.isEmpty(subTitle)){
            setTySubTitle(subTitle);
        }

        if(!TextUtils.isEmpty(rightText)){
            tyRightText.setText(rightText);
            tyRightText.setVisibility(VISIBLE);
            tyRightText.setCompoundDrawables(null,null,null,null);
        }

        if(!TextUtils.isEmpty(secondRightStr)){
            secondRightText.setVisibility(VISIBLE);
            secondRightText.setText(secondRightStr);
            secondRightText.setCompoundDrawables(null,null,null,null);
        }

        setBackgroundColor(backgroundColor);

        if(leftDrawable != null){
            tyLeftText.setCompoundDrawables(leftDrawable,null,null,null);
            tyLeftText.setText("");
            tyLeftText.setVisibility(VISIBLE);
        }

        if(rightDrawable != null){
            tyRightText.setCompoundDrawables(null,null,rightDrawable,null);
            tyRightText.setText("");
            tyRightText.setVisibility(VISIBLE);
        }

        if(secondRightDrawable != null){
            secondRightText.setCompoundDrawables(null,null,secondRightDrawable,null);
            secondRightText.setText("");
            secondRightText.setVisibility(VISIBLE);
        }


        tyLeftText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leftListener != null){
                    leftListener.onClick(v);
                }
            }
        });

        tyRightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(righListener != null) {
                    righListener.onClick(v);
                }
            }
        });

        secondRightText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(secondRightListener != null){
                    secondRightListener.onClick(v);
                }
            }
        });
    }


    public void setTyMainTitle(String mainTitle) {
        tyMainTitle.setText(mainTitle);
    }

    public void setTySubTitle(String subTitle) {
        if(TextUtils.isEmpty(subTitle)) return;
        tySubTitle.setText(subTitle);
        tySubTitle.setVisibility(View.VISIBLE);
    }

    public void setTyRightText(String rightText, OnClickListener listener) {
        tyRightText.setText(rightText);
        tyRightText.setCompoundDrawables(null,null,null,null);
        tyRightText.setVisibility(View.VISIBLE);
        this.righListener = listener;
    }

    public void setLeftListener(OnClickListener listener){
        this.leftListener = listener;
        tyLeftText.setText("");
    }

    public void setLeftText(String leftText){
        tyLeftText.setText(leftText);
        tyLeftText.setCompoundDrawables(null,null,null,null);
    }

    public void setTyLeftDrawable(int resourceId){
        Drawable leftDrawable  = getContext().getResources().getDrawable(resourceId);
        leftDrawable.setBounds(0,0,leftDrawable.getIntrinsicWidth(),leftDrawable.getIntrinsicHeight());
        tyLeftText.setCompoundDrawables(leftDrawable,null,null,null);
    }

    public void setRightListener(OnClickListener listener){
        this.righListener = listener;
    }

    public void setSecondRightListener(OnClickListener listener){
        this.secondRightListener = listener;
    }

    public void setTyRightDrawable(int resourceId){
        Drawable rightDrawable  = getContext().getResources().getDrawable(resourceId);
        rightDrawable.setBounds(0,0,rightDrawable.getIntrinsicWidth(),rightDrawable.getIntrinsicHeight());
        tyRightText.setCompoundDrawables(null,null,rightDrawable,null);
        tyRightText.setText("");
        tyRightText.setVisibility(VISIBLE);
    }

    public void setTySecondRightText(String secRightText){
        if(TextUtils.isEmpty(secRightText)) return;
        secondRightText.setVisibility(VISIBLE);
        secondRightText.setText(secRightText);
        secondRightText.setCompoundDrawables(null,null,null,null);
    }

    public void setTySecondRightDrawable(int resourceId, OnClickListener listener){
        Drawable rightDrawable  = getContext().getResources().getDrawable(resourceId);
        rightDrawable.setBounds(0,0,rightDrawable.getIntrinsicWidth(),rightDrawable.getIntrinsicHeight());
        secondRightText.setCompoundDrawables(rightDrawable,null,null,null);
        secondRightText.setText("");
        secondRightText.setVisibility(VISIBLE);
        secondRightListener = listener;
    }

    public void setTySecondRightDrawableVisibility(int visibility){
        secondRightText.setVisibility(visibility);
    }
}
