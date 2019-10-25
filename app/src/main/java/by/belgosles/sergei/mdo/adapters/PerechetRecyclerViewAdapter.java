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

import by.belgosles.sergei.mdo.EdittextModelPerechet;
import by.belgosles.sergei.mdo.R;

public class PerechetRecyclerViewAdapter extends RecyclerView.Adapter<PerechetRecyclerViewAdapter.ViewHolder> {

    private int [] diameters;
    private  LayoutInflater inflater;
    public static ArrayList<EdittextModelPerechet> masvalues;

    public PerechetRecyclerViewAdapter(int[] diameters, Context ctx) {
         masvalues = new ArrayList<>();
        this.diameters = diameters;
        inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_perechet, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PerechetRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
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

        }

        void bind(final int diameter) {
            String t = Integer.toString(diameter);
            textviewDiameter.setText(t);
        }
    }
}

//https://stackoverflow.com/questions/37096547/how-to-get-data-from-edit-text-in-a-recyclerview