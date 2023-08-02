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

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.support.v4.R;

public class SettingsLicense extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle) {
		WebView webView = new WebView(inflater.getContext());
		webView.loadUrl("file:///android_asset/licenses.html");

		return webView;
	}
}
