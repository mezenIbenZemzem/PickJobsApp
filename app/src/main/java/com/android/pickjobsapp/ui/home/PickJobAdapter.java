package com.android.pickjobsapp.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.android.pickjobsapp.R;
import com.android.pickjobsapp.databinding.ItemPickjobBinding;
import com.android.pickjobsapp.listner.PickJobsListListner;
import com.android.pickjobsapp.model.PickJob;
import java.util.List;

class PickJobAdapter extends RecyclerView.Adapter<PickJobAdapter.PickJobsViewHolder>  {

    private  List<PickJob> pickJobList;
    private final LayoutInflater layoutInflater;
    private final PickJobsListListner pickJobsListListner;

    public PickJobAdapter(List<PickJob> pickjobsList, Context context , PickJobsListListner pickJobsListListner) {
        this.pickJobList = pickjobsList;
        layoutInflater = LayoutInflater.from(context);
        this.pickJobsListListner = pickJobsListListner;
    }

    @NonNull
    @Override
    public PickJobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPickjobBinding itemPickjobBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_pickjob, parent, false);
        return new PickJobsViewHolder(itemPickjobBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PickJobsViewHolder holder, int position) {
        holder.bindWord(pickJobList.get(position));
    }

    @Override
    public int getItemCount() {
        return pickJobList.size();
    }

    public class PickJobsViewHolder extends RecyclerView.ViewHolder {
        ItemPickjobBinding itemPickjobBinding;
        public PickJobsViewHolder(@NonNull ItemPickjobBinding itemView) {
            super((itemView.getRoot()));
            itemPickjobBinding = itemView;
        }
        public void bindWord(PickJob pickJob) {
            itemPickjobBinding.setPickjob(pickJob);
            itemPickjobBinding.executePendingBindings();
            itemPickjobBinding.pickjobItem.setOnClickListener( view -> pickJobsListListner.onPickJobClick(pickJob));
        }
    }
}
