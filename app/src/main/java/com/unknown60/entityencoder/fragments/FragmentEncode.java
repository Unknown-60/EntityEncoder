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
import android.support.v7.appcompat.R;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.widget.Toast;
import android.content.ClipDescription;

public class FragmentEncode extends Fragment {

    EditText et_encode, et_encode_output;
    Button btn_encode;
    CheckBox cb_html_style;
    ImageButton ib_copy, ib_paste;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encode, container, false);

        et_encode = view.findViewById(R.id.et_encode);
        et_encode_output = view.findViewById(R.id.et_encode_output);
        btn_encode = view.findViewById(R.id.btn_encode);
        cb_html_style = view.findViewById(R.id.cb_html_style);
        ib_paste = view.findViewById(R.id.ib_paste_input_encode);
        ib_copy = view.findViewById(R.id.ib_copy_result_encode);

        cb_html_style.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (cb_html_style.isChecked()){
                    	if (!et_encode_output.getText().toString().equals("")){
                            String text_encoded_html = et_encode.getText().toString();
                            String convertir_html = toEntityHtml(text_encoded_html);
							et_encode_output.setText(convertir_html);
                        }
                    } else {
                        if (!et_encode_output.getText().toString().equals("")){
                            String text_encoded = et_encode.getText().toString();
                            String convertir = toEntity(text_encoded);
                            et_encode_output.setText(convertir); 
                        }
                    }
                }
            });

        btn_encode.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if (cb_html_style.isChecked()){
                    	String text_encoded_html = et_encode.getText().toString();
                    	String convertir_html = toEntityHtml(text_encoded_html);
						et_encode_output.setText(convertir_html);
                    } else {
                        String text_encoded = et_encode.getText().toString();
                        String convertir = toEntity(text_encoded);
						et_encode_output.setText(convertir);
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
                    et_encode.setText(text);
                } 
             }
         });   
            
        ib_copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipboardManager clipboard = (android.content.ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("", et_encode_output.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getActivity(), getString(R.string.result_copy), Toast.LENGTH_SHORT).show();
                }
            });
            
        return view;
    }

    private String toEntityHtml(String text_encoded_html) {
        StringBuilder htmlBuilder = new StringBuilder();

        for (char c : text_encoded_html.toCharArray()) {
            htmlBuilder.append("&#x").append(Integer.toHexString(c)).append(";");
        }

        return htmlBuilder.toString();
    }

    private String toEntity(String text_encoded) {
        StringBuilder htmlBuilder = new StringBuilder();

        for (char c : text_encoded.toCharArray()) {
            htmlBuilder.append("\\0x").append(Integer.toHexString(c));
        }

        return htmlBuilder.toString();
    }
}
