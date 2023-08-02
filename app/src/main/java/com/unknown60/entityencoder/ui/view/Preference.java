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
package com.unknown60.entityencoder.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.support.v4.R;

public class Preference extends android.preference.Preference {

    private boolean _isInitialized = false;

    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        setLayoutResource(R.layout.custom_preference);
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    public Preference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!_isInitialized) {
            _isInitialized = true;
            init(context, attrs, defStyleAttr, defStyleRes);
        }
    }

    public Preference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!_isInitialized) {
            _isInitialized = true;
            init(context, attrs, defStyleAttr, 0);
        }
    }

    public Preference(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!_isInitialized) {
            _isInitialized = true;
            init(context, attrs, 0, 0);
        }
    }

    public Preference(Context context) {
        super(context);
        if (!_isInitialized) {
            _isInitialized = true;
            init(context, null, 0, 0);
        }
    }

}
