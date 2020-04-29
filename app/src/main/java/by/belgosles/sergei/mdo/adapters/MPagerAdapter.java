package by.belgosles.sergei.mdo.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.fragments.CanNotCutFragment;
import by.belgosles.sergei.mdo.fragments.EnumFragment;
import by.belgosles.sergei.mdo.fragments.UndegrowthPollutionFragment;
import by.belgosles.sergei.mdo.fragments.StatementMdoFragment;

/*
adapter for viewpager on StatementInfoActivity
 */
public class MPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    public static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.podrost, R.string.not_cut};
    private final Context mContext;
    private long id_fund;

    public MPagerAdapter(Context context, FragmentManager fm, long id) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        id_fund = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case  0:
                return StatementMdoFragment.newInstance(id_fund);
            case 1:
                return EnumFragment.newInstance(id_fund);
            case 2:
                //todo change to .newInstance
                return new UndegrowthPollutionFragment();
            case 3:
                return CanNotCutFragment.newInstance();
                default:return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment createdFrag = (Fragment) super.instantiateItem(container, position);
        switch (position){
            case 0:
                String firstTag = createdFrag.getTag();
                break;
            case 1:
                String secondTag = createdFrag.getTag();
                break;
            case  2:
                String thirdFrag = createdFrag.getTag();
                break;
            case 3:
                String forthFrag = createdFrag.getTag();
                break;
        }

        return createdFrag;
    }

    /*@Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
    }*/
}