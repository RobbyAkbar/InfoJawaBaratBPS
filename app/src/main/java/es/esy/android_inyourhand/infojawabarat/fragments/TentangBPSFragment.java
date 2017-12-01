package es.esy.android_inyourhand.infojawabarat.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.esy.android_inyourhand.infojawabarat.R;
import es.esy.android_inyourhand.infojawabarat.adapters.ViewPagerTentangAdapter;
import es.esy.android_inyourhand.infojawabarat.ui.InfoBPS;

/**
 * A simple {@link Fragment} subclass.
 */
public class TentangBPSFragment extends Fragment {

    private InfoBPS infoBPS;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public TentangBPSFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        infoBPS = (InfoBPS)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tentang_brs, container, false);

        toolbar = view.findViewById(R.id.tab_toolbar);
        setupToolbar();
        viewPager = view.findViewById(R.id.tab_view_pager);
        setupViewPager();
        tabLayout = view.findViewById(R.id.tab_layout);
        setupTab();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        infoBPS.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar(){
        toolbar.setTitle(getString(R.string.tentang_aplikasi));
        infoBPS.setSupportActionBar(toolbar);
    }

    private void setupTab(){
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.post(tabLayout_config);
    }

    Runnable tabLayout_config = new Runnable()
    {
        @Override
        public void run()
        {
            if(tabLayout.getWidth() < getActivity().getResources().getDisplayMetrics().widthPixels)
            {
                tabLayout.setTabMode(TabLayout.MODE_FIXED);
                ViewGroup.LayoutParams mParams = tabLayout.getLayoutParams();
                mParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                tabLayout.setLayoutParams(mParams);
            }
            else
            {
                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            }
        }
    };

    private void setupViewPager(){
        viewPager.setAdapter(new ViewPagerTentangAdapter(getActivity().getSupportFragmentManager()));
    }

}
