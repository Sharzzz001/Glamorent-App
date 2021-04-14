package sharan.cool.lasttryglam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class cartpage extends AppCompatActivity {
    ActionBar aBar;
    RecyclerView cartrecycler;
    ArrayList<cartmodel> cartdatalist;
    FirebaseFirestore db;
    cartadapter cadapter;
    Button abcd, carttotal;
    String total1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView totalprice;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartpage);

        cartrecycler = (RecyclerView)findViewById(R.id.cartrecview);
        cartrecycler.setLayoutManager(new LinearLayoutManager(this));
        cartdatalist=new ArrayList<>();
        cadapter = new cartadapter(cartdatalist);
        cartrecycler.setAdapter(cadapter);
        carttotal = (Button)findViewById(R.id.carttotal);


        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        //Toast.makeText(this,""+currentuser.getEmail(), Toast.LENGTH_SHORT).show();
        String abc = currentuser.getEmail();

        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#6526B3"));
        aBar.setBackgroundDrawable(cd);

        getSupportActionBar().setTitle(abc);

        totalprice = (TextView)findViewById(R.id.ordertotal);



        db=FirebaseFirestore.getInstance();
        db.collection("cart").whereEqualTo("name", abc).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    int total=0;


                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){



                            total+=Integer.parseInt(d.getString("pprice").toString());
                            cartmodel obj=d.toObject(cartmodel.class);
                            cartdatalist.add(obj);


                        }

                        total1 = String.valueOf(total);
                        totalprice.setText("Total Amount: "+total1+" Rs");

                        //update adapter
                        cadapter.notifyDataSetChanged();

                    }

                });
        abcd=(Button)findViewById(R.id.cartdel);
        abcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("cart").whereEqualTo("name",abc).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List <DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                for (DocumentSnapshot d:list){

                                    db.collection("cart").document(d.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(cartpage.this, "Item Deleted", Toast.LENGTH_SHORT).show();

                                        }
                                    });

                                }
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });
            }
        });

        carttotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cartpage.this,checkout.class);
                intent.putExtra("totprice",total1);
                startActivity(intent);
            }
        });











    }
}