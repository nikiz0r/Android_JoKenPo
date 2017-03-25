package com.rumeniguenogueira.jokenpo;

import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Button btnPedra, btnPapel, btnTesoura;
    private ImageView ivJogadaPlayer, ivJogadaAndroid;
    private TextView tvResultado;

    private final int PEDRA = 1, PAPEL = 2, TESOURA = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnPedra = (Button)findViewById(R.id.btnPedra);
        btnPapel = (Button)findViewById(R.id.btnPapel);
        btnTesoura = (Button)findViewById(R.id.btnTesoura);
        ivJogadaPlayer = (ImageView)findViewById(R.id.ivJogadaPlayer);
        ivJogadaAndroid = (ImageView)findViewById(R.id.ivJogadaAndroid);
        tvResultado = (TextView)findViewById(R.id.tvResultado);

        btnPedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivJogadaPlayer.setImageDrawable(
                        ContextCompat.getDrawable(GameActivity.this,
                                R.drawable.pedra)
                );

                realizarJogada(PEDRA);
            }
        });

        btnPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivJogadaPlayer.setImageDrawable(
                        ContextCompat.getDrawable(GameActivity.this,
                                R.drawable.papel)
                );

                realizarJogada(PAPEL);
            }
        });

        btnTesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivJogadaPlayer.setImageDrawable(
                        ContextCompat.getDrawable(GameActivity.this,
                                R.drawable.tesoura)
                );

                realizarJogada(TESOURA);
            }
        });
    }


    private void realizarJogada(int jogadaPlayer) {
        Random numAleatorio = new Random();
        int jogadaAndroid = numAleatorio.nextInt(3) + 1;

        MediaPlayer.create(this, R.raw.jokenpo).start();

        switch (jogadaAndroid){
            case PEDRA:
                ivJogadaAndroid.setImageDrawable(
                        ContextCompat.getDrawable(GameActivity.this,
                                R.drawable.pedra)
                );
                switch (jogadaPlayer){
                    case PEDRA:
                        empatou();
                        break;
                    case PAPEL:
                        venceu();
                        break;
                    case TESOURA:
                        perdeu();
                        break;
                }
                break;
            case PAPEL:
                ivJogadaAndroid.setImageDrawable(
                        ContextCompat.getDrawable(GameActivity.this,
                                R.drawable.papel)
                );
                switch (jogadaPlayer){
                    case PEDRA:
                        perdeu();
                        break;
                    case PAPEL:
                        empatou();
                        break;
                    case TESOURA:
                        venceu();
                        break;
                }
                break;
            case TESOURA:
                ivJogadaAndroid.setImageDrawable(
                        ContextCompat.getDrawable(GameActivity.this,
                                R.drawable.tesoura)
                );
                switch (jogadaPlayer){
                    case PEDRA:
                        venceu();
                        break;
                    case PAPEL:
                        perdeu();
                        break;
                    case TESOURA:
                        empatou();
                        break;
                }
                break;
        }
    }

    private void venceu(){
        tvResultado.setText(getString(R.string.vitoria));
    }

    private void empatou(){
        tvResultado.setText(getString(R.string.empate));
    }

    private void perdeu(){
        tvResultado.setText(getString(R.string.derrota));
    }
}
