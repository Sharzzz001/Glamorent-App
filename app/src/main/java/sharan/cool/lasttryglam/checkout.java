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

public class checkout extends AppCompatActivity {
    ActionBar aBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        TextView totprice;
        Button pay;
        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#6526B3"));
        aBar.setBackgroundDrawable(cd);
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        //Toast.makeText(this,""+currentuser.getEmail(), Toast.LENGTH_SHORT).show();
        String abc = currentuser.getEmail();
        getSupportActionBar().setTitle(abc);

        totprice = (TextView)findViewById(R.id.textView7);

        totprice.setText("Order Amount: "+getIntent().getStringExtra("totprice").toString()+"/-");
        pay = (Button)findViewById(R.id.buttonpay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(checkout.this, "Bought Successfully - Thank You", Toast.LENGTH_LONG).show();
                startActivity(new Intent(checkout.this,boughtby.class));
            }
        });

    }
}