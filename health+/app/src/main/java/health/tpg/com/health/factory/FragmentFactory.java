package health.tpg.com.health.factory;

import android.app.Fragment;
import android.os.Parcelable;

import health.tpg.com.health.enums.FragmentTag;


public class FragmentFactory {

    public static Fragment getFragment(FragmentTag fragmentTag, Parcelable serializableArgs) {

        Fragment fragment = null;


        switch (fragmentTag.getModule()) {

        }

        return fragment;

    }
}
