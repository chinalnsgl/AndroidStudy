package com.study.sgl.tools.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Fragment 基类
 *
 * @author Song.gl
 * @version 2016 06 09 23:13
 */
public abstract class BaseFragment extends Fragment {

    private String TAG;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
    }

    /**
     * 启动 Activity
     * @param tarActivity 目标 Activity
     */
    protected void toActivity(Class<? extends BaseActivity> tarActivity) {
        Intent intent = new Intent(getActivity(), tarActivity);
        startActivity(intent);
    }
}
