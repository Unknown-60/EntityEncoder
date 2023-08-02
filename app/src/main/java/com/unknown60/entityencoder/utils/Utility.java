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
package com.unknown60.entityencoder.utils;

import android.content.Context;
import android.os.Build;
import java.lang.reflect.Method;

public class Utility {

	public static boolean isChrome() {
		return Build.BRAND.equals("chromium") || Build.BRAND.equals("chrome");
	}

	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	public static String getFirstCharacter(String sentence) {
		for (int i = 0; i < sentence.length(); i++) {
			String s = sentence.substring(i, i+1);
			if (s.equals("[") || s.equals("]")) continue;
			if (s.equals("{") || s.equals("}")) continue;
			if (s.equals("(") || s.equals(")")) continue;
			if (s.equals(",") || s.equals(".")) continue;
			if (s.equals("<") || s.equals(">")) continue;
			if (s.equals("《") || s.equals("》")) continue;
			if (s.equals("【") || s.equals("】")) continue;
			if (s.equals("｛") || s.equals("｝")) continue;
			return s;
		}
		return null;
	}

	public static String getSystemProperties(String key) {
		try {
			Class c = Class.forName("android.os.SystemProperties");
			Method m = c.getDeclaredMethod("get", String.class);
			m.setAccessible(true);
			return (String) m.invoke(null, key);
		} catch (Throwable e) {
			return "";
		}
	}

}
