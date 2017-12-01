package es.esy.android_inyourhand.infojawabarat.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import es.esy.android_inyourhand.infojawabarat.R;
import es.esy.android_inyourhand.infojawabarat.adapters.ViewPagerKategoriAdapter;

public class KategoriActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        Intent intent = getIntent();
        String kategori = intent.getStringExtra("kategori");
        id = intent.getStringExtra("id");

        toolbar = findViewById(R.id.tab_toolbar);
        toolbar.setTitle(kategori);
        setupToolbar();
        viewPager = findViewById(R.id.tab_view_pager);
        setupViewPager();
        tabLayout = findViewById(R.id.tab_layout);
        setupTab();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            if(tabLayout.getWidth() < KategoriActivity.this.getResources().getDisplayMetrics().widthPixels)
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
        viewPager.setAdapter(new ViewPagerKategoriAdapter(getSupportFragmentManager(), id));
    }
}
