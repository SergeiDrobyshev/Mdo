package by.belgosles.sergei.mdo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListTestesAdapter extends BaseAdapter {
    Context ctx;
    private ArrayList<KindOFTestesTrees> kindOFTestes;
    private LayoutInflater layoutInflater;

    ListTestesAdapter(Context context, ArrayList<KindOFTestesTrees> kindOFTestesTrees) {
        ctx = context;
        kindOFTestes = kindOFTestesTrees;
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return kindOFTestes.size();
    }

    @Override
    public Object getItem(int position) {
        return kindOFTestes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        View view = convertview;
        if(view == null){
            view = layoutInflater.inflate(R.layout.list_item_cannot_be_cutting, viewGroup, false);
        }

        view = layoutInflater.inflate(R.layout.list_item_cannot_be_cutting, viewGroup, false);
        KindOFTestesTrees kind =(KindOFTestesTrees) getItem(position);

        ((TextView)view.findViewById(R.id.list_view_of_testes)).setText(kind.getViewOftestes());
        ((TextView)view.findViewById(R.id.list_deiameter_testes)).setText(kind.getDiameter());
        ((TextView)view.findViewById(R.id.list_amount_testes)).setText(kind.getAmount());
        ((TextView)view.findViewById(R.id.list_number_testes)).setText(kind.getNumber());

        return view;
    }
}
