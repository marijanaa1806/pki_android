package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TorteActivity extends MainActivity2 {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.torte);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setBackgroundResource(R.drawable.backgr);

        viewPager = findViewById(R.id.view_pager2);
        ArrayList<String> arrayList = new ArrayList<>(0);

        arrayList.add("Torte");
        arrayList.add("Kolaci");

        tabLayout.setupWithViewPager(viewPager);

        prepareViewPager(viewPager, arrayList);
    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());

        for (int i = 0; i < arrayList.size(); i++) {
            MainFragment mainFragment = new MainFragment();

            Bundle bundle = new Bundle();

            bundle.putString("title", arrayList.get(i));

            mainFragment.setArguments(bundle);

            adapter.addFragment(mainFragment, arrayList.get(i));
        }

        viewPager.setAdapter(adapter);
    }

    private static class MainAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        private final ArrayList<String> stringArrayList = new ArrayList<>();

        public void addFragment(Fragment fragment, String s) {
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }

        public MainAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringArrayList.get(position);
        }
    }
}
