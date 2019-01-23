package by.belgosles.sergei.mdo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAddedSpeciesAdapter extends BaseAdapter {
    Context ctx;
    private ArrayList<String> addedSpecies;
    private LayoutInflater layoutInflater;

    public ListAddedSpeciesAdapter(Context context, ArrayList<String> addedSpecies) {
        this.ctx = context;
        this.addedSpecies = addedSpecies;
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return addedSpecies.size();
    }

    @Override
    public Object getItem(int position) {
        return addedSpecies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        View view = convertview;
        if(view == null){
            view = layoutInflater.inflate(R.layout.item_listview_added_species, viewGroup, false);
        }
        //view = layoutInflater.inflate(R.layout.item_listview_added_species, viewGroup, false);
        String species =(String) getItem(position);
        ((TextView)view.findViewById(R.id.textview_added_species)).setText(species);
        return view;
    }
}
