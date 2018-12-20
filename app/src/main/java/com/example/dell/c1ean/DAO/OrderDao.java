package com.example.dell.c1ean.DAO;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.dell.c1ean.Bean.User;
import com.example.dell.c1ean.Bean.Worker;

import com.example.dell.c1ean.Bean.Order;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ORDER".
*/
public class OrderDao extends AbstractDao<Order, Long> {

    public static final String TABLENAME = "ORDER";

    /**
     * Properties of entity Order.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property User_id = new Property(1, long.class, "user_id", false, "USER_ID");
        public final static Property Worker_id = new Property(2, Long.class, "worker_id", false, "WORKER_ID");
        public final static Property Company_id = new Property(3, Long.class, "company_id", false, "COMPANY_ID");
        public final static Property Type = new Property(4, String.class, "type", false, "TYPE");
        public final static Property Area = new Property(5, String.class, "area", false, "AREA");
        public final static Property Item_number = new Property(6, String.class, "item_number", false, "ITEM_NUMBER");
        public final static Property UserLocation = new Property(7, String.class, "userLocation", false, "USER_LOCATION");
        public final static Property Money = new Property(8, String.class, "money", false, "MONEY");
        public final static Property OrderTime = new Property(9, String.class, "orderTime", false, "ORDER_TIME");
        public final static Property BookingTime = new Property(10, String.class, "bookingTime", false, "BOOKING_TIME");
        public final static Property PayTime = new Property(11, String.class, "payTime", false, "PAY_TIME");
        public final static Property State = new Property(12, int.class, "state", false, "STATE");
        public final static Property UserEvaluation = new Property(13, String.class, "userEvaluation", false, "USER_EVALUATION");
        public final static Property WorkerEvaluation = new Property(14, String.class, "workerEvaluation", false, "WORKER_EVALUATION");
        public final static Property Star = new Property(15, int.class, "star", false, "STAR");
        public final static Property Activity_id = new Property(16, Long.class, "activity_id", false, "ACTIVITY_ID");
        public final static Property IsActivity = new Property(17, int.class, "isActivity", false, "IS_ACTIVITY");
    }

    private DaoSession daoSession;


    public OrderDao(DaoConfig config) {
        super(config);
    }
    
    public OrderDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ORDER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"USER_ID\" INTEGER NOT NULL ," + // 1: user_id
                "\"WORKER_ID\" INTEGER," + // 2: worker_id
                "\"COMPANY_ID\" INTEGER," + // 3: company_id
                "\"TYPE\" TEXT NOT NULL ," + // 4: type
                "\"AREA\" TEXT," + // 5: area
                "\"ITEM_NUMBER\" TEXT," + // 6: item_number
                "\"USER_LOCATION\" TEXT NOT NULL ," + // 7: userLocation
                "\"MONEY\" TEXT," + // 8: money
                "\"ORDER_TIME\" TEXT," + // 9: orderTime
                "\"BOOKING_TIME\" TEXT," + // 10: bookingTime
                "\"PAY_TIME\" TEXT," + // 11: payTime
                "\"STATE\" INTEGER NOT NULL ," + // 12: state
                "\"USER_EVALUATION\" TEXT," + // 13: userEvaluation
                "\"WORKER_EVALUATION\" TEXT," + // 14: workerEvaluation
                "\"STAR\" INTEGER NOT NULL ," + // 15: star
                "\"ACTIVITY_ID\" INTEGER," + // 16: activity_id
                "\"IS_ACTIVITY\" INTEGER NOT NULL );"); // 17: isActivity
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ORDER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Order entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getUser_id());
 
        Long worker_id = entity.getWorker_id();
        if (worker_id != null) {
            stmt.bindLong(3, worker_id);
        }
 
        Long company_id = entity.getCompany_id();
        if (company_id != null) {
            stmt.bindLong(4, company_id);
        }
        stmt.bindString(5, entity.getType());
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(6, area);
        }
 
        String item_number = entity.getItem_number();
        if (item_number != null) {
            stmt.bindString(7, item_number);
        }
        stmt.bindString(8, entity.getUserLocation());
 
        String money = entity.getMoney();
        if (money != null) {
            stmt.bindString(9, money);
        }
 
        String orderTime = entity.getOrderTime();
        if (orderTime != null) {
            stmt.bindString(10, orderTime);
        }
 
        String bookingTime = entity.getBookingTime();
        if (bookingTime != null) {
            stmt.bindString(11, bookingTime);
        }
 
        String payTime = entity.getPayTime();
        if (payTime != null) {
            stmt.bindString(12, payTime);
        }
        stmt.bindLong(13, entity.getState());
 
        String userEvaluation = entity.getUserEvaluation();
        if (userEvaluation != null) {
            stmt.bindString(14, userEvaluation);
        }
 
        String workerEvaluation = entity.getWorkerEvaluation();
        if (workerEvaluation != null) {
            stmt.bindString(15, workerEvaluation);
        }
        stmt.bindLong(16, entity.getStar());
 
        Long activity_id = entity.getActivity_id();
        if (activity_id != null) {
            stmt.bindLong(17, activity_id);
        }
        stmt.bindLong(18, entity.getIsActivity());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Order entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getUser_id());
 
        Long worker_id = entity.getWorker_id();
        if (worker_id != null) {
            stmt.bindLong(3, worker_id);
        }
 
        Long company_id = entity.getCompany_id();
        if (company_id != null) {
            stmt.bindLong(4, company_id);
        }
        stmt.bindString(5, entity.getType());
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(6, area);
        }
 
        String item_number = entity.getItem_number();
        if (item_number != null) {
            stmt.bindString(7, item_number);
        }
        stmt.bindString(8, entity.getUserLocation());
 
        String money = entity.getMoney();
        if (money != null) {
            stmt.bindString(9, money);
        }
 
        String orderTime = entity.getOrderTime();
        if (orderTime != null) {
            stmt.bindString(10, orderTime);
        }
 
        String bookingTime = entity.getBookingTime();
        if (bookingTime != null) {
            stmt.bindString(11, bookingTime);
        }
 
        String payTime = entity.getPayTime();
        if (payTime != null) {
            stmt.bindString(12, payTime);
        }
        stmt.bindLong(13, entity.getState());
 
        String userEvaluation = entity.getUserEvaluation();
        if (userEvaluation != null) {
            stmt.bindString(14, userEvaluation);
        }
 
        String workerEvaluation = entity.getWorkerEvaluation();
        if (workerEvaluation != null) {
            stmt.bindString(15, workerEvaluation);
        }
        stmt.bindLong(16, entity.getStar());
 
        Long activity_id = entity.getActivity_id();
        if (activity_id != null) {
            stmt.bindLong(17, activity_id);
        }
        stmt.bindLong(18, entity.getIsActivity());
    }

    @Override
    protected final void attachEntity(Order entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Order readEntity(Cursor cursor, int offset) {
        Order entity = new Order( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // user_id
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // worker_id
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // company_id
            cursor.getString(offset + 4), // type
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // area
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // item_number
            cursor.getString(offset + 7), // userLocation
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // money
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // orderTime
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // bookingTime
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // payTime
            cursor.getInt(offset + 12), // state
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // userEvaluation
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // workerEvaluation
            cursor.getInt(offset + 15), // star
            cursor.isNull(offset + 16) ? null : cursor.getLong(offset + 16), // activity_id
            cursor.getInt(offset + 17) // isActivity
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Order entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUser_id(cursor.getLong(offset + 1));
        entity.setWorker_id(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setCompany_id(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setType(cursor.getString(offset + 4));
        entity.setArea(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setItem_number(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUserLocation(cursor.getString(offset + 7));
        entity.setMoney(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setOrderTime(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setBookingTime(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setPayTime(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setState(cursor.getInt(offset + 12));
        entity.setUserEvaluation(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setWorkerEvaluation(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setStar(cursor.getInt(offset + 15));
        entity.setActivity_id(cursor.isNull(offset + 16) ? null : cursor.getLong(offset + 16));
        entity.setIsActivity(cursor.getInt(offset + 17));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Order entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Order entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Order entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getUserDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getWorkerDao().getAllColumns());
            builder.append(" FROM ORDER T");
            builder.append(" LEFT JOIN USER T0 ON T.\"USER_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN WORKER T1 ON T.\"WORKER_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Order loadCurrentDeep(Cursor cursor, boolean lock) {
        Order entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        User user = loadCurrentOther(daoSession.getUserDao(), cursor, offset);
         if(user != null) {
            entity.setUser(user);
        }
        offset += daoSession.getUserDao().getAllColumns().length;

        Worker worker = loadCurrentOther(daoSession.getWorkerDao(), cursor, offset);
        entity.setWorker(worker);

        return entity;    
    }

    public Order loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Order> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Order> list = new ArrayList<Order>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Order> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Order> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
