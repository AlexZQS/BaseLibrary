/*
 *  Copyright (C) 2015 Rayman Yan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.mvvm.lib.viewmodel;

import android.support.v7.widget.RecyclerView;

import com.ray.mvvm.lib.presenter.IPresenter;
import com.ray.mvvm.lib.view.adapter.list.base.ListAdapter;
import com.ray.mvvm.lib.view.base.view.IView;

import java.util.List;

public abstract class ListVM<P extends IPresenter, V extends IView, D> extends SwipRefreshVM<P, V, List<D>> {

    private final RecyclerView.LayoutManager layoutManager;
    private ListAdapter<D> adapter;

    public ListVM(P presenter, V view, RecyclerView.LayoutManager layoutManager, ListAdapter<D> adapter) {
        super(presenter, view);
        this.layoutManager = layoutManager;
        this.adapter = adapter;
    }

    @Override
    protected final boolean isRespNull(List<D> data) {
        return data == null || data.size() == 0;
    }

    public ListAdapter<D> getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    @Override
    protected void bindResp(List<D> data, int originState) {
        setData(data);
        adapter.setList(data);
        layoutManager.scrollToPosition(0);
    }
}
