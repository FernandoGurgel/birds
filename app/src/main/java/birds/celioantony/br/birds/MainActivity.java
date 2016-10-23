package birds.celioantony.br.birds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Musics> musics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.grid_musics);
        gridview.setAdapter(new CardAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });


        // get data in firebase
        readDataFirebase();

    }

    public void toYoutube(View view) {
        Intent intent = new Intent(this, YoutubeActivity.class);
        startActivity(intent);
    }

    public void readDataFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("musics");


        Log.i("READ FIREBASE", "READ DATA FIREBASE");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("LISTENER", "" + dataSnapshot.getValue());
                musics = (ArrayList<Musics>) dataSnapshot.getValue();
                Log.i("ARRAYLIST MUSIC", "" + musics);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.i("onCancelled", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        reference.addValueEventListener(postListener);
    }
}
