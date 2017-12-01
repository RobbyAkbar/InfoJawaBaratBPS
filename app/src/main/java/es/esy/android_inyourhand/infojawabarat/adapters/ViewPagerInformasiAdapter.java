package es.esy.android_inyourhand.infojawabarat.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import es.esy.android_inyourhand.infojawabarat.viewpagers.InfodataFragment;

/**
 * Created by robby on 07/11/17.
 */

public class ViewPagerInformasiAdapter extends FragmentPagerAdapter {

    public ViewPagerInformasiAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        InfodataFragment fragment = new InfodataFragment();
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
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Berita Resmi Statistik";
            case 1:
                return "Tabel Statistik";
            case 2:
                return "Publikasi Statistik";
            case 3:
                return "Berita";
        }
        return null;
    }

}
