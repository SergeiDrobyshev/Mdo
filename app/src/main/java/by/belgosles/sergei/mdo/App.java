package by.belgosles.sergei.mdo;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.facebook.stetho.Stetho;

import by.belgosles.sergei.mdo.Db.AppDb;

public class App extends Application {

    public static App instance;
    private AppDb database;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        instance = this;
        database = Room.databaseBuilder(this, AppDb.class, "databaseMdo").allowMainThreadQueries()  /// удалить .allowMainThreadQueries()
                .build();


       // InitializeStetho();

    }

    public static App getInstance() {
        return instance;
    }

    public AppDb getDatabase() {
        return database;
    }

    public void InitializeStetho (){

        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        Stetho.Initializer initializer = initializerBuilder.build();

        Stetho.initialize(initializer);
    }
}