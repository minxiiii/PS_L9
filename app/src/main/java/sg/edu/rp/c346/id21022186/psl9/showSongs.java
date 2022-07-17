package sg.edu.rp.c346.id21022186.psl9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class showSongs extends AppCompatActivity {

    Button btn5stars;
    ListView List;
    ArrayList<Songs> al;
    ArrayAdapter<Songs> aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_songs);

        btn5stars = findViewById(R.id.btn5stars);
        List = findViewById(R.id.List);

        al = new ArrayList<Songs>();
        aa = new ArrayAdapter<Songs>(this,
                android.R.layout.simple_list_item_1, al);


        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int i, long l) {

                Intent intentReceived = getIntent();

                DBHelper dbh = new DBHelper(showSongs.this);
                al.clear();
                al.addAll(dbh.getAllSongs());
                aa.notifyDataSetChanged();

            }
        });

        btn5stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(showSongs.this);
                al.clear();
                al.addAll(dbh.getAllFilteredSongs(5));



            }
        });

    }
}