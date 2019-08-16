package by.belgosles.sergei.mdo;

import android.app.Application;

import androidx.room.Room;

import com.facebook.stetho.Stetho;

import by.belgosles.sergei.mdo.TEstDb.AppDbDict;
import by.belgosles.sergei.mdo.model.entity.AppDb;

public class App extends Application {

    public static App instance;
    public static App instance_dict;
    private AppDb database;
    private AppDbDict database_dict;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        instance = this;
        database = Room.databaseBuilder(this, AppDb.class, "databaseMdo").allowMainThreadQueries()
                // удалить .allowMainThreadQueries()
                .build();

        database_dict = Room.databaseBuilder(this, AppDbDict.class, "databaseDicts")
                .createFromAsset("DICT_ACC_METH.dbf")
                .allowMainThreadQueries()
                // удалить .allowMainThreadQueries()
                .build();
       InitializeStetho();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDb getDatabase() {
        return database;
    }

    public static App getInstance_dict() {
        return instance_dict;
    }

    public AppDbDict getDatabase_dict() {
        return database_dict;
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