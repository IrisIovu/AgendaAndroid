package eu.ase.angedasincronizareonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProjectFirebaseActivity extends AppCompatActivity {
    TextView tv_nameProject;
    TextView tv_procentSucces;
    Button btn_addProject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_firebase);
        init();
    }

    private void init(){
        tv_nameProject = findViewById(R.id.et_numeProiect);
        tv_procentSucces = findViewById(R.id.et_procentSucces);
        btn_addProject = findViewById((R.id.btn_addProject));

        btn_addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProjectFirebaseActivity.this, "utilizatorul a aapasat pe buton", Toast.LENGTH_SHORT).show();
                if(!validate())
                {
                    String name=tv_nameProject.getText().toString();
                    Intent i = new Intent(getApplicationContext(),AddToFirebaseAndSeedata.class);
                    int procent = Integer.parseInt(tv_procentSucces.getText().toString());
                    i.putExtra("nameProject",name);
                    i.putExtra("procent",procent);
                    startActivity(i);
                }
                else{
                    Toast.makeText(ProjectFirebaseActivity.this, "Reintroduceti datele", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validate(){
        if(tv_nameProject.getText() != null && tv_nameProject.getText().toString().trim().length()>0)
        {
            return false;
        }
        else{

            Toast.makeText(this, "Numele proiectului nu poate fi gol", Toast.LENGTH_SHORT).show();
        }

        if(tv_procentSucces.getText()!=null || tv_procentSucces.getText().toString().trim().length()>0 || (Integer.parseInt(tv_procentSucces.getText().toString())<100) || (Integer.parseInt(tv_procentSucces.getText().toString())>0))
        {
            return false;
        }
        else{
            Toast.makeText(this, "Procentul de succes al proiectului este introdus gresit", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


}
