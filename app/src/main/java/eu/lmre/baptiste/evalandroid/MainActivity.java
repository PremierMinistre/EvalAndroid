package eu.lmre.baptiste.evalandroid;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import retrofit2.Retrofit;
//import retrofit2.http.GET;
//import android.os.AsyncTask;
//import android.os.Handler.Callback;
//import retrofit2.http.Path;
import retrofit2.Call;
import com.google.gson.Gson;

import java.util.Collection;
import java.util.List;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
//import android.os.AsyncTask;
//import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;//Pour debug
//import java.util.List;

import retrofit2.Callback;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listDesPersos = findViewById(R.id.listDesPersos);
        texte = findViewById(R.id.noDeviceFound);
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
                                chargerPeople();
                            }else{
                                afficherPeople();
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

            findViewById(R.id.loader).setVisibility(View.GONE);
            texte.setVisibility(View.GONE);

            ArrayAdapter<String> adapterPersos = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1);
            listDesPersos.setAdapter(adapterPersos);
            for(int i=0; i<peoples.size();i++){
                adapterPersos.add(peoples.get(i).name);
            }
            listDesPersos.setClickable(true);
            listDesPersos.setOnItemClickListener(onPeopleClick);
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
