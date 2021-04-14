package sharan.cool.lasttryglam;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    ArrayList<model> datalist;

    public myadapter(ArrayList<model> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        holder.name.setText(datalist.get(position).getName());
        holder.price.setText("Rs "+datalist.get(position).getPrice());
        //holder.desc.setText("Rs "+datalist.get(position).getDescription());
        //Glide.with(holder.img.getContext()).load(datalist.get(position).getIurl()).into(holder.img);
        //Picasso.get().load(datalist.get(position).getIurl()).into(holder.img);
        //MyGlideApp.with(holder.img.getContext()).load(datalist.get(position).getIurl()).into(holder.img);
        Glide.with(holder.img.getContext()).load(datalist.get(position).getIurl()).into(holder.img);


        //onclick
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.name.getContext(),product_details.class);
                intent.putExtra("uname",datalist.get(position).getName());
                intent.putExtra("uprice",datalist.get(position).getPrice());
                intent.putExtra("uimg",datalist.get(position).getIurl());
                intent.putExtra("udesc",datalist.get(position).getDescription());

                //need to set flags to jump recyclerview
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.name.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,price,desc;
        CircleImageView img;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name = (TextView)itemView.findViewById(R.id.nametext);
            price = (TextView)itemView.findViewById(R.id.emailtext);
            //desc= (TextView)itemView.findViewById(R.id.desctext);


            //add description.
        }
    }
}
