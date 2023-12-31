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
package com.unknown60.entityencoder.ui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public abstract class AbsRecyclerViewAdapter extends RecyclerView.Adapter<AbsRecyclerViewAdapter.ClickableViewHolder> {

	private Context context;

	protected RecyclerView mRecyclerView;
	protected List<RecyclerView.OnScrollListener> mListeners = new ArrayList<RecyclerView.OnScrollListener>();

	public AbsRecyclerViewAdapter(RecyclerView recyclerView) {
		this.mRecyclerView = recyclerView;
		this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView rv, int newState) {
				for (RecyclerView.OnScrollListener listener : mListeners) {
					listener.onScrollStateChanged(rv, newState);
				}
			}

			@Override
			public void onScrolled(RecyclerView rv, int dx, int dy) {
				for (RecyclerView.OnScrollListener listener : mListeners) {
					listener.onScrolled(rv, dx, dy);
				}
			}
		});
	}

	public void addOnScrollListener(RecyclerView.OnScrollListener listener) {
		mListeners.add(listener);
	}

	public interface OnItemClickListener {
		public void onItemClick(int position, ClickableViewHolder holder);
	}

	public interface OnItemLongClickListener {
		public boolean onItemLongClick(int position, ClickableViewHolder holder);
	}

	private OnItemClickListener itemClickListener;
	private OnItemLongClickListener itemLongClickListener;

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.itemClickListener = listener;
	}

	public void setOnItemLongClickListener(OnItemLongClickListener listener) {
		this.itemLongClickListener = listener;
	}

	public void bindContext(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return this.context;
	}

	@Override
	public void onBindViewHolder(final ClickableViewHolder holder, final int position) {
		holder.getParentView().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (itemClickListener != null) {
					itemClickListener.onItemClick(position, holder);
				}
			}
		});
		holder.getParentView().setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if (itemLongClickListener != null) {
					return itemLongClickListener.onItemLongClick(position, holder);
				} else {
					return false;
				}
			}
		});
	}

	public class ClickableViewHolder extends RecyclerView.ViewHolder {

		private View parentView;

		public ClickableViewHolder(View itemView) {
			super(itemView);
			this.parentView = itemView;
		}

		public View getParentView() {
			return parentView;
		}

	}

}
