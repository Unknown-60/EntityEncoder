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
package com.unknown60.entityencoder.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.text.Html;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.support.v7.appcompat.R;
import android.widget.ImageButton;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.widget.Toast;
import android.content.ClipDescription;

public class FragmentDecode extends Fragment {

    EditText et_decode, et_decode_output;
    Button btn_decode;
    ImageButton ib_copy, ib_paste;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_decode, container, false);
 
        et_decode = view.findViewById(R.id.et_decode);
        et_decode_output = view.findViewById(R.id.et_decode_output);
        btn_decode = view.findViewById(R.id.btn_decode);
        ib_paste = view.findViewById(R.id.ib_paste_input_decode);
        ib_copy = view.findViewById(R.id.ib_copy_result_decode);

        btn_decode.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if (et_decode.getText().toString().contains("&#")){

                        String text_decoded = et_decode.getText().toString();
                   		String convertir = toASCII(text_decoded);
                   		et_decode_output.setText(convertir);

                    } else {
                        String text_decoded_entity = et_decode.getText().toString();
                        String entity_decode_convert = convertHexEntitiesToAscii(text_decoded_entity);
                        et_decode_output.setText(entity_decode_convert);                       
                    }
                }
            });
            
        ib_paste.setOnClickListener(new View.OnClickListener() {
        	@Override
                public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboard.hasPrimaryClip() && clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                    String text = item.getText().toString();
                    et_decode.setText(text);
                	}
                }
            });
            
        ib_copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipboardManager clipboard = (android.content.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("", et_decode_output.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getActivity(), getString(R.string.result_copy), Toast.LENGTH_SHORT).show();
                }
            });
            
        return view;
    }
    
    private String toASCII(String text_decoded){
        CharSequence convertedASCII = Html.fromHtml(text_decoded, null, null);
        return convertedASCII.toString();
    }
    
        public static String convertHexEntitiesToAscii(String input) {
            String regex = "\\\\0x([0-9A-Fa-f]{2})";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            StringBuffer output = new StringBuffer();

            while (matcher.find()) {
                String hexValue = matcher.group(1);
                int decimalValue = Integer.parseInt(hexValue, 16);
                char asciiChar = (char) decimalValue;
                matcher.appendReplacement(output, String.valueOf(asciiChar));
            }

            matcher.appendTail(output);

            return output.toString();
        }
}
