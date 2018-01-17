package eu.lmre.baptiste.evalandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by lemairba on 17/01/18.
 */

public class PersoDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perso_details);

        ScrollView infoPersoScrollView = findViewById(R.id.infoPersoScrollView);
        LinearLayout infoPersoLinearLayout = findViewById(R.id.infoPersoLinearLayout);
        Intent intent = getIntent();
        String perso_choisi = intent.getStringExtra(MainActivity.PERSO_CHOISI);
        People peopleChoisi = MainActivity.peoples.get(Integer.parseInt(perso_choisi));
        TextView textView = findViewById(R.id.infoPerso);
        TextView textViewRight = findViewById(R.id.infoPersoRight);

        String infosLeft = "Nom :\n";
        String infosRight = peopleChoisi.name+"\n";
        infosLeft += "Taille :\n";
        infosRight += peopleChoisi.height+"\n";
        infosLeft += "Poids :\n";
        infosRight += peopleChoisi.mass+"\n";

        infosLeft += "Couleur de cheveux :\n";
        infosRight += peopleChoisi.hair_color+"\n";
        infosLeft += "Couleur de peau :\n";
        infosRight += peopleChoisi.skin_color+"\n";
        infosLeft += "Couleur des yeux :\n";
        infosRight += peopleChoisi.eye_color+"\n";
        infosLeft += "Date de naissance :\n";
        infosRight += peopleChoisi.birth_year+"\n";
        infosLeft += "Sexe :\n";
        infosRight += peopleChoisi.gender+"\n";
        infosLeft += "Monde de naissance :\n";
        infosRight += peopleChoisi.homeworld+"\n";
        infosLeft += "Créé le :\n";
        infosRight += peopleChoisi.created+"\n";
        infosLeft += "Édité le :\n";
        infosRight += peopleChoisi.edited+"\n";
        infosLeft += "URL :\n";
        infosRight += peopleChoisi.url+"\n";

        textView.setText(infosLeft);
        textViewRight.setText(infosRight);

        infoPersoScrollView.setOnTouchListener(new OnSwipeTouchListener(PersoDetailsActivity.this) {
            public void onSwipeRight() {
                finish();
            }

        });
    }
}