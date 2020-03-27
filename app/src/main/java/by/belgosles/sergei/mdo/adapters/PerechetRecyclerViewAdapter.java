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

import java.util.HashMap;
import java.util.Map;

import by.belgosles.sergei.mdo.DiamDelDrov;
import by.belgosles.sergei.mdo.R;

public class PerechetRecyclerViewAdapter extends RecyclerView.Adapter<PerechetRecyclerViewAdapter.ViewHolder> {

    private int[] diameters;
    private LayoutInflater inflater;
    private Map<Integer, DiamDelDrov> maptest = new HashMap<>();
    //private DiamDelDrov instanceItem;

    public PerechetRecyclerViewAdapter(int[] arrayDiam, Context ctx) {
        inflater = LayoutInflater.from(ctx);
        this.diameters = arrayDiam;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.row_diam_del_drov, parent, false), new MyCustomDelEdittextListener(), new MyCustomDrovEditTextListener());
    }

    @Override
    public void onBindViewHolder(@NonNull PerechetRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.myCustomDelEdittextListener.updatePosition(holder.getAdapterPosition());
        holder.myCustomDrovEditTextListener.updatePosition(holder.getAdapterPosition());
        holder.bind(diameters[position]);

    }

    @Override
    public int getItemCount() {
        return diameters.length;
    }

    public Map<Integer,DiamDelDrov> retrieveMap() {
        return maptest;
    }

    public void dataChanged(Map<Integer, DiamDelDrov> map){

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

        void bind(int diameter) {
            textviewDiameter.setText(diameter);
            //editTextDel.setText(diamDelDrovItem.delAmount);
            //editTextDrov.setText(diamDelDrovItem.drovAmount);
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
                if (!maptest.containsKey(position)) {
                    DiamDelDrov newitem = new DiamDelDrov();
                    //newitem.diameter = String.valueOf(diameters[position]);// todo
                    newitem.delAmount = charSequence.toString();
                    maptest.put(position, newitem);
                } else {
                    maptest.get(position).delAmount = charSequence.toString();// todo
                }
              //listDiamDelDrov.get(position).delAmount = charSequence.toString();
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
            //listDiamDelDrov.get(position).drovAmount = charSequence.toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }
}

//https://stackoverflow.com/questions/37096547/how-to-get-data-from-edit-text-in-a-recyclerview