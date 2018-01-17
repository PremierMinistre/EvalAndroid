package eu.lmre.baptiste.evalandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by lemairba on 17/01/18.
 */

public class PersoDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perso_details);

        Intent intent = getIntent();
        String perso_choisi = intent.getStringExtra(MainActivity.PERSO_CHOISI);
        People peopleChoisi = MainActivity.peoples.get(Integer.parseInt(perso_choisi));
        TextView textView = findViewById(R.id.infoPerso);

        String infos = "Nom : "+peopleChoisi.name+"\n";
        infos += "Taille : "+peopleChoisi.height+"\n";
        infos += "Poids : "+peopleChoisi.mass+"\n";

        infos += "Couleur de cheveux : "+peopleChoisi.hair_color+"\n";
        infos += "Couleur de peau : "+peopleChoisi.skin_color+"\n";
        infos += "Couleur des yeux : "+peopleChoisi.eye_color+"\n";
        infos += "Date de naissance : "+peopleChoisi.birth_year+"\n";
        infos += "Sexe : "+peopleChoisi.gender+"\n";
        infos += "Monde de naissance : "+peopleChoisi.homeworld+"\n";
        infos += "Créé le : "+peopleChoisi.created+"\n";
        infos += "Édité le : "+peopleChoisi.edited+"\n";
        infos += "URL : "+peopleChoisi.url+"\n";

        textView.setText(infos);
    }
}