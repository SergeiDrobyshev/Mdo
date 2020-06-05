package by.belgosles.sergei.mdo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.model.DictName;


public class DictSpinnerAdapter extends ArrayAdapter<DictName> {

    private LayoutInflater inflater;
    private ArrayList<DictName> addedValues;
    private Context ctx;
    private int resId;

    public  DictSpinnerAdapter(Context context, int resId, ArrayList<DictName> addedValues) {
        super(context, resId, addedValues);

        this.addedValues = addedValues;
        this.ctx = context;
        this.inflater = LayoutInflater.from(context);
        this.resId = resId;
        this.setDropDownViewResource(R.layout.spinner_dropdown_item);
    }

    @Override
    public int getCount() {
        return addedValues.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    public DictSpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        final View view = inflater.inflate(resId, parent, false);
        TextView tv = (TextView) view.findViewById(R.id.tv_spinner_item);

        DictName rowitem = addedValues.get(position);
        tv.setText(rowitem.getValue());
        tv.setTag(rowitem.getId());

        return view;
    }





}
