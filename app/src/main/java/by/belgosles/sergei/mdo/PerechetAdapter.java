package by.belgosles.sergei.mdo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerechetAdapter extends RecyclerView.Adapter<PerechetAdapter.ViewHolder> {

    private int [] diameters;
    private  LayoutInflater inflater;
    static ArrayList<EdittextModelPerechet> masvalues = new ArrayList<>();

    PerechetAdapter(int[] diameters, Context ctx) {
        this.diameters = diameters;
        inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_perechet, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PerechetAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.bind(diameters[position]);

            //методы для считывания значений из полей, и для записи значений из бд

    }

    @Override
    public int getItemCount() {
        return diameters.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textviewDiameter;
        private EditText editTextDel;
        private EditText editTextDrov;

        ViewHolder(View view) {
            super(view);
            textviewDiameter = view.findViewById(R.id.textView_diameter_number);
            editTextDel = view.findViewById(R.id.editText_del);
            editTextDrov = view.findViewById(R.id.editText_drov);
            editTextDel.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    masvalues.get(getAdapterPosition()).setEdittext_del(editTextDel.getText().toString());
                }
                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
            masvalues.get(getAdapterPosition()).setDiameter(textviewDiameter.getText().toString());
            editTextDrov.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    masvalues.get(getAdapterPosition()).setEdittext_drov(editTextDrov.getText().toString());
                }
                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }

        void bind(final int diameter) {
            String t = Integer.toString(diameter);
            textviewDiameter.setText(t);
        }
    }
}
