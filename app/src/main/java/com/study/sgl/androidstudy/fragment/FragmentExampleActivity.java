package com.study.sgl.androidstudy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.study.sgl.androidstudy.R;
import com.study.sgl.tools.base.BaseActivity;

public class FragmentExampleActivity extends BaseActivity {

    private Fragment fragmentOne;
    private Fragment fragmentTwo;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);
        fragmentOne = FragmentOne.newInstance("", "");
        fragmentTwo = FragmentTwo.newInstance(null, null);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentContent, fragmentOne,"fragmentOne");
        transaction.commit();
    }

    public void switchFragment(View view) {
        if (manager.findFragmentByTag("fragmentOne") != null) {
            manager.beginTransaction()
                    .replace(R.id.fragmentContent, fragmentTwo, "fragmentTwo")
                    .commit();
        } else {
            manager.beginTransaction()
                    .replace(R.id.fragmentContent, fragmentOne, "fragmentOne")
                    .commit();
        }
    }
}
