package eu.ase.angedasincronizareonline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import java.util.ArrayList;

import eu.ase.angedasincronizareonline.database.model.User;
import eu.ase.angedasincronizareonline.database.service.UserService;
import eu.ase.angedasincronizareonline.fragment.HomeFragment;
import eu.ase.angedasincronizareonline.fragment.ListEvents;
import eu.ase.angedasincronizareonline.fragment.MeetingInfoJsonFragment;
import eu.ase.angedasincronizareonline.utils.CustomSharedPreferences;
import eu.ase.angedasincronizareonline.utils.Event;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD_EVENT = 200;
    DrawerLayout drawerLayout;
    FloatingActionButton fabAddEvent;
    NavigationView navigationView;
    private User currentUser;
    Fragment currentFragment;
    ArrayList <Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configNavigation();
        initComponents();
        openDefaultFragment(savedInstanceState);
        getCurrentUser();
    }

    private void openDefaultFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            currentFragment = createHomeFragment();
            openFragment();
            navigationView.setCheckedItem(R.id.main_nav_home);
        }
    }
    private Fragment createHomeFragment() {
        Fragment fragment = new ListEvents();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ListEvents.EVENT_KEY, events);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initComponents() {
        fabAddEvent = findViewById(R.id.main_fab_add_meeting);
        fabAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =
                        new Intent(getApplicationContext(),
                                AddEvent.class);
                startActivityForResult(intent1, 200);
            }
        });
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navigationItemEvent());
    }
    @SuppressLint("StaticFieldLeak")
    private void deleteUser() {
        new UserService.Delete(getApplicationContext()) {
            @Override
            protected void onPostExecute(Integer result) {
                if (result == 0) {
                    CustomSharedPreferences.setIdToPreferences(getApplicationContext(), RegisterActivity.SHARED_PREF_NAME, -1);
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(intent);
                }
            }
        }.execute(currentUser);
    }

    private NavigationView
            .OnNavigationItemSelectedListener navigationItemEvent() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(
                    @NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.MeetingInfo) {
                    currentFragment = new MeetingInfoJsonFragment();
                }
                else if(menuItem.getItemId() == R.id.main_nav_home)
                {
                    currentFragment=new HomeFragment();
                }
                else if(menuItem.getItemId() == R.id.main_nav_list_events)
                {
                    currentFragment=createHomeFragment();
                }
                else if (menuItem.getItemId() == R.id.main_nav_vizualizareproiecte){
                    Intent intent = new Intent(getApplicationContext(), ProjectFirebaseActivity.class);
                    startActivity(intent);
                }

                openFragment();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }
    private void getCurrentUser() {
        if (getIntent().getExtras() != null) {
            currentUser = getIntent().getExtras().getParcelable(LoginActivity.CURRENT_USER);

        }
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
       if (requestCode == REQUEST_CODE_ADD_EVENT
                && resultCode == RESULT_OK
                && data != null) {
            Event event = data.getParcelableExtra(AddEvent
                    .ADD_EVENT_KEY);
            if (event != null) {
                events.add(event);
                if (currentFragment instanceof ListEvents) {
                    ((ListEvents) currentFragment).notifyInternal();
                }
            }
        }
    }



    private void configNavigation() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBar =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBar);
        actionBar.syncState();
    }
}
