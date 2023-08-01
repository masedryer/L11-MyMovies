package sg.edu.rp.c346.id22020860.l11_mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class movieDisplay extends AppCompatActivity {
    ListView lv;
    ArrayList<Movies> alMovie;
    Button pg13;

    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);

        alMovie = new ArrayList<Movies>();


        lv = findViewById(R.id.lv);



        pg13 = findViewById(R.id.btnPG13);


        //ArrayAdapter adapter = new ArrayAdapter<>(songDisplay.this, android.R.layout.simple_list_item_1, alSong);


        customAdapter = new CustomAdapter(this,R.layout.row,alMovie);
        lv.setAdapter(customAdapter);

        DBHelper db = new DBHelper(movieDisplay.this);
        alMovie.clear();
        alMovie.addAll(db.getMovies());
        customAdapter.notifyDataSetChanged();


        ArrayList<String> data = db.getMovieContent();

        ;
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Movies data = alMovie.get(position);




                // Create an Intent to launch the third activity
                Intent intent = new Intent(movieDisplay.this, updateMovie.class);
                intent.putExtra("data", data);
                startActivity(intent);

            }
        });
        pg13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(movieDisplay.this);

                ArrayList<Movies> pg13List = new ArrayList<>();

                for (Movies movie : alMovie) {
                    if (movie.getRating().equals("PG13")) {
                        pg13List.add(movie);
                    }
                }

                customAdapter = new CustomAdapter(movieDisplay.this, R.layout.row, pg13List);
                lv.setAdapter(customAdapter);
            }
        });




    }
}