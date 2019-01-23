package by.belgosles.sergei.mdo.ChangeStatements;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import by.belgosles.sergei.mdo.App;
import by.belgosles.sergei.mdo.CreateStatement;
import by.belgosles.sergei.mdo.Db.AppDb;
import by.belgosles.sergei.mdo.Db.Statement;
import by.belgosles.sergei.mdo.R;

public class RVAdapterChangeStatement extends RecyclerView.Adapter<RVAdapterChangeStatement.ViewHolder> {
    private int [] mas;
    private Context context;

    RVAdapterChangeStatement(int[] mas, Context context){
        this.mas = mas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_statementlist, parent, false), context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.change_statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CreateStatement.class);
                intent.putExtra("message", "change");
              //  intent.putExtra("id", );
                context.startActivity(intent);
                // передать id по котороve необходимо найти в бд записи и заполнить данными макет
            }
        });

        holder.delete_statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // кнопка удалить в ресайклере
            }
        });
    }

    @Override
    public int getItemCount() {
        return mas.length;
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

        /*void bind1 (){
         AppDb db = App.getInstance().getDatabase();
            List<Statement> st = db.getstatementDao().getAll();
            et_forestry.setText(st.get(0).forestry);
            et_date.setText(st.get(0).filling_date);
        }
*/
    }
}
