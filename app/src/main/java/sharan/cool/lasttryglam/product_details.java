package sharan.cool.lasttryglam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class product_details extends AppCompatActivity {

    ActionBar aBar;
    TextView name,price,desc;
    ImageView img;
    Button atc;

    FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        //Toast.makeText(this,""+currentuser.getEmail(), Toast.LENGTH_SHORT).show();
        String abc = currentuser.getEmail();
        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#6526B3"));
        aBar.setBackgroundDrawable(cd);

        getSupportActionBar().setTitle(abc);


       //String uemail= mauth.getCurrentUser().getEmail();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        name = (TextView) findViewById(R.id.prodname);
        price = (TextView) findViewById(R.id.prodprice);
        desc = (TextView) findViewById(R.id.proddesc);
        img = (ImageView)findViewById(R.id.prodimage);
        atc = (Button) findViewById(R.id.addtocartbtn);
        fstore=FirebaseFirestore.getInstance();


        name.setText(getIntent().getStringExtra("uname").toString());
        price.setText("Price: Rs "+getIntent().getStringExtra("uprice").toString());
        Glide.with(this)
                .load(getIntent().getStringExtra("uimg").toString()).into(img);

        desc.setText(getIntent().getStringExtra("udesc").toString());


        //addtocart

        atc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object>cart=new HashMap<>();
                cart.put("name",abc);
                cart.put("pname",getIntent().getStringExtra("uname").toString());
                cart.put("pprice",getIntent().getStringExtra("uprice").toString());
                cart.put("purl",getIntent().getStringExtra("uimg").toString());

                fstore.collection("cart").document().set(cart).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(product_details.this,getIntent().getStringExtra("uname").toString()+" added to cart",Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(product_details.this,cartpage.class);
//                        startActivity(intent);
                    }
                });
//                Intent intent = new Intent(product_details.this,login.class);
//                startActivity(intent);

            }
        });

    }
}