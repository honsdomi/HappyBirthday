package com.hons.happybirthday;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.hons.happybirthday.domain.entity.Birthday;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by Dominik on 07.01.2018.
 */

public class SettingsFragment extends PreferenceFragment {
    private static final String PREF_KEY_SORT = "pref_sort";
    private static final String SORT_BY_NAME = "0";
    private static final String SORT_BY_DATE = "1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

    public static Comparator<Birthday> getSortByComparator(SharedPreferences settings) {
        String sortBy = settings.getString(PREF_KEY_SORT, SORT_BY_NAME);

        switch (sortBy) {
            case SORT_BY_DATE:
                return new Comparator<Birthday>() {
                    @Override
                    public int compare(Birthday o1, Birthday o2) {
                        Date d1 = new Date(o1.getYear(), o1.getMonth() - 1, o1.getDay());
                        Date d2 = new Date(o2.getYear(), o2.getMonth() - 1, o2.getDay());
                        return d1.compareTo(d2);
                    }
                };

            case SORT_BY_NAME:
            default:
                return new Comparator<Birthday>() {
                    @Override
                    public int compare(Birthday o1, Birthday o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                };
        }
    }

}
