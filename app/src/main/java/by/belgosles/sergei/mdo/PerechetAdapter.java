package by.belgosles.sergei.mdo;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class PerechetAdapter extends RecyclerView.Adapter<PerechetAdapter.ViewHolder> {

    private int [] diameters;
    public String [] masvalues;

    PerechetAdapter(int[] diameters) {
        this.diameters = diameters;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_perechet, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PerechetAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.bind(diameters[position]);
            //masvalues[position] = holder.readValues(diameters[position]);
            //методы для считывания значений из полей, и для записи значений из бд

    }

    @Override
    public int getItemCount() {
        return diameters.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textviewDiameter;
        private EditText editTextDel;
        private EditText editTextDrov;
        private String [] mas;

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

        String[] readValues(int diameter){
            mas[0] = String.valueOf(diameter);
            mas[1] = editTextDel.getText().toString();
            mas[2] = editTextDrov.getText().toString();

            return mas;
        }
    }
}
