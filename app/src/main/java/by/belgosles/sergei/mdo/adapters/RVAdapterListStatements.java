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

import butterknife.BindView;
import butterknife.ButterKnife;
import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.activity.CreateStatementActivity;
import by.belgosles.sergei.mdo.activity.ListStatementsActivity;
import by.belgosles.sergei.mdo.model.entity.Fund;

public class RVAdapterListStatements extends RecyclerView.Adapter<RVAdapterListStatements.ViewHolder> {
    private ArrayList<Fund> fundlistcreated;
    private Context context;
    public static final int REQUEST_CODE_CHANGE = 1;
    private ListStatementsActivity activity;

    public RVAdapterListStatements(ArrayList<Fund> mas, Context context){
        this.fundlistcreated = mas;
        this.context = context;
        this.activity = (ListStatementsActivity) context;
    }

    public void dataChanged(ArrayList<Fund> fundlist){
        fundlistcreated.clear(); // todo change clear? chane update rv
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
            Intent intent = new Intent(view.getContext(), CreateStatementActivity.class);
            intent.putExtra(CreateStatementActivity.EXTRA_id_fund, getFundIdByPosition(position));
            intent.putExtra("REQUEST_CODE", REQUEST_CODE_CHANGE);
            // номер порядковый и ид из бд
            context.startActivity(intent);
            //listStatements.toStatementActivity(view.getContext(), holder.getAdapterPosition(), REQUEST_CODE_CHANGE);// передать id выбранного лесничества для выборк
        });

        holder.delete_statement.setOnClickListener(view1 -> {
           activity.deleteStatementFromDb(getFundIdByPosition(position));
        });
    }

    @Override
    public int getItemCount() {
        return fundlistcreated.size();
    }

    private long getFundIdByPosition(int position){
            return fundlistcreated.get(position).getId_fund();
    }


    static class ViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.editText_vid_polzov) EditText et_vid;
        @BindView(R.id.editText_itemrecyclerview_forestry) EditText et_forestry;
        @BindView(R.id.editText_itemrecyclerview_date) EditText et_date;
        @BindView(R.id.itemrecycler_change_statement) Button change_statement;
        @BindView(R.id.itemrecycler_delete_statement) Button delete_statement;

        ViewHolder(View view, Context context) {
            super(view);
            ButterKnife.bind(this, view);
            et_vid.setKeyListener(null);
            et_forestry.setKeyListener(null);
            et_date.setKeyListener(null);
        }

        void bind (Fund statement){
            if(statement != null) {
                et_forestry.setText(statement.getId_forestry()); //достать по ид лесничетсва его название и сетнуть на вью
                et_date.setText(statement.getFilling_date());//достать дату по ид
            }
        }
    }
}
