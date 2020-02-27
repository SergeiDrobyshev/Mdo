package by.belgosles.sergei.mdo.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import by.belgosles.sergei.mdo.DiamDelDrov;
import by.belgosles.sergei.mdo.R;

public class PerechetRecyclerViewAdapter extends RecyclerView.Adapter<PerechetRecyclerViewAdapter.ViewHolder> {

    private ArrayList<DiamDelDrov> listDiamDelDrov;
    private LayoutInflater inflater;

    public PerechetRecyclerViewAdapter(ArrayList<DiamDelDrov> listDiamDelDrov, Context ctx) {
        inflater = LayoutInflater.from(ctx);
        this.listDiamDelDrov = listDiamDelDrov;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_perechet, parent, false), new MyCustomDelEdittextListener(), new MyCustomDrovEditTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull PerechetRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.myCustomDelEdittextListener.updatePosition(holder.getAdapterPosition());
        holder.myCustomDrovEditTextListener.updatePosition(holder.getAdapterPosition());
        holder.bind(listDiamDelDrov.get(position));

    }

    @Override
    public int getItemCount() {
        return listDiamDelDrov.size();
    }

    public ArrayList<DiamDelDrov> retrieveList() {
        return listDiamDelDrov;
    }

    public void dataChanged(ArrayList<DiamDelDrov> list){
        listDiamDelDrov.clear();
        listDiamDelDrov.addAll(list); //todo clear all lists, and addall add empty list, then recyclerview become invisible
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textviewDiameter;
        private EditText editTextDel;
        private EditText editTextDrov;
        MyCustomDelEdittextListener myCustomDelEdittextListener;
        MyCustomDrovEditTextListener myCustomDrovEditTextListener;

        ViewHolder(View view, MyCustomDelEdittextListener myCustomDelEdittextListener, MyCustomDrovEditTextListener myCustomDrovEditTextListener) {
            super(view);
            textviewDiameter = view.findViewById(R.id.textView_diameter_number);
            editTextDel = view.findViewById(R.id.editText_del);
            editTextDrov = view.findViewById(R.id.editText_drov);
            this.myCustomDelEdittextListener = myCustomDelEdittextListener;
            this.myCustomDrovEditTextListener = myCustomDrovEditTextListener;
            editTextDel.addTextChangedListener(myCustomDelEdittextListener);
            editTextDrov.addTextChangedListener(myCustomDrovEditTextListener);
        }

        void bind(DiamDelDrov diamDelDrovItem) {
            textviewDiameter.setText(diamDelDrovItem.diameter);
            editTextDel.setText(diamDelDrovItem.delAmount);
            editTextDrov.setText(diamDelDrovItem.drovAmount);
        }
    }

    private class MyCustomDelEdittextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
              listDiamDelDrov.get(position).delAmount = charSequence.toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

    private  class MyCustomDrovEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            listDiamDelDrov.get(position).drovAmount = charSequence.toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }
}

//https://stackoverflow.com/questions/37096547/how-to-get-data-from-edit-text-in-a-recyclerview