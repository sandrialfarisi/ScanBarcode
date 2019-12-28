package id.devcamp.scanbarcode.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.devcamp.scanbarcode.R;
import id.devcamp.scanbarcode.apihelper.UserData;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<UserData> dataModelArrayList;

    public RetrofitAdapter(Context ctx, ArrayList<UserData> dataModelArrayList){
        this.mContext = ctx;
        this.dataModelArrayList = dataModelArrayList;
    }



    @NonNull
    @Override
    public RetrofitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.matkul_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RetrofitAdapter.MyViewHolder holder, int position) {
        holder.namaMatkul.setText(dataModelArrayList.get(position).getNama_matkul());
        holder.hari.setText(dataModelArrayList.get(position).getHari());
        holder.kodeMatkul.setText(dataModelArrayList.get(position).getKode_matkul());
        holder.jamMulai.setText(dataModelArrayList.get(position).getJam_mulai());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView namaMatkul, hari, kodeMatkul, jamMulai;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            namaMatkul = itemView.findViewById(R.id.tvNamaMatkul);
            hari = itemView.findViewById(R.id.tvHari);
            kodeMatkul = itemView.findViewById(R.id.tvKodeMatkul);
            jamMulai = itemView.findViewById(R.id.tvJamMulai);

        }
    }
}
