package com.example.firebase_sign_in_up_test.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.firebase_sign_in_up_test.Fragment.InfoFragment;
import com.example.firebase_sign_in_up_test.Fragment.PostFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;

    public ViewPagerAdapter(Context mContext, FragmentManager fm){
        super(fm);
        context=mContext;

    }
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new InfoFragment();
        } else {
            return new PostFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Information";
        }
       else {
            return "Your Post";
        }
        //        } else if (position == 1) {
//            return context.getString(R.string.monthly_title);
//        }
    }

}
