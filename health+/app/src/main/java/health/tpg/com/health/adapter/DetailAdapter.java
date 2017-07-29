package health.tpg.com.health.adapter;

/**
 * Created by rahul.pandey on 7/28/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import health.tpg.com.health.R;
import health.tpg.com.health.pojo.Record;
import health.tpg.com.health.pojo.TreatmentDetails;


public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private List<Record> list;
    private int rowLayout;
    private Context context;
    private ItemClickListner itemClickListner;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Button btnSymtomsReport;
        TextView title;
        TextView predata;
        TextView symtomsdata;
        Button btnCheckReport;
        View view;


        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.createdDate);
            predata = (TextView) v.findViewById(R.id.predata);
            btnCheckReport = (Button) v.findViewById(R.id.checkreport);
            symtomsdata = (TextView) v.findViewById(R.id.symtomsdata);
            btnSymtomsReport = (Button) v.findViewById(R.id.symtomsReport);
            view = v;
        }
    }

    public DetailAdapter(List<Record> items, Context context) {
        list = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getDate());
        if (list.get(position).getPrescription().getDetial() != null) {
            holder.predata.setText(list.get(position).getPrescription().getDetial());
            if (list.get(position).getPrescription().getUrl() != null) {
                holder.btnCheckReport.setVisibility(View.VISIBLE);
            } else {
                holder.btnCheckReport.setVisibility(View.GONE);
            }

            holder.btnCheckReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListner.onItemClickListner(list.get(position).getPrescription().getUrl());
                }
            });
        } else {
            holder.predata.setVisibility(View.GONE);
            holder.btnCheckReport.setVisibility(View.GONE);
        }
        if (list.get(position).getSymptoms().getDetial() != null) {
            holder.symtomsdata.setText(list.get(position).getSymptoms().getDetial());
            if (list.get(position).getSymptoms().getUrl() != null) {
                holder.btnSymtomsReport.setVisibility(View.VISIBLE);
            } else {
                holder.btnSymtomsReport.setVisibility(View.GONE);
            }

            holder.btnSymtomsReport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListner.onItemClickListner(list.get(position).getSymptoms().getUrl());
                }
            });
        } else {
            holder.symtomsdata.setVisibility(View.GONE);
            holder.btnSymtomsReport.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnSingleItemClickListener(ItemClickListner onItemClickListner) {
        this.itemClickListner = onItemClickListner;
    }

    public interface ItemClickListner {

        void onItemClickListner(String caseId);

    }
}