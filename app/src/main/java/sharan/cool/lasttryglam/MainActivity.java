package sharan.cool.lasttryglam;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//This is the registration page
public class MainActivity extends AppCompatActivity
{
    TextInputLayout t1,t2;
    ProgressBar bar;
    FirebaseAuth mAuth;
    VideoView video1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        t1=(TextInputLayout)findViewById(R.id.email);
        t2=(TextInputLayout)findViewById(R.id.pwd);
        bar=(ProgressBar)findViewById(R.id.progressBar3);
        mAuth = FirebaseAuth.getInstance();
        video1 = (VideoView)findViewById(R.id.video1);
        String path = "android.resource://sharan.cool.lasttryglam/"+R.raw.glomologin;
        Uri u = Uri.parse(path);
        video1.setVideoURI(u);
        video1.start();

        video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

    }

    @Override
    protected void onResume(){
        video1.resume();
        super.onResume();

    }

    @Override
    protected void onPause(){
        video1.suspend();
        super.onPause();

    }

    @Override
    protected void onDestroy(){
        video1.stopPlayback();
        super.onDestroy();

    }


    public void gotosignin(View view)
    {
        startActivity(new Intent(MainActivity.this,login.class));
    }

    public void singup(View view)
    {
        bar.setVisibility(View.VISIBLE);

        String email=t1.getEditText().getText().toString();
        String password=t2.getEditText().getText().toString();

        if(email.isEmpty()==false && password.length()>=8)
        {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                bar.setVisibility(View.INVISIBLE);
                                t1.getEditText().setText("");
                                t2.getEditText().setText("");
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                            } else
                            {
                                bar.setVisibility(View.INVISIBLE);
                                t1.getEditText().setText("");
                                t2.getEditText().setText("");
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                            }

                            // ...
                        }
                    });

        }
        else
        {
            bar.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Please input valid details",Toast.LENGTH_LONG).show();
        }
    }


}