package huo.andr1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VidioActivity extends AppCompatActivity {
    VideoView video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidio);

        video = findViewById(R.id.videoView);
        String videoMsg = getIntent().getStringExtra("videoAdress");
        video.setMediaController(new MediaController(this));
        video.setVideoURI(Uri.parse(videoMsg));
        video.start();
    }
}
