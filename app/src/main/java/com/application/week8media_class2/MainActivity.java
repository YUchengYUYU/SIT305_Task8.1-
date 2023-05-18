package com.application.week8media_class2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    PlayerView playerView;
    Button playButton;
    Button addButton;
    Button listButton;
    EditText urlInput;
    ExoPlayer exoPlayer;
    SharedPreferences sharedPref;
    private Boolean playWhenReady = true;
    private int currentItem = 0;
    private long playbackPosition = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = findViewById(R.id.play_button);
        addButton = findViewById(R.id.add_button);
        listButton = findViewById(R.id.list_button);
        urlInput = findViewById(R.id.url_input);

        sharedPref = getSharedPreferences("URL_LIST", MODE_PRIVATE);

        exoPlayer = new ExoPlayer.Builder(this).build();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                EditText editText = findViewById(R.id.url_input);
                String url = editText.getText().toString();
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlInput.getText().toString();
                if (!url.isEmpty()) {
                    Set<String> urlList = sharedPref.getStringSet("urlList", new HashSet<>());
                    urlList.add(url);
                    sharedPref.edit().putStringSet("urlList", urlList).apply();
                }
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }
}
