/* 
* This file is part of the Entity Encoder distribution (https://github.com/Unknown-60/EntityEncoder).
* Copyright (c) 2023 Unknown-60.
* 
* This program is free software: you can redistribute it and/or modify  
* it under the terms of the GNU General Public License as published by  
* the Free Software Foundation, version 3.
*
* This program is distributed in the hope that it will be useful, but 
* WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
* General Public License for more details.
*
* You should have received a copy of the GNU General Public License 
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/
package com.unknown60.entityencoder.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.unknown60.entityencoder.ui.SettingsActivity;
import com.unknown60.entityencoder.ui.view.Preference;
import android.support.coreutils.R;

public class SettingsMain extends PreferenceFragment implements Preference.OnPreferenceClickListener {

	private Preference mVersionPref;
	private Preference mLicensePref;
	private Preference mGithubPref;
    private Preference mUnknown60Pref;
    private Preference mKarenPref;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings_main);

		mVersionPref = (Preference) findPreference("version");
		mLicensePref = (Preference) findPreference("license");
        mGithubPref = (Preference) findPreference("github");
        mUnknown60Pref = (Preference) findPreference("unknown60");
		mKarenPref = (Preference) findPreference("karen");

		String version = "Unknown";
		try {
			version = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
			version += " (" + getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionCode + ")";
		} catch (Exception e) {

		}
		mVersionPref.setSummary(version);

		mLicensePref.setOnPreferenceClickListener(this);
		mGithubPref.setOnPreferenceClickListener(this);
        mUnknown60Pref.setOnPreferenceClickListener(this);
	}

	@Override
	public boolean onPreferenceClick(android.preference.Preference pref) {
		if (pref == mLicensePref) {
			SettingsActivity.launchActivity(getActivity(), SettingsActivity.FLAG_LICENSE);
			return true;
		}
		
        if (pref == mGithubPref) {
            openWebUrl("https://www.github.com/Unknown-60/EntityEncoder");
		}
        
		if (pref == mUnknown60Pref) {
			openWebUrl("https://www.github.com/Unknown-60");
			return true;
		}
		
		return false;
	}

	private void openWebUrl(String url) {
		Uri uri = Uri.parse(url);
		startActivity(new Intent(Intent.ACTION_VIEW, uri));
	}

}
