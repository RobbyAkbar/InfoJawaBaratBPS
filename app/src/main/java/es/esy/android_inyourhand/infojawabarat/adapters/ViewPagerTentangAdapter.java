package es.esy.android_inyourhand.infojawabarat.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import es.esy.android_inyourhand.infojawabarat.viewpagers.TentangFragment;

/**
 * Created by robby on 07/11/17.
 */

public class ViewPagerTentangAdapter extends FragmentPagerAdapter {

    public ViewPagerTentangAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        TentangFragment fragment = new TentangFragment();
        Bundle args = new Bundle();
        switch(position){
            case 0:
                args.putString("menu", "1");
                fragment.setArguments(args);
                return fragment;
            case 1:
                args.putString("menu", "2");
                fragment.setArguments(args);
                return fragment;
            case 2:
                args.putString("menu", "3");
                fragment.setArguments(args);
                return fragment;
            case 3:
                args.putString("menu", "4");
                fragment.setArguments(args);
                return fragment;
            case 4:
                args.putString("menu", "5");
                fragment.setArguments(args);
                return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Informasi Umum";
            case 1:
                return "Visi dan Misi";
            case 2:
                return "Struktur Organisasi";
            case 3:
                return "Tugas, Fungsi dan Kewenangan";
            case 4:
                return "Pengolahan Data";
        }
        return null;
    }

}
