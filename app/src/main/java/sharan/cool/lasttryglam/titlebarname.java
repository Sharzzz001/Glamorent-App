package sharan.cool.lasttryglam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class titlebarname extends AppCompatActivity {
    TextView myname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titlebarname);
        myname = (TextView)findViewById(R.id.mytext);
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        //Toast.makeText(this,""+currentuser.getEmail(), Toast.LENGTH_SHORT).show();
        String abc = currentuser.getEmail();
        myname.setText(abc);
    }
}