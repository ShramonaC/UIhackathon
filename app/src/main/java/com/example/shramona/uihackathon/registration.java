package com.example.shramona.uihackathon;
        import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class registration extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onClick(View v) {


        Intent intent = new Intent(this, empadd.class);

        startActivity(intent);
      }
}
