package com.example.dell.c1ean.DAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.dell.c1ean.Bean.UserWallet;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_WALLET".
*/
public class UserWalletDao extends AbstractDao<UserWallet, Long> {

    public static final String TABLENAME = "USER_WALLET";

    /**
     * Properties of entity UserWallet.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property User_type = new Property(1, String.class, "user_type", false, "USER_TYPE");
        public final static Property User_id = new Property(2, Long.class, "user_id", false, "USER_ID");
        public final static Property Alipay_account = new Property(3, String.class, "alipay_account", false, "ALIPAY_ACCOUNT");
        public final static Property Balance = new Property(4, Float.class, "balance", false, "BALANCE");
    }


    public UserWalletDao(DaoConfig config) {
        super(config);
    }
    
    public UserWalletDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_WALLET\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"USER_TYPE\" TEXT," + // 1: user_type
                "\"USER_ID\" INTEGER," + // 2: user_id
                "\"ALIPAY_ACCOUNT\" TEXT," + // 3: alipay_account
                "\"BALANCE\" REAL);"); // 4: balance
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_WALLET\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserWallet entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String user_type = entity.getUser_type();
        if (user_type != null) {
            stmt.bindString(2, user_type);
        }
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(3, user_id);
        }
 
        String alipay_account = entity.getAlipay_account();
        if (alipay_account != null) {
            stmt.bindString(4, alipay_account);
        }
 
        Float balance = entity.getBalance();
        if (balance != null) {
            stmt.bindDouble(5, balance);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserWallet entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String user_type = entity.getUser_type();
        if (user_type != null) {
            stmt.bindString(2, user_type);
        }
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(3, user_id);
        }
 
        String alipay_account = entity.getAlipay_account();
        if (alipay_account != null) {
            stmt.bindString(4, alipay_account);
        }
 
        Float balance = entity.getBalance();
        if (balance != null) {
            stmt.bindDouble(5, balance);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserWallet readEntity(Cursor cursor, int offset) {
        UserWallet entity = new UserWallet( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // user_type
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // user_id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // alipay_account
            cursor.isNull(offset + 4) ? null : cursor.getFloat(offset + 4) // balance
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserWallet entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUser_type(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUser_id(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setAlipay_account(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setBalance(cursor.isNull(offset + 4) ? null : cursor.getFloat(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserWallet entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserWallet entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserWallet entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
