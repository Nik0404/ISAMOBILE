package com.example.isa.di.fragment.mypage

import com.example.isa.di.global.base.BaseComponent
import com.example.isa.di.global.base.BaseComponentBuilder
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.presentation.fragment.mypage.MyPageFragment
import dagger.Subcomponent

@PerScreen
@Subcomponent(modules = [MyPageModule::class])
interface MyPageComponent : BaseComponent<MyPageFragment> {

    @Subcomponent.Builder
    interface Builder : BaseComponentBuilder<MyPageComponent, MyPageModule>
}