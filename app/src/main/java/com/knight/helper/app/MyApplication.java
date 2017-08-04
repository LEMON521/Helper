package com.knight.helper.app;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.db.table.TableEntity;
import org.xutils.x;

/**
 * Created by lemon on 2017/7/22.
 */

public class MyApplication extends Application {


    public DbManager db;

    public DbManager.DaoConfig daoConfig;

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志, 开启debug会影响性能.


        /**
         * 初始化DaoConfig配置
         */
        daoConfig = new DbManager.DaoConfig()
                //设置数据库名，默认xutils.db
                .setDbName("helper.db")
                //设置数据库路径，默认存储在app的私有目录
                //.setDbDir(new File("/mnt/sdcard/ahelper/db"))//发现一个问题，（在模拟器中）如果将数据库放到sd卡上面，用户卸载程序，重新安装，会出现无法预知的打开数据库错误的的错误
                //设置数据库的版本号
                .setDbVersion(1)
                //设置数据库打开的监听
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        //开启数据库支持多线程操作，提升性能，对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                //设置数据库更新的监听
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        LogUtil.e("Helper数据库监听" + "更新数据库" + db.getDaoConfig().toString());
                    }
                })
                //设置表创建的监听
                .setTableCreateListener(new DbManager.TableCreateListener() {
                    @Override
                    public void onTableCreated(DbManager db, TableEntity<?> table) {
                        LogUtil.e("Helper数据库监听" + "创建表:" + table.getName());
                    }
                });
        //设置是否允许事务，默认true
        //.setAllowTransaction(true)

        db = x.getDb(daoConfig);
    }
}
