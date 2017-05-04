package cz.kerslager.android.bestscore;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

// Program generuje náhodná čísla a zapamatuje si nejvyšší (do SharedPreferences)
// Nastavení

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private final static String TOP_SCORE = "top_score";
    private TextView textViewAktSkore;
    private TextView textViewMaxSkore;
    private Random r;
    private int nejvyssiSkore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewAktSkore = (TextView) findViewById(R.id.textViewAktSkore);
        textViewMaxSkore = (TextView) findViewById(R.id.textViewMaxSkore);
        // obnovíme uložené maximální skore z nastavení programu (default je nula)
        pref = getPreferences(MODE_PRIVATE);
        textViewMaxSkore.setText(String.valueOf(pref.getInt(TOP_SCORE, 0)));
        r = new Random();
    }

    // metoda pro uložení nastavení programu do SharedPreferences
    private void ulozNastaveni(String key, int value) {
        SharedPreferences.Editor e = pref.edit();
        e.putInt(key, value);
        e.apply();
    }

    public void onKlik(View v) {
        switch (v.getId()) {
            case R.id.buttonHraj:
                int skore = r.nextInt(1000);
                if (skore > nejvyssiSkore) {
                    nejvyssiSkore = skore;
                    textViewMaxSkore.setText(String.valueOf(skore));
                    ulozNastaveni(TOP_SCORE, skore);
                }
                textViewAktSkore.setText(String.valueOf(skore));
                break;
            case R.id.buttonReset:
                nejvyssiSkore = 0;
                textViewMaxSkore.setText("0");
                textViewAktSkore.setText("0");
                ulozNastaveni(TOP_SCORE, 0);
                break;
        }
    }
}
