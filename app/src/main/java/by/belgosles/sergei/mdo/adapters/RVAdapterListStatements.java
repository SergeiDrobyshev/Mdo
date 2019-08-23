package by.belgosles.sergei.mdo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import by.belgosles.sergei.mdo.App;
import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.activity.CreateStatement;
import by.belgosles.sergei.mdo.activity.ListStatements;
import by.belgosles.sergei.mdo.model.entity.AppDb;
import by.belgosles.sergei.mdo.model.entity.Fund;

public class RVAdapterListStatements extends RecyclerView.Adapter<RVAdapterListStatements.ViewHolder> {
    private ArrayList<Fund> fundlistcreated;
    private Context context;
    public static final int REQUEST_CODE_CHANGE = 1;
    private ListStatements listStatements = new ListStatements();

    public RVAdapterListStatements(ArrayList<Fund> mas, Context context){
        this.fundlistcreated = mas;
        this.context = context;
    }

    public void dataChanged(ArrayList<Fund> fundlist){
        fundlistcreated.clear();
        fundlistcreated.addAll(fundlist);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_statementlist, parent, false), context);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.bind(fundlistcreated.get(position));
        holder.change_statement.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), CreateStatement.class);
            intent.putExtra(CreateStatement.EXTRA_id_fund , holder.getAdapterPosition());
            intent.putExtra("REQUEST_CODE", REQUEST_CODE_CHANGE);
            // номер порядковый и ид из бд
            context.startActivity(intent);
            //listStatements.statementDetail(view.getContext(), holder.getAdapterPosition(), REQUEST_CODE_CHANGE);// передать id выбранного лесничества для выборк
        });

        holder.delete_statement.setOnClickListener(view -> {
            // кнопка удалить в ресайклере
        });
    }

    @Override
    public int getItemCount() {
        return fundlistcreated.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder  {
        private EditText et_vid, et_forestry, et_date;
        private Button change_statement, delete_statement;

        ViewHolder(View view, Context context) {
            super(view);
            et_vid = view.findViewById(R.id.editText_vid_polzov);
            et_forestry = view.findViewById(R.id.editText_itemrecyclerview_forestry);
            et_date = view.findViewById(R.id.editText_itemrecyclerview_date);
            change_statement = view.findViewById(R.id.itemrecycler_change_statement);
            delete_statement = view.findViewById(R.id.itemrecycler_delete_statement);


        }

        void bind (Fund statement){
            AppDb db = App.getInstance().getDatabase();
            //et_forestry.setText(); достать по ид лесничетсва его название и сетнуть на вью
            //et_date.setText(st.get(0).filling_date); достать дату по ид
        }

    }
}
