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

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import com.unknown60.entityencoder.ui.common.AbsActivity;
import com.unknown60.entityencoder.ui.SettingsLicense;
import com.unknown60.entityencoder.ui.SettingsMain;
import android.support.v7.appcompat.R;

public class SettingsActivity extends AbsActivity {

    private Fragment mFragment;
    private int flag;

    public static final String EXTRA_FLAG = "flag";
    public static final int FLAG_MAIN = 0, FLAG_LICENSE = 1, FLAG_GUI = 2, FLAG_NETWORK = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        flag = intent.getIntExtra(EXTRA_FLAG, FLAG_MAIN);

        setContentView(R.layout.activity_settings);

        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("");
    }

    @Override
    public void setUpViews() {

        switch (flag) {
            case FLAG_MAIN:
                mFragment = new SettingsMain();
                break;
            case FLAG_LICENSE:
                mFragment = new SettingsLicense();
                break;
        }
        getFragmentManager().beginTransaction()
            .replace(R.id.container, mFragment)
            .commit();
    }


    public static void launchActivity(Activity mActivity, int flag) {
        Intent intent = new Intent(mActivity, SettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        intent.putExtra(EXTRA_FLAG, flag);
        mActivity.startActivity(intent);
    }

}
