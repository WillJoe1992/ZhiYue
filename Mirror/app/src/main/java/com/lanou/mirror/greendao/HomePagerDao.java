package com.lanou.mirror.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.lanou.mirror.greendao.HomePager;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HOME_PAGER".
*/
public class HomePagerDao extends AbstractDao<HomePager, Long> {

    public static final String TABLENAME = "HOME_PAGER";

    /**
     * Properties of entity HomePager.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Goods_img = new Property(1, String.class, "Goods_img", false, "GOODS_IMG");
        public final static Property Brand = new Property(2, String.class, "Brand", false, "BRAND");
        public final static Property Model = new Property(3, String.class, "Model", false, "MODEL");
        public final static Property Product_area = new Property(4, String.class, "Product_area", false, "PRODUCT_AREA");
        public final static Property Goods_price = new Property(5, String.class, "Goods_price", false, "GOODS_PRICE");
    };


    public HomePagerDao(DaoConfig config) {
        super(config);
    }
    
    public HomePagerDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HOME_PAGER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"GOODS_IMG\" TEXT," + // 1: Goods_img
                "\"BRAND\" TEXT," + // 2: Brand
                "\"MODEL\" TEXT," + // 3: Model
                "\"PRODUCT_AREA\" TEXT," + // 4: Product_area
                "\"GOODS_PRICE\" TEXT);"); // 5: Goods_price
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HOME_PAGER\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, HomePager entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Goods_img = entity.getGoods_img();
        if (Goods_img != null) {
            stmt.bindString(2, Goods_img);
        }
 
        String Brand = entity.getBrand();
        if (Brand != null) {
            stmt.bindString(3, Brand);
        }
 
        String Model = entity.getModel();
        if (Model != null) {
            stmt.bindString(4, Model);
        }
 
        String Product_area = entity.getProduct_area();
        if (Product_area != null) {
            stmt.bindString(5, Product_area);
        }
 
        String Goods_price = entity.getGoods_price();
        if (Goods_price != null) {
            stmt.bindString(6, Goods_price);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public HomePager readEntity(Cursor cursor, int offset) {
        HomePager entity = new HomePager( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Goods_img
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Brand
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Model
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Product_area
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // Goods_price
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, HomePager entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGoods_img(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBrand(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setModel(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProduct_area(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setGoods_price(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(HomePager entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(HomePager entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
