package com.atguigu.administrator.mytest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.atguigu.administrator.mytest.base.BaseFragment;
import com.atguigu.administrator.mytest.fragment.LocalAudioFragment;
import com.atguigu.administrator.mytest.fragment.LocalVideoFragment;
import com.atguigu.administrator.mytest.fragment.NetAudioFragment;
import com.atguigu.administrator.mytest.fragment.NetVideoFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rg_main;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);

        rg_main.check(R.id.rb_local_video);
        initFragment();
        initListener();

    }

    private void initListener() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_local_video:
                        position = 0;
                        break;
                    case R.id.rb_local_audio:
                        position = 1;
                        break;
                    case R.id.rb_net_audio:
                        position = 2;
                        break;
                    case R.id.rb_net_video:
                        position = 3;
                        break;
                }
                Fragment currentFragment = fragments.get(position);
                switchFragment(currentFragment);

            }


        });
        rg_main.check(R.id.rb_local_video);

    }

    private void switchFragment(Fragment currentFragment) {
        if (tempFragment != currentFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (currentFragment != null) {

                if (!currentFragment.isAdded()) {

                    if (tempFragment != null) {

                        ft.hide(tempFragment);
                    }

                    ft.add(R.id.fl_mainc_content, currentFragment);

                } else {

                    if (tempFragment != null) {

                        ft.hide(tempFragment);
                    }

                    ft.show(currentFragment);

                }
                ft.commit();

            }
            tempFragment = currentFragment;

        }

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new LocalVideoFragment());
        fragments.add(new LocalAudioFragment());
        fragments.add(new NetAudioFragment());
        fragments.add(new NetVideoFragment());
    }
}
