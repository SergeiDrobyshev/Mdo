package by.belgosles.sergei.mdo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.adapters.MPagerAdapter;
import by.belgosles.sergei.mdo.fragments.CanNotCutFragment;
import by.belgosles.sergei.mdo.fragments.EnumFragment;
import by.belgosles.sergei.mdo.fragments.StatementMdoFragment;
import by.belgosles.sergei.mdo.fragments.UndegrowthPollutionFragment;

public class StatementInfoActivity extends AppCompatActivity {
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabs;
    @BindView(R.id.saveInfoActivity) Button saveInfoActivity;
    private  Bundle bundle;
    private long id_fund;
    private MPagerAdapter sectionsPagerAdapter;
    //private AppDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement_info);
        ButterKnife.bind(this);
        getFundId();
        sectionsPagerAdapter = new MPagerAdapter(this, getSupportFragmentManager(), id_fund);
        viewPager.setOffscreenPageLimit(MPagerAdapter.TAB_TITLES.length);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
        //db = App.getInstance().getDatabase();
        viewPager.getId();
        // при создании новой ведомости сначала инсенртнуть пустую стркоу в базу, потом взять ее id, и по этому id обновить данные
    }

    //id выбранной или созданной ведомости
    private void getFundId() {
        bundle = getIntent().getExtras();
        if(bundle != null) {
            id_fund = bundle.getLong(CreateStatementActivity.EXTRA_id_fund);
            //todo проверить выбор ид в списке при нажатии
        }
    }

    // при сохранении все данные с фрагментов обновятся в бд
    private void setDataFragments (long id_fund){
        StatementMdoFragment fragment = (StatementMdoFragment) sectionsPagerAdapter.instantiateItem(viewPager,0);
        fragment.saveMDOValues(id_fund);

        EnumFragment enumfragment = (EnumFragment) sectionsPagerAdapter.instantiateItem(viewPager,1);
        enumfragment.saveEnumValues(id_fund);

        UndegrowthPollutionFragment undegrowthPollutionFragment = (UndegrowthPollutionFragment) sectionsPagerAdapter.instantiateItem(viewPager,2);

        CanNotCutFragment canNotFragment = (CanNotCutFragment) sectionsPagerAdapter.instantiateItem(viewPager,3);
    }

    @OnClick(R.id.saveInfoActivity)
    public void onClick() {
        Intent intent = new Intent();
        setDataFragments(id_fund);
        setResult(RESULT_OK, intent);
    }
}