package sharan.cool.lasttryglam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class cartadapter extends RecyclerView.Adapter<cartadapter.cartviewholder>

{

    ArrayList<cartmodel> cartdatalist;

    public cartadapter(ArrayList<cartmodel> cartdatalist)
    {
        this.cartdatalist = cartdatalist;
    }

    @NonNull
    @Override
    public cartviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowcart, parent,false);
        return new cartviewholder(view);
    }





    @Override
    public void onBindViewHolder(@NonNull cartviewholder holder, int position) {
        holder.name.setText(cartdatalist.get(position).getPname());
        holder.price.setText(cartdatalist.get(position).getPprice());
        Glide.with(holder.img1.getContext()).load(cartdatalist.get(position).getPurl()).into(holder.img1);
    }




    @Override
    public int getItemCount() {
        return cartdatalist.size();
    }



    class cartviewholder extends RecyclerView.ViewHolder
    {
        TextView name, price;
        CircleImageView img1;

        public cartviewholder(@NonNull View itemView) {
            super(itemView);
            name =(TextView) itemView.findViewById(R.id.pname);
            price = (TextView) itemView.findViewById(R.id.pprice);
            img1 = (CircleImageView) itemView.findViewById(R.id.img2);



        }

    }
}
