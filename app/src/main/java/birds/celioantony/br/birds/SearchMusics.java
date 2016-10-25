package birds.celioantony.br.birds;

import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class SearchMusics extends AppCompatActivity implements RecyclerAdapter.AdapterListener {

    ArrayList<Musics> musics = new ArrayList<>();
    ProgressDialog progressDialog;
    private final int SIZE_LIMIT = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_musics);
        settingsActivity();

        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Buscando...");


        ImageView imgSearch = (ImageView) findViewById(R.id.img_search);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Search();
            }
        });
    }

    public void createAdapter(ArrayList<Musics> m) {

        // Create Adapter
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, m);

        //Get reference our Recycler on layout
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_search);

        // Connect to recyler adapter
        recyclerView.setAdapter(recyclerAdapter);
        //Set orientation  of our RecycerView how LinearLayout (Vertical)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerAdapter.setListener(this);
    }

    public void Search() {
        EditText searchField = (EditText) findViewById(R.id.input_search_music);
        String information = searchField.getText().toString();

        searchMusics(information);
        Log.d("INFO", "PASS FUNCTION SEARCH_MUSIC");
    }


    public void searchMusics(String info) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("musics");
        Query q = reference.orderByChild("title").startAt(info).limitToFirst(SIZE_LIMIT);
        musics.clear();

        q.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Musics music = new Musics();
                Log.d("dataSnapshot", ""+dataSnapshot);
                music = dataSnapshot.getValue(Musics.class);
                musics.add(music);

                if(musics.size() > 0) {
                    Log.d("ADAPTER CREATE", "CREATE ADAPTER");
                    createAdapter(musics);
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("onChildChanged", ""+s);
                Log.d("DataSnapshot: ", ""+dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemClick(View view, int posicao) {

    }

    public void settingsActivity() {
        SetttingsApp settings = new SetttingsApp(SearchMusics.this);
        settings.setTtitleAndIcon("Birds Musics");
    }
}
