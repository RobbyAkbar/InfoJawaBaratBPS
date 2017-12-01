package es.esy.android_inyourhand.infojawabarat.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import es.esy.android_inyourhand.infojawabarat.R;
import es.esy.android_inyourhand.infojawabarat.fragments.DeveloperFragment;
import es.esy.android_inyourhand.infojawabarat.fragments.EkonomiPerdaganganFragment;
import es.esy.android_inyourhand.infojawabarat.fragments.HomeFragment;
import es.esy.android_inyourhand.infojawabarat.fragments.InfromasiFragment;
import es.esy.android_inyourhand.infojawabarat.fragments.PertanianPertambanganFragment;
import es.esy.android_inyourhand.infojawabarat.fragments.PublikasiFragment;
import es.esy.android_inyourhand.infojawabarat.fragments.SosialKependudukanFragment;
import es.esy.android_inyourhand.infojawabarat.fragments.TentangBPSFragment;

public class InfoBPS extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toast toast;
    private long lastBackPressTime = 0;

    private final static String SELECTED_TAG = "selected_index";
    private static int selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        drawerLayout = findViewById(R.id.drawer_layout);
        ImageView imageView = header.findViewById(R.id.imgheader);
        Picasso.with(this).load("http://catatan-robby.000webhostapp.com/header.png").into(imageView);

        if (savedInstanceState!=null){
            navigationView.getMenu().findItem(savedInstanceState.getInt(SELECTED_TAG)).setChecked(true);
            return;
        }

        selectedIndex = R.id.nav_halaman_utama;

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new HomeFragment(), HomeFragment.class.getSimpleName()).commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_TAG, selectedIndex);
    }

    public void setupNavigationDrawer(Toolbar toolbar){
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_halaman_utama:
                if(!item.isChecked()){
                    selectedIndex = R.id.nav_halaman_utama;
                    item.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new HomeFragment(), HomeFragment.class.getSimpleName()).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_informasi_terbaru:
                if(!item.isChecked()){
                    selectedIndex = R.id.nav_informasi_terbaru;
                    item.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new InfromasiFragment(), InfromasiFragment.class.getSimpleName()).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_publikasi:
                if(!item.isChecked()){
                    selectedIndex = R.id.nav_publikasi;
                    item.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new PublikasiFragment(), PublikasiFragment.class.getSimpleName()).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_sosial_kependudukan:
                if(!item.isChecked()){
                    selectedIndex = R.id.nav_sosial_kependudukan;
                    item.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new SosialKependudukanFragment(), SosialKependudukanFragment.class.getSimpleName()).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_ekonomi_perdagangan:
                if(!item.isChecked()){
                    selectedIndex = R.id.nav_ekonomi_perdagangan;
                    item.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new EkonomiPerdaganganFragment(), EkonomiPerdaganganFragment.class.getSimpleName()).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case  R.id.nav_pertanian_pertambangan:
                if (!item.isChecked()){
                    selectedIndex = R.id.nav_pertanian_pertambangan;
                    item.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new PertanianPertambanganFragment(), PertanianPertambanganFragment.class.getSimpleName()).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case  R.id.nav_about:
                if (!item.isChecked()){
                    selectedIndex = R.id.nav_about;
                    item.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new TentangBPSFragment(), TentangBPSFragment.class.getSimpleName()).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case  R.id.nav_kontak:
                if (!item.isChecked()){
                    selectedIndex = R.id.nav_kontak;
                    item.setChecked(true);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new DeveloperFragment(), DeveloperFragment.class.getSimpleName()).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_keluar:
                if(!item.isChecked()){
                    keluar();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }

    private void keluar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setMessage("Apakah anda yakin keluar dari aplikasi ini?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        InfoBPS.this.finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Tekan sekali lagi untuk keluar",
                    Toast.LENGTH_LONG);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }

    public void website(View view) {
        startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse("http://jabar.bps.go.id/".trim())), "Open with"));
    }
}
