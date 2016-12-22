/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:47:37 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:47:37 +0800.
 *  This file is originally created by Lena.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.mvvm.lib.view.adapter.list.base;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ray.mvvm.lib.databinding.StateEmptyLayoutBinding;
import com.ray.mvvm.lib.databinding.StateErrorLayoutBinding;
import com.ray.mvvm.lib.databinding.StateLoadMoreErrorLayoutBinding;
import com.ray.mvvm.lib.databinding.StateLoadMoreLayoutBinding;
import com.ray.mvvm.lib.databinding.StateLoadingLayoutBinding;
import com.ray.mvvm.lib.databinding.StateNoMoreLayoutBinding;
import com.ray.mvvm.lib.view.adapter.list.viewholder.BaseViewHolder;
import com.ray.mvvm.lib.viewmodel.StateVM;
import com.ray.mvvm.lib.widget.anotations.ListViewItemType;
import com.ray.mvvm.lib.widget.anotations.PageState;

public abstract class StateListAdapter<T> extends ListAdapter<T> {

    private static final int NO_INDEX = -99;

    private StateVM stateVM;

    public void setStateVM(StateVM stateVM) {
        this.stateVM = stateVM;
    }

    @Override
    public final BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding viewDataBinding;
        final int state = stateVM.getState();
        switch (state) {
            case PageState.EMPTY:
                viewDataBinding = StateEmptyLayoutBinding.inflate(inflater, parent, false);
                break;
            case PageState.ERROR:
                viewDataBinding = StateErrorLayoutBinding.inflate(inflater, parent, false);
                break;
            case PageState.LOADING:
                viewDataBinding = StateLoadingLayoutBinding.inflate(inflater, parent, false);
                break;
            default:
            case PageState.CONTENT:
            case PageState.LOAD_MORE:
                switch (viewType) {
                    default:
                        viewDataBinding = createBinding(LayoutInflater.from(parent.getContext()), parent, viewType);
                        break;
                    case ListViewItemType.NO_MORE:
                        viewDataBinding = StateNoMoreLayoutBinding.inflate(inflater, parent, false);
                        break;
                    case ListViewItemType.LOAD_MORE:
                        viewDataBinding = StateLoadMoreLayoutBinding.inflate(inflater, parent, false);
                        break;
                    case ListViewItemType.LOAD_MORE_ERROR:
                        viewDataBinding = StateLoadMoreErrorLayoutBinding.inflate(inflater, parent, false);
                        break;
                }
                break;
        }
        return new BaseViewHolder(viewDataBinding);
    }

    @Override
    public int getItemCount() {
        final int state = stateVM.getState();
        switch (state) {
            case PageState.EMPTY:
            case PageState.ERROR:
            case PageState.LOADING:
                return 1;
            default:
            case PageState.CONTENT:
            case PageState.LOAD_MORE:
                return super.getItemCount() + 1;
        }
    }

    @Override
    public final int getItemViewType(int position) {
        final int state = stateVM.getState();
        switch (state) {
            case PageState.EMPTY:
            case PageState.ERROR:
            case PageState.LOADING:
                return state;
            default:
            case PageState.CONTENT:
            case PageState.LOAD_MORE:
                final int listItemType = stateVM.getListItemType();
                final int totalCount = getItemCount();
                if (position == totalCount - 1) {
                    return listItemType;
                } else {
                    return getRealItemViewType(position);
                }
        }
    }

    protected int getRealItemViewType(int position) {
        return NO_INDEX;
    }

    @Override
    protected void bindingViewHolder(BaseViewHolder holder, int position) {
        final int state = stateVM.getState();
        final int listItemType = getItemViewType(position);
        if (state == PageState.EMPTY || state == PageState.ERROR || state == PageState.LOADING) {
            holder.bindData(stateVM);
        } else {
            if (listItemType == ListViewItemType.NO_MORE || listItemType == ListViewItemType.LOAD_MORE || listItemType == ListViewItemType.LOAD_MORE_ERROR)
                holder.bindData(stateVM);
            else {
                super.bindingViewHolder(holder, position);
            }
        }
    }

}