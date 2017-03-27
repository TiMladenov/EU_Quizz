package io.github.timladenov.euquizz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public String playerNamesMain = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerNamesMain = getIntent().getExtras().getString("playerNames");
//        Log.v("playerNamesMain", playerNamesMain);
    }

    public void answer0(View view){

        ;

    }
}
