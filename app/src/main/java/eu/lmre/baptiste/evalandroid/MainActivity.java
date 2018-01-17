package eu.lmre.baptiste.evalandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import retrofit2.Call;
import java.util.List;
import retrofit2.Response;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Callback;


/***
 *
 * Site pour l'aide Retrofit2
 * http://tutos-android-france.com/retrofit2/
 *
 ***/
public class MainActivity extends AppCompatActivity {

    private final SwapiService swapiService = SwapiService.Builder.getInstance();
    ListView listDesPersos;
    static List<People> peoples;
    TextView texte;
    public final static String PERSO_CHOISI = "eu.lmre.baptiste.evalandroid.PERSO_CHOISI";
    public String nextPage;
    int numeroDePage = 1;
    int compteurPeople = 0;
    ArrayAdapter<String> adapterPersos;
    View footerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listDesPersos = findViewById(R.id.listDesPersos);
        footerView =  ((LayoutInflater)this.getSystemService(this.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_layout, null, false);
        listDesPersos.addFooterView(footerView);
        listDesPersos.setClickable(false);
        Toast.makeText(MainActivity.this, R.string.loading, Toast.LENGTH_LONG).show();
        chargerPeople();
    }
    public void chargerPeople (){
        swapiService.listPeoplePage(numeroDePage).enqueue(new Callback<PeopleResult>() {
            @Override
            public void onResponse(final Call<PeopleResult> call, final Response<PeopleResult> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            if(numeroDePage==1){
                                peoples = response.body().getPeoples();
                            }else{
                                peoples.addAll(response.body().getPeoples());
                            }
                            nextPage = response.body().getNextPage();
                            if(nextPage!=null){
                                numeroDePage++;
                                afficherPeople();
                                chargerPeople();
                            }else{
                                afficherPeople();
                                listDesPersos.removeFooterView(footerView);
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(final Call<PeopleResult> call, final Throwable t) {
                t.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, R.string.status_error, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    public void afficherPeople() {
            //findViewById(R.id.loader).setVisibility(View.GONE);
            //texte.setVisibility(View.GONE);
            if(!listDesPersos.isClickable()) {
                adapterPersos = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1);
                listDesPersos.setClickable(true);
                listDesPersos.setOnItemClickListener(onPeopleClick);
                listDesPersos.setAdapter(adapterPersos);
            }
            while(compteurPeople<peoples.size()){
                adapterPersos.add(peoples.get(compteurPeople).name);
                compteurPeople++;
            }

    }

    /**
     * Gère les clics sur les personnages
     */
    public AdapterView.OnItemClickListener onPeopleClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
            Intent intent = new Intent(MainActivity.this,PersoDetailsActivity.class);
            intent.putExtra(PERSO_CHOISI, ""+position);
            startActivity(intent);
        }
    };
}
