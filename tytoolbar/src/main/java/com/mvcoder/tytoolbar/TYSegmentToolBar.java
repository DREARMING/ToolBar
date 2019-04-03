package com.mvcoder.tytoolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;


/**
 * Created by mvcoder on 2017/8/7.
 */

public class TYSegmentToolBar extends Toolbar {

    private int backgroundColor;
    private String rightText;
    private String leftText;
    private String secondRightStr;
    private Drawable leftDrawable;
    private Drawable rightDrawable;
    private Drawable secondRightDrawable;

    private TextView tyRightText;
    private TextView tyLeftText;
    private TextView secondRightText;
    private SegmentTabLayout segmentTabLayout;
    private OnClickListener righListener;
    private OnClickListener leftListener;
    private OnClickListener secondRightListener;
    private OnTabSelectListener onTabSelectListener;

    private int indicatorColor;
    private int selectTextColor;
    private int unSelectTextColor;
    private int dividerColor;

    public TYSegmentToolBar(Context context) {
        this(context, null);
    }

    public TYSegmentToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TYSegmentToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TYSegmentToolBar, defStyleAttr, 0);
        int n = a.getIndexCount();
        backgroundColor = getResources().getColor(R.color.colorPrimary);
        for(int i = 0; i<n; i++){
            int index = a.getIndex(i);
            if(index == R.styleable.TYToolBar_toolbarBackground){
                backgroundColor = a.getColor(index, backgroundColor);
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
            }else if(index == R.styleable.TYToolBar_toolbarLeftText){
                leftText = a.getString(index);
            }else if(index == R.styleable.TYToolBar_toolbarSLIndicatorColor){
                int defColor =getResources().getColor(R.color.colorAccent);
                indicatorColor = a.getColor(index, defColor);
            }else if(index == R.styleable.TYToolBar_toolbarSLSelectTextColor){
                int defColor = getResources().getColor(R.color.colorPrimary);
                selectTextColor = a.getColor(index, defColor);
            }else if(index == R.styleable.TYToolBar_toolbarSLUnSelectTextColor){
                int defColor = Color.BLACK;
                unSelectTextColor = a.getColor(index, defColor);
            }else if(index == R.styleable.TYToolBar_toolbarSLStrokeColor){
                int defColor =getResources().getColor(R.color.colorAccent);
                dividerColor = a.getColor(index, defColor);
            }
        }
        a.recycle();
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_tysegmenttoolbar, this);
        tyLeftText = view.findViewById(R.id.tyLeftText);
        tyRightText = view.findViewById(R.id.tyRightText);
        secondRightText = view.findViewById(R.id.tySecondRightText);
        segmentTabLayout = view.findViewById(R.id.tySegmentTabLayout);


        if(!TextUtils.isEmpty(leftText)){
            tyLeftText.setText(leftText);
            tyLeftText.setVisibility(VISIBLE);
            tyRightText.setCompoundDrawables(null,null,null,null);
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

        segmentTabLayout.setIndicatorColor(indicatorColor);
        segmentTabLayout.setTextSelectColor(selectTextColor);
        segmentTabLayout.setTextUnselectColor(unSelectTextColor);
        segmentTabLayout.setDividerColor(dividerColor);

        segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int i) {
                if(onTabSelectListener != null){
                    onTabSelectListener.onTabSelect(i);
                }
            }

            @Override
            public void onTabReselect(int i) {
                if(onTabSelectListener != null){
                    onTabSelectListener.onTabReselect(i);
                }
            }
        });

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

    public void setOnTabSelectListener(@NonNull OnTabSelectListener listener){
        this.onTabSelectListener = listener;
    }

    public void setSegmentTabData(@NonNull String[] titles){
        segmentTabLayout.setTabData(titles);
    }


    public int getIndicatorColor() {
        return indicatorColor;
    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        segmentTabLayout.setIndicatorColor(indicatorColor);
    }

    public int getSelectTextColor() {
        return selectTextColor;
    }

    public void setSelectTextColor(int selectTextColor) {
        this.selectTextColor = selectTextColor;
        segmentTabLayout.setTextSelectColor(indicatorColor);
    }

    public int getUnSelectTextColor() {
        return unSelectTextColor;
    }

    public void setUnSelectTextColor(int unSelectTextColor) {
        this.unSelectTextColor = unSelectTextColor;
        segmentTabLayout.setTextUnselectColor(unSelectTextColor);
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        segmentTabLayout.setDividerColor(dividerColor);
    }

    public void setTyRightText(String rightText, OnClickListener listener) {
        tyRightText.setText(rightText);
        tyRightText.setCompoundDrawables(null,null,null,null);
        tyRightText.setVisibility(View.VISIBLE);
        this.righListener = listener;
    }

    public void setLeftListener(OnClickListener listener){
        this.leftListener = listener;
    }

    public void setLeftText(String leftText){
        tyLeftText.setText(leftText);
        tyLeftText.setCompoundDrawables(null,null,null,null);
    }

    public void setTyLeftDrawable(int resourceId){
        Drawable leftDrawable  = getContext().getResources().getDrawable(resourceId);
        tyLeftText.setText("");
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
