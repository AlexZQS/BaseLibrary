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

package com.ray.mvvm.lib.view.base.page;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.ray.mvvm.lib.BR;
import com.ray.mvvm.lib.R;
import com.ray.mvvm.lib.app.BaseApplication;
import com.ray.mvvm.lib.di.IBuildComp;
import com.ray.mvvm.lib.di.modules.ActivityModule;
import com.ray.mvvm.lib.presenter.IPresenter;
import com.ray.mvvm.lib.view.base.comp.ActivityComp;
import com.ray.mvvm.lib.view.base.comp.DaggerActivityComp;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.mvvm.lib.viewmodel.BaseVM;
import com.ray.mvvm.lib.widget.lifecycle.LifecycleEvent;
import com.squareup.leakcanary.RefWatcher;

import rx.Subscription;

public abstract class BaseDIActivity extends BaseActivity implements IBuildComp {

    private ActivityComp activityComp;
    private Subscription subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildComp();
    }

    protected ActivityComp getActivityComp() {
        if (activityComp == null)
            activityComp = DaggerActivityComp
                    .builder()
                    .appComp(BaseApplication.getAppComp(this))
                    .activityModule(new ActivityModule(this))
                    .build();
        return activityComp;
    }

    protected <P extends IPresenter, V extends IView> void bindLayout(int layoutRes, BaseVM<P, V> viewModel) {
        bindViewModel(layoutRes, viewModel, true);
    }

    protected <P extends IPresenter, V extends IView> void bindLayout(int layoutRes, BaseVM<P, V> viewModel, boolean homeAsUp) {
        bindViewModel(layoutRes, viewModel, homeAsUp);
    }

    private <P extends IPresenter, V extends IView> void bindViewModel(int layoutRes, BaseVM<P, V> viewModel, boolean homeAsUp) {
        bindViewModel(DataBindingUtil.setContentView(this, layoutRes), viewModel, homeAsUp);
    }

    protected <P extends IPresenter, V extends IView, B extends ViewDataBinding> void bindViewModel(B binding, BaseVM<P, V> viewModel, boolean homeAsUp) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setSubtitle("");
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUp);
        viewModel.presenter().setLifecycleObs(lifecycleSubject.asObservable(), LifecycleEvent.DESTROY);
        subscription = lifecycleSubject
                .subscribe(activityEvent -> {
                    if (activityEvent == LifecycleEvent.DESTROY) {
                        final RefWatcher refWatcher = BaseApplication.getRefWatcher(BaseDIActivity.this);
                        if (refWatcher != null) {
                            refWatcher.watch(viewModel);
                            refWatcher.watch(viewModel.presenter());
                            refWatcher.watch(this);
                        }
                        subscription.unsubscribe();
                    }
                });
        binding.setVariable(BR.viewModel, viewModel);
        if (ViewDataBinding.getBuildSdkInt() < Build.VERSION_CODES.KITKAT) {
            binding.executePendingBindings();
        }
    }

}
