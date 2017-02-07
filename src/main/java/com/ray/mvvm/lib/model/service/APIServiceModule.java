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

package com.ray.mvvm.lib.model.service;

import com.ray.mvvm.lib.di.scope.PerApplication;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public final class APIServiceModule {

    @Provides
    @PerApplication
    static TopicService provideTopicService(Retrofit retrofit) {
        return retrofit.create(TopicService.class);
    }

    @Provides
    @PerApplication
    static MemberService provideMemberService(Retrofit retrofit) {
        return retrofit.create(MemberService.class);
    }

    @Provides
    @PerApplication
    static NodeService provideNodeService(Retrofit retrofit) {
        return retrofit.create(NodeService.class);
    }

    @Provides
    @PerApplication
    static ReplyService provideReplyService(Retrofit retrofit) {
        return retrofit.create(ReplyService.class);
    }

}
