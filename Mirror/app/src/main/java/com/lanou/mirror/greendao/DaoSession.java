package com.lanou.mirror.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.lanou.mirror.greendao.LabelEntity;
import com.lanou.mirror.greendao.AllHolder;
import com.lanou.mirror.greendao.HomePager;
import com.lanou.mirror.greendao.Special;
import com.lanou.mirror.greendao.Login;

import com.lanou.mirror.greendao.LabelEntityDao;
import com.lanou.mirror.greendao.AllHolderDao;
import com.lanou.mirror.greendao.HomePagerDao;
import com.lanou.mirror.greendao.SpecialDao;
import com.lanou.mirror.greendao.LoginDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig labelEntityDaoConfig;
    private final DaoConfig allHolderDaoConfig;
    private final DaoConfig homePagerDaoConfig;
    private final DaoConfig specialDaoConfig;
    private final DaoConfig loginDaoConfig;

    private final LabelEntityDao labelEntityDao;
    private final AllHolderDao allHolderDao;
    private final HomePagerDao homePagerDao;
    private final SpecialDao specialDao;
    private final LoginDao loginDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        labelEntityDaoConfig = daoConfigMap.get(LabelEntityDao.class).clone();
        labelEntityDaoConfig.initIdentityScope(type);

        allHolderDaoConfig = daoConfigMap.get(AllHolderDao.class).clone();
        allHolderDaoConfig.initIdentityScope(type);

        homePagerDaoConfig = daoConfigMap.get(HomePagerDao.class).clone();
        homePagerDaoConfig.initIdentityScope(type);

        specialDaoConfig = daoConfigMap.get(SpecialDao.class).clone();
        specialDaoConfig.initIdentityScope(type);

        loginDaoConfig = daoConfigMap.get(LoginDao.class).clone();
        loginDaoConfig.initIdentityScope(type);

        labelEntityDao = new LabelEntityDao(labelEntityDaoConfig, this);
        allHolderDao = new AllHolderDao(allHolderDaoConfig, this);
        homePagerDao = new HomePagerDao(homePagerDaoConfig, this);
        specialDao = new SpecialDao(specialDaoConfig, this);
        loginDao = new LoginDao(loginDaoConfig, this);

        registerDao(LabelEntity.class, labelEntityDao);
        registerDao(AllHolder.class, allHolderDao);
        registerDao(HomePager.class, homePagerDao);
        registerDao(Special.class, specialDao);
        registerDao(Login.class, loginDao);
    }
    
    public void clear() {
        labelEntityDaoConfig.getIdentityScope().clear();
        allHolderDaoConfig.getIdentityScope().clear();
        homePagerDaoConfig.getIdentityScope().clear();
        specialDaoConfig.getIdentityScope().clear();
        loginDaoConfig.getIdentityScope().clear();
    }

    public LabelEntityDao getLabelEntityDao() {
        return labelEntityDao;
    }

    public AllHolderDao getAllHolderDao() {
        return allHolderDao;
    }

    public HomePagerDao getHomePagerDao() {
        return homePagerDao;
    }

    public SpecialDao getSpecialDao() {
        return specialDao;
    }

    public LoginDao getLoginDao() {
        return loginDao;
    }

}
