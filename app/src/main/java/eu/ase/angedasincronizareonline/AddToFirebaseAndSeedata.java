package eu.ase.angedasincronizareonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import eu.ase.angedasincronizareonline.utils.Proiect;
import eu.ase.angedasincronizareonline.utils.ProiecteArrayAdapter;

public class AddToFirebaseAndSeedata extends AppCompatActivity {

    Intent intent= getIntent();
    private String proiectName;
    DatabaseReference databaseProiecte;
    private int procentSucces;
    ListView proiecte;
    List<Proiect> proiecteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_firebase_and_seedata);
        databaseProiecte = FirebaseDatabase.getInstance().getReference("proiect");
    }

    private void init(){
        proiecteList=new ArrayList<>();
        proiecte=findViewById(R.id.lv_projects);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             proiectName = extras.getString("nameProject");
             procentSucces = extras.getInt("procent");

        }

    }
    private void addProiect(){
        String id =databaseProiecte.push().getKey();
        Proiect proiect =new Proiect(id,proiectName,procentSucces);
        databaseProiecte.child(id).setValue((proiect));
        Toast.makeText(this, "Proiect adaugat", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart(){
        super.onStart();
        init();
        addProiect();
        databaseProiecte.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               proiecteList.clear();
                for(DataSnapshot proiectSnapshot : dataSnapshot.getChildren()){
                    Proiect proiect = proiectSnapshot.getValue(Proiect.class);
                    proiecteList.add(proiect);
                }

                ProiecteArrayAdapter adapter = new ProiecteArrayAdapter(AddToFirebaseAndSeedata.this,proiecteList);
                proiecte.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
