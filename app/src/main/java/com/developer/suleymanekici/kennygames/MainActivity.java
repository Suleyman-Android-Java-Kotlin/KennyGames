package com.developer.suleymanekici.kennygames;

import android.content.DialogInterface;
import android.opengl.Visibility;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView puanDikey;
    TextView saniyeDikey;
    ImageView kenny1Dikey;
    ImageView kenny2Dikey;
    ImageView kenny3Dikey;
    ImageView kenny4Dikey;
    ImageView kenny5Dikey;
    ImageView kenny6Dikey;
    ImageView kenny7Dikey;
    ImageView kenny8Dikey;
    ImageView kenny9Dikey;
    Handler handler;
    Runnable runnable;

    int score;

    ImageView [] kennyImgArrayDikey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kenny1Dikey = (ImageView) findViewById(R.id.kenny1Dikey);
        kenny2Dikey = (ImageView) findViewById(R.id.kenny2Dikey);
        kenny3Dikey = (ImageView) findViewById(R.id.kenny3Dikey);
        kenny4Dikey = (ImageView) findViewById(R.id.kenny4Dikey);
        kenny5Dikey = (ImageView) findViewById(R.id.kenny5Dikey);
        kenny6Dikey = (ImageView) findViewById(R.id.kenny6Dikey);
        kenny7Dikey = (ImageView) findViewById(R.id.kenny7Dikey);
        kenny8Dikey = (ImageView) findViewById(R.id.kenny8Dikey);
        kenny9Dikey = (ImageView) findViewById(R.id.kenny9Dikey);

        kennyImgArrayDikey = new ImageView[]{
                kenny1Dikey,
                kenny2Dikey,
                kenny3Dikey,
                kenny4Dikey,
                kenny5Dikey,
                kenny6Dikey,
                kenny7Dikey,
                kenny8Dikey,
                kenny9Dikey };
        hideImages();

        score = 0;

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                saniyeDikey = (TextView) findViewById(R.id.saniyeDikey);
                saniyeDikey.setText("Saniye " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                saniyeDikey = (TextView) findViewById(R.id.saniyeDikey);
                saniyeDikey.setText("Game Over ");
                kenny1Dikey.setVisibility(View.INVISIBLE);
                kenny2Dikey.setVisibility(View.INVISIBLE);
                kenny3Dikey.setVisibility(View.INVISIBLE);
                kenny4Dikey.setVisibility(View.INVISIBLE);
                kenny5Dikey.setVisibility(View.INVISIBLE);
                kenny6Dikey.setVisibility(View.INVISIBLE);
                kenny7Dikey.setVisibility(View.INVISIBLE);
                kenny8Dikey.setVisibility(View.INVISIBLE);
                kenny9Dikey.setVisibility(View.INVISIBLE);
                resetGame();
                handler.removeCallbacks(runnable);
            }
        }.start();
    }

    public void resetGame() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Game Over");
        alert.setMessage("Tebrikler Topam Scorunuz: " + score + " Tekrar Oynamak ister misin ? " );
        alert.setPositiveButton("Evet, Lütfen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // bug olmaması için oyunu tekrar başlatıyorum
                finish();
                startActivity(getIntent());
            }
        });

        alert.setNegativeButton("Hayır, oyundan çık", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Oyunu Kapata Bilirsiniz.",Toast.LENGTH_LONG).show();
                System.exit(0);
            }
        });
        alert.show();
    }

    public void kennySkor(View view) {
        puanDikey = (TextView) findViewById(R.id.puanDikey);
        score++;
        puanDikey.setText("Puan:  " + score);
    }


    public void hideImages() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : kennyImgArrayDikey) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random r = new Random();
                int i = r.nextInt(8-0);
                kennyImgArrayDikey[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 600);
            }
        };
        handler.post(runnable);
    }
}

