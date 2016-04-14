package com.lanou.mirror.greendao;

import android.database.sqlite.SQLiteDatabase;

import com.lanou.mirror.base.BaseApplication;
import com.lanou.mirror.tool.MyLog;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by dllo on 16/4/14.
 */
public class UsingData {

    // 数据库
    private static SQLiteDatabase db;
    // 对应的表,由java代码生成的,对数据库内相应的表操作使用此对象
    private static AllHolderDao allHolderDao;
    //操作数据库
    // 管理者
    private static DaoMaster daoMaster;
    // 会话
    private static DaoSession daoSession;

    private static UsingData usingData;

    private static LoginDao loginDao;

    private static HomePagerDao homePagerDao;

    private static LabelEntityDao labelEntityDao;

    private static SpecialDao specialDao;

    private DaoMaster.DevOpenHelper helper;

    private static final String DB_NAME = "AllHolder.db";

    public static UsingData GetUsingData() {
        if (usingData == null) {
            synchronized (UsingData.class) {
                if (usingData == null) {
                    usingData = new UsingData();
                }
            }
        }
        return usingData;
    }

    /**
     * 获得 DevOpenHelper类对象,类似于SQLiteOpAnHelper
     */
    public DaoMaster.DevOpenHelper getHelper() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(BaseApplication.getContext(), DB_NAME, null);
        }
        return helper;
    }

    /**
     * 获得一个可操作的 数据库对象
     */
    private SQLiteDatabase getDb() {
        if (db == null) {
            db = getHelper().getWritableDatabase();
        }
        return db;
    }

    private DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(getDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = getDaoMaster().newSession();
        }
        return daoSession;
    }


    /**
     * @return HolderDa
     */

    public AllHolderDao getHolderDa() {
        if (allHolderDao == null) {
            allHolderDao = getDaoSession().getAllHolderDao();
        }
        return allHolderDao;
    }

    public void deleteAllHolderDao(AllHolderDao allHolder) {
        getHolderDa().deleteAll();
    }

    public List<AllHolder> getAllHolderDao() {
        return getHolderDa().loadAll();
    }

    /**
     * 插入数据
     * 插入集合
     * AllHolder allHolder
     */
    public void addAllHolderDao(AllHolder allHolder) {
        getHolderDa().insertOrReplaceInTx(allHolder);
        //  getHolderDa().insert(allHolder);
    }

    public void addOneAllHolderDao(AllHolder allHolder) {
        getHolderDa().insertOrReplace(allHolder);
        //  getHolderDa().insert(allHolder);
    }

    public AllHolder queryAllHolderOne(String name) {
        return getHolderDa().load(Long.valueOf(name));
    }

    public void deleteOneHolder(String name) {
        getHolderDa().deleteByKey(Long.valueOf(name));
    }

    /**
     * @return LoginDao
     */
    public LoginDao getLoginDao() {
        if (loginDao == null) {
            loginDao = getDaoSession().getLoginDao();
        }
        return loginDao;
    }

    public void deleteLoginDao() {
        getLoginDao().deleteAll();
    }

    public List<Login> getAllLoginDao() {
        return getLoginDao().loadAll();
    }

    public void addLoginDao(Login login) {
        getLoginDao().insertOrReplaceInTx(login);
    }

    public void addOneLoginDao(Login login) {
        getLoginDao().insertOrReplace(login);
        //  getHolderDa().insert(allHolder);
    }

    public Login queryLoginDao(String name) {
        return getLoginDao().load(Long.valueOf(name));
    }

    public void deleteOneLoginDao(String name) {
        getLoginDao().deleteByKey(Long.valueOf(name));
    }

    /**
     * HomePagerDao
     *
     * @return
     */
    public HomePagerDao getHomePagerDao() {
        if (homePagerDao == null) {
            homePagerDao = getDaoSession().getHomePagerDao();
        }
        return homePagerDao;
    }

    public void deleteHomePagerDao() {
        getHomePagerDao().deleteAll();
        QueryBuilder queryBuilder = null;
    }

    public List<HomePager> getAllHomePagerDao(HomePager homePager) {
        return getHomePagerDao().loadAll();
    }

    public void addHomePagerDao(HomePager homePager) {

        getHomePagerDao().insertOrReplaceInTx(homePager);
    }


    public void addOneHomePagerDao(HomePager homePager) {
        getHomePagerDao().insertOrReplace(homePager);
        //  getHolderDa().insert(allHolder);
    }

    public HomePager queryHomePagerDao(String name) {
        return getHomePagerDao().load(Long.valueOf(name));
    }

    public void deleteHomePagerDao(String name) {
        getHomePagerDao().deleteByKey(Long.valueOf(name));
>>>>>>> feature/邹玮:Mirror/app/src/main/java/com/lanou/mirror/greendao/UsingData.java
    }

    /**
     * SpecialDao
     *
     * @return
     */
    public SpecialDao getSpecialDao() {
        if (specialDao == null) {
            specialDao = getDaoSession().getSpecialDao();
        }
        return specialDao;
    }

    public void deleteSpecialDao() {
        getSpecialDao().deleteAll();
    }

    public List<Special> getSpecialDao(LabelEntity labelEntity) {
        return getSpecialDao().loadAll();
    }

    public void addSpecialDao(Special special) {
        getSpecialDao().insertOrReplaceInTx(special);
    }


    public void addOneSpecialDao(Special special) {
        getSpecialDao().insertOrReplace(special);
        //  getHolderDa().insert(allHolder);
    }

    public Special querySpecialDao(String name) {
        return getSpecialDao().load(Long.valueOf(name));
    }

    public void deleteSpecialDao(String name) {
        getSpecialDao().deleteByKey(Long.valueOf(name));
    }


    /**
     * LabelEntityDao
     *
     * @return
     */
    public LabelEntityDao getLabelEntityDao() {
        if (labelEntityDao == null) {
            labelEntityDao = getDaoSession().getLabelEntityDao();
        }
        return labelEntityDao;
    }

    public void deleteLabelEntityDao() {
        getLabelEntityDao().deleteAll();
    }

    public List<LabelEntity> getAllLabelEntityDao() {
        return getLabelEntityDao().loadAll();
    }

    public void addLabelEntityDao(LabelEntity labelEntity) {
        getLabelEntityDao().insertOrReplaceInTx(labelEntity);
    }

    public void addOneLabelEntityDao(LabelEntity labelEntity) {
        getLabelEntityDao().insertOrReplace(labelEntity);
        //  getHolderDa().insert(allHolder);
    }


    public LabelEntity queryLabelEntityDao(String name) {
        return getLabelEntityDao().load(Long.valueOf(name));
    }

    public void deleteLabelEntityDao(String name) {
        getLabelEntityDao().deleteByKey(Long.valueOf(name));
    }



}
