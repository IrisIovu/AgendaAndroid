package eu.ase.angedasincronizareonline;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;

import eu.ase.angedasincronizareonline.fragment.AboutFragment;
import eu.ase.angedasincronizareonline.fragment.HomeFragment;
import eu.ase.angedasincronizareonline.fragment.ListviewMeetingsFragment;
import eu.ase.angedasincronizareonline.fragment.TransferMarketFragment;
import eu.ase.angedasincronizareonline.fragment.ViewByDateFragment;
import eu.ase.angedasincronizareonline.utils.Meeting;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD_MEETING= 200;
    DrawerLayout drawerLayout;
    FloatingActionButton fabAddMeeting;
    NavigationView navigationView;
    Fragment currentFragment;
    ArrayList<Meeting> meetings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configNavigation();
        initComponents();
        openDefaultFragment(savedInstanceState);
    }

    private void openDefaultFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            currentFragment = createListViewMeetingFragment();
            openFragment();
            navigationView.setCheckedItem(R.id.main_nav_home);
        }
    }

    private void initComponents() {
        fabAddMeeting = findViewById(R.id.main_fab_add_meeting);
        fabAddMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(getApplicationContext(),
                                AddMeetingActivity.class);
                startActivityForResult(intent,
                        REQUEST_CODE_ADD_MEETING);
            }
        });

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navigationItemEvent());
    }

    private NavigationView
            .OnNavigationItemSelectedListener navigationItemEvent() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(
                    @NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.main_nav_list_meetings) {
                    //optiune acasa
                    currentFragment = createListViewMeetingFragment();
                } else if (menuItem.getItemId() == R.id.main_nav_transfer_market) {
                    //optiune Transfer Market
                    currentFragment = new TransferMarketFragment();
                } else if(menuItem.getItemId() == R.id.main_nav_about){
                    //optiunea despre
                    currentFragment = new AboutFragment();
                }
                else if(menuItem.getItemId() == R.id.main_nav_home)
                {
                    currentFragment=new HomeFragment();
                }
                else{
                    currentFragment = new ViewByDateFragment();
                }


                //adaugare fragment
                openFragment();
                //inchidere meniu lateral
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    private void openFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame_container, currentFragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_MEETING
                && resultCode == RESULT_OK
                && data != null) {
            Meeting meeting = data.getParcelableExtra(AddMeetingActivity
                    .ADD_MEETING_KEY);
            if (meeting != null) {
                Toast.makeText(getApplicationContext(),
                        meeting.toString(),
                        Toast.LENGTH_LONG).show();
                meetings.add(meeting);
                if (currentFragment instanceof ListviewMeetingsFragment) {
                    ((ListviewMeetingsFragment) currentFragment).notifyInternal();
                }
            }
        }
    }

    private Fragment createListViewMeetingFragment() {
        Fragment fragment = new ListviewMeetingsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ListviewMeetingsFragment.MEETING_KEY, meetings);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void configNavigation() {
        //initializare toolbar - bara de actiune
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //initializare drawer layout - panou meniu lateral
        drawerLayout = findViewById(R.id.drawer_layout);
        //legare meniu lateral cu bara actiune
        // + eveniment de deschidere
        //creare instanta utilitara
        ActionBarDrawerToggle actionBar =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        //atasare eveniment
        drawerLayout.addDrawerListener(actionBar);
        //sincronizare actionBartoggle
        actionBar.syncState();
    }
}
