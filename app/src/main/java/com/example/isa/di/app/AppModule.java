package com.example.isa.di.app;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.example.isa.BuildConfig;
import com.example.isa.common.constants.DatabaseConstants;
import com.example.isa.common.constants.NetworkConstants;
import com.example.isa.common.constants.utils.SchedulersProvider;
import com.example.isa.data.datasource.database.AppDatabase;
import com.example.isa.data.repositories.PreferencesRepositoryImpl;
import com.example.isa.di.activity.main.MainComponent;
import com.example.isa.di.fragment.login.auth.LoginComponent;
import com.example.isa.di.fragment.mypage.MyPageComponent;
import com.example.isa.di.fragment.packettask.functionpacket.FunctionPacketComponent;
import com.example.isa.di.fragment.packettask.infopackettask.PacketTaskInfoComponent;
import com.example.isa.di.fragment.packettask.task.PacketTaskComponent;
import com.example.isa.di.fragment.packetcontent.PacketContentComponent;
import com.example.isa.di.fragment.packetnegotiation.infopacket.PacketNegotiationDetailComponent;
import com.example.isa.di.fragment.packetnegotiation.packetnegotiation.PacketNegotiationComponent;
import com.example.isa.di.global.base.BaseComponentBuilder;
import com.example.isa.di.global.scope.PerApplication;
import com.example.isa.domain.repositories.PreferencesRepository;
import com.example.isa.presentation.activity.main.MainActivity;
import com.example.isa.presentation.fragment.login.auth.LoginFragment;
import com.example.isa.presentation.fragment.mypage.MyPageFragment;
import com.example.isa.presentation.fragment.packetnegotiation.PacketNegotiationFragment;
import com.example.isa.presentation.fragment.packetnegotiation.taskpacket.PacketBaseFragment;
import com.example.isa.presentation.fragment.packetnegotiation.contentpacket.PacketContentFragment;
import com.example.isa.presentation.fragment.packetnegotiation.infopacket.PacketNegotiationDetailFragment;
import com.example.isa.presentation.fragment.packetnegotiation.taskpacket.functionpacket.FunctionPacketFragment;
import com.example.isa.presentation.fragment.packetnegotiation.taskpacket.infotaskpacket.PacketTaskDetailFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = {LoginComponent.class, MainComponent.class, MyPageComponent.class, PacketNegotiationComponent.class, PacketNegotiationDetailComponent.class, PacketContentComponent.class, PacketTaskComponent.class, PacketTaskInfoComponent.class, FunctionPacketComponent.class})
public class AppModule {
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideApplicationContext() {
        return context;
    }

    @PerApplication
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl(BuildConfig.BASE_URL).client(okHttpClient).build();
    }

    @PerApplication
    @Provides
    Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    @PerApplication
    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor, TrustManager[] trustAllCenter, SSLSocketFactory sslSocketFactory) {
        return new OkHttpClient.Builder().addInterceptor(interceptor).sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCenter[0]).hostnameVerifier((hostname, session) -> true).connectTimeout(NetworkConstants.TIME_OUT_MINUTES, TimeUnit.MINUTES).writeTimeout(NetworkConstants.TIME_OUT_MINUTES, TimeUnit.MINUTES).readTimeout(NetworkConstants.TIME_OUT_MINUTES, TimeUnit.MINUTES).build();
    }

    @PerApplication
    @Provides
    SSLSocketFactory provideSSLSocketFactory(TrustManager[] trustAllCenter) {
        try {
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCenter, new SecureRandom());

            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PerApplication
    @Provides
    @SuppressLint("TrustAllX509TrustManager")
    TrustManager[] provideTrustManager() {
        return new TrustManager[]{new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                //Do nothing
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                //Do nothing
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
    }

    @PerApplication
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    @PerApplication
    @Provides
    PreferencesRepository providePreferencesRepository() {
        return new PreferencesRepositoryImpl(context);
    }

    @Provides
    @IntoMap
    @ClassKey(LoginFragment.class)
    BaseComponentBuilder provideLoginFragment(LoginComponent.Builder builder) {
        return builder;
    }

    @PerApplication
    @Provides
    SchedulersProvider provideSchedulersProvider() {
        return new SchedulersProvider();
    }

    @PerApplication
    @Provides
    AppDatabase provideAppDatabase() {
        return Room.databaseBuilder(context, AppDatabase.class, DatabaseConstants.DATABASE_NAME).fallbackToDestructiveMigration().build();
    }

    @Provides
    @IntoMap
    @ClassKey(MainActivity.class)
    BaseComponentBuilder provideMainActivity(MainComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(MyPageFragment.class)
    BaseComponentBuilder provideMyPageFragment(MyPageComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(PacketNegotiationFragment.class)
    BaseComponentBuilder providePacketNegotiationFragment(PacketNegotiationComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(PacketNegotiationDetailFragment.class)
    BaseComponentBuilder providePacketNegotiationDetailFragment(PacketNegotiationDetailComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(PacketContentFragment.class)
    BaseComponentBuilder providePacketContentFragment(PacketContentComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(PacketBaseFragment.class)
    BaseComponentBuilder providePacketBaseFragment(PacketTaskComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(PacketTaskDetailFragment.class)
    BaseComponentBuilder providePacketTaskDetailFragment(PacketTaskInfoComponent.Builder builder) {
        return builder;
    }

    @Provides
    @IntoMap
    @ClassKey(FunctionPacketFragment.class)
    BaseComponentBuilder provideFunctionPacketFragment(FunctionPacketComponent.Builder builder) {
        return builder;
    }
}
