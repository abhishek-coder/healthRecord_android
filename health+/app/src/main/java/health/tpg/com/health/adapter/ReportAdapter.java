package health.tpg.com.health.adapter;

/**
 * Created by rahul.pandey on 7/28/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import health.tpg.com.health.R;
import health.tpg.com.health.pojo.TreatmentDetails;


public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    private List<TreatmentDetails> list;
    private int rowLayout;
    private Context context;
    private onItemClickListner itemClickListner;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        TextView doctor_Name;
        View view;


        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            date = (TextView) v.findViewById(R.id.date);
            doctor_Name = (TextView) v.findViewById(R.id.doctor);
            view = v;
        }
    }

    public ReportAdapter(List<TreatmentDetails> items,Context context) {
        list = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        String string = list.get(position).getDate();
        DateFormat format = new SimpleDateFormat("dd-M-yyyy");
        Date date = null;
        try {
            date = format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        holder.date.setText(date.toString());
        holder.doctor_Name.setText(list.get(position).getDoctor_name());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListner.onItemClickListner(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setOnSingleItemClickListener(onItemClickListner onItemClickListner) {
        this.itemClickListner = onItemClickListner;
    }

    public interface onItemClickListner{

        void onItemClickListner(String caseId);

    }
}