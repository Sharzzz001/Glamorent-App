package sharan.cool.lasttryglam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class dashboard extends AppCompatActivity
{
    ActionBar aBar;

    RecyclerView recview;
    ArrayList<model> datalist;
    FirebaseFirestore db;
    myadapter adapter;
    Button userprofile,cartbtn;
    FirebaseAuth mauth;
    TextView emailhome,uidhome, unamet;
    BottomNavigationView bad;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#6526B3"));
        aBar.setBackgroundDrawable(cd);

        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        String abc = currentuser.getEmail();


        getSupportActionBar().setTitle(abc);


        String email = getIntent().getStringExtra("email").toString();


        emailhome=(TextView)findViewById(R.id.textView3);
        //uidhome=(TextView)findViewById(R.id.uidhome);
        emailhome.setText("Welcome "+getIntent().getStringExtra("email").toString());
        //uidhome.setText("UID :"+getIntent().getStringExtra("uid").toString());

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        datalist = new ArrayList<>();
        adapter = new myadapter(datalist);
        recview.setAdapter(adapter);

        //Query.Dirction.Descending === If you need to sort in descending order.
        db=FirebaseFirestore.getInstance();
        db.collection("clothes").orderBy("name").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list)
                        {
                            model obj=d.toObject(model.class);
                            datalist.add(obj);
                        }
                        //update adapter
                        adapter.notifyDataSetChanged();
                    }
                });

        //profile view
        mauth = FirebaseAuth.getInstance();
        userprofile = (Button)findViewById(R.id.profilebtn);
        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this,userprofile.class);
                startActivity(intent);
            }
        });
        //cart btn
        cartbtn = (Button)findViewById(R.id.cartbtn);
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this, cartpage.class));
            }
        });
        //navigation bar


    }





    public void logoutprocess(View view)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(dashboard.this,MainActivity.class));
    }
}