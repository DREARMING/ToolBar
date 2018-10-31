package com.mvcoder.tytoolbar;

import android.view.View;

/**
 * Created by mvcoder on 2017/8/7.
 */

public interface ITYToobar {

    void setTyMainTitle(String mainTitle);
    void setTySubTitle(String subTitle);
    void setTyRightText(String rightText);
    void setTyRightListener(View.OnClickListener listener);
    void setLeftListener(View.OnClickListener listener);
}
