package sharan.cool.lasttryglam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import sharan.cool.lasttryglam.MainActivity;
import sharan.cool.lasttryglam.R;

public class userprofile extends AppCompatActivity {
    ActionBar aBar;
    TextView useremail,uidhome;
    Button deluser, logoutuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        String abc = currentuser.getEmail();
        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#6526B3"));
        aBar.setBackgroundDrawable(cd);

        getSupportActionBar().setTitle(abc);

        uidhome=(TextView)findViewById(R.id.uidhome);
        useremail=(TextView)findViewById(R.id.emailuser);
        useremail.setText("Email: "+abc);
        uidhome.setText("UID: "+currentuser.getUid());

        deluser = (Button) findViewById(R.id.deluser);
        deluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userprofile.this, MainActivity.class);
                startActivity(i);
            }
        });




    }
    public void logoutprocess(View view)
    {
        //FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(userprofile.this,MainActivity.class));
    }
}