package com.example.isa.di.fragment.mypage

import com.example.isa.common.constants.utils.SchedulersProvider
import com.example.isa.di.global.base.BaseModule
import com.example.isa.di.global.scope.PerScreen
import com.example.isa.di.repository.user.UserRepositoryModule
import com.example.isa.domain.interactors.mypage.MyPageInteractor
import com.example.isa.domain.interactors.mypage.MyPageInteractorImpl
import com.example.isa.domain.repositories.UserRepository
import com.example.isa.presentation.fragment.mypage.MyPagePresenter
import dagger.Module
import dagger.Provides

@Module(includes = [UserRepositoryModule::class])
class MyPageModule : BaseModule {

    @Provides
    fun providePresenter(myPageInteractor: MyPageInteractor): MyPagePresenter {
        return MyPagePresenter(myPageInteractor)
    }

    @PerScreen
    @Provides
    fun provideMyPageInteractor(userRepository: UserRepository, schedulersProvider: SchedulersProvider): MyPageInteractor {
        return MyPageInteractorImpl(userRepository, schedulersProvider)
    }
}