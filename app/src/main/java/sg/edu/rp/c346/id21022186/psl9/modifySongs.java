package sg.edu.rp.c346.id21022186.psl9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class modifySongs extends AppCompatActivity {

    EditText etID, etTitle, etSinger,etYear;
    RadioGroup stars;
    Button update, delete,cancel;
    Songs data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_songs);

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etYear = findViewById(R.id.etYear);
        stars = findViewById(R.id.starsGrp);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        cancel = findViewById(R.id.cancel);

        Intent i = getIntent();
        data = (Songs) i.getSerializableExtra("data");

        etID.setText("ID" + data.getId());
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(data.getYear());
        stars.getCheckedRadioButtonId();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(modifySongs.this);
                // data.setNoteContent(etContent.getText().toString());
                dbh.updateSongs(data);
                dbh.close();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(modifySongs.this);
                dbh.deleteSong(data.getId());

            }
        });


    }

    protected void onResume() {
        super.onResume();

        cancel.performClick();
    }

}