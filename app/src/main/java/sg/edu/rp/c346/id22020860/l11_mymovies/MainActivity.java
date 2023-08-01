package sg.edu.rp.c346.id22020860.l11_mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText title;
    EditText genre;
    EditText year;
    Spinner rating;
    Button insert;
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.Title);
        genre = findViewById(R.id.Genre);
        year = findViewById(R.id.Year);
        insert = findViewById(R.id.update);
        show = findViewById(R.id.Show);
        rating = findViewById(R.id.spinnerRating);

        // Set up the adapter for the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rating_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rating.setAdapter(adapter);

        // Set up item selected listener for the spinner
        rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedRating = adapterView.getSelectedItem().toString();
                String counterpartString = getCounterpartString(selectedRating);
                showCounterpartToast(counterpartString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);

                String movieTitle = title.getText().toString();
                String movieGenre = genre.getText().toString();
                int movieYear = Integer.parseInt(year.getText().toString());
                String movieRating = rating.getSelectedItem().toString();

              db.insertMovie(movieTitle,movieGenre,movieYear,movieRating);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(MainActivity.this,movieDisplay.class);
                startActivity(intent);
            }
        });

    }

    private String getCounterpartString(String rating) {
        switch (rating) {
            case "G":
                return "G";
            case "PG":
                return "PG";
            case "PG13":
                return "PG13";
            case "NC16":
                return "NC16";
            case "M18":
                return "M18";
            case "R21":
                return "R21";
            default:
                return "Unknown Rating";
        }
    }

    private void showCounterpartToast(String counterpartString) {
        Toast.makeText(this, counterpartString, Toast.LENGTH_SHORT).show();
    }
}