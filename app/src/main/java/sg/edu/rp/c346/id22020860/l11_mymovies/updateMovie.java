package sg.edu.rp.c346.id22020860.l11_mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class updateMovie extends AppCompatActivity {
    EditText title;
    EditText genre;
    EditText year;
    Spinner rating;
    Button Update;
    Button Delete;
    Button Cancel;
    Movies data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);
        title = findViewById(R.id.Title);
        genre = findViewById(R.id.Genre);
        year = findViewById(R.id.Year);
        Update = findViewById(R.id.update);
        Delete = findViewById(R.id.Delete);
        Cancel = findViewById(R.id.Cancel);
        rating = findViewById(R.id.spinnerRating);

        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");

        title.setText(data.getTitle());
        genre.setText(data.getGenre());
        year.setText(String.valueOf(data.getYear()));


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rating_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rating.setAdapter(adapter);
        int position = adapter.getPosition(data.getRating()); // Get the position of the rating in the array
        rating.setSelection(position);


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(updateMovie.this);
                data.setTitle(title.getText().toString());
                data.setGenre(genre.getText().toString());
                data.setYear(Integer.parseInt(year.getText().toString()));
                data.setRating(rating.getSelectedItem().toString());

                dbh.updateMovies(data);
                dbh.close();
                Toast.makeText(updateMovie.this,"The content is edited",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(updateMovie.this, MainActivity.class);
                startActivity(intent);
    }


        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(updateMovie.this);
                dbh.deleteMovies(data.get_id());
                Intent intent = new Intent(updateMovie.this, movieDisplay.class);
                startActivity(intent);
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(updateMovie.this, movieDisplay.class);
                startActivity(intent);
            }
        });
    }
}
