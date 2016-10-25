package birds.celioantony.br.birds;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.AdapterListener {

    ArrayList<Musics> musics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get data in firebase
         readDataFirebase();

    }

    public void createAdapter(ArrayList<Musics> m) {
        // Create Adapter
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, m);

        //Get reference our Recycler on layout
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_musics);

        // Connect to recyler adapter
        recyclerView.setAdapter(recyclerAdapter);
        //Set orientation  of our RecycerView how LinearLayout (Vertical)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerAdapter.setListener(this);
    }

    public void readDataFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("musics");
        final ProgressDialog progressDialog = new ProgressDialog(this);


        progressDialog.setMessage("Carregando músicas...");
        progressDialog.show();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("LISTENER", "" + dataSnapshot.getValue());

                for(DataSnapshot obj: dataSnapshot.getChildren()) {
                    Musics m = new Musics();
                    m = obj.getValue(Musics.class);
                    musics.add(m);
                }

                createAdapter(musics);
                progressDialog.dismiss();
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

    @Override
    public void onItemClick(View view, int posicao) {
//        Toast.makeText(this, ""+posicao, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.search_musics){
            startActivity(new Intent(this, SearchMusics.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
