package com.example.anna.myapplication;

import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LongSparseArray<Person> persons;
    private OnClickListener onClickListener;

    DataAdapter(OnClickListener onClickListener) {
        this.persons = new LongSparseArray<>();
        this.onClickListener = onClickListener;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder holder, final int position) {
        final Person person = persons.get(position);
        holder.imageView.setImageResource(person.getImageRes());
        holder.nameView.setText(person.getName());
        holder.itemView.setOnClickListener(v -> onClickListener.onClick(person.getId()));
    }

    public void setPersons(LongSparseArray<Person> persons) {
        this.persons = persons;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    interface OnClickListener {
        void onClick(long personId);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView;

        private ViewHolder(final View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.person_image);
            nameView = itemView.findViewById(R.id.person_name);
        }
    }
}