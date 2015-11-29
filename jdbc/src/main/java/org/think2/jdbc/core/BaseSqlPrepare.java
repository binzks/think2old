package org.think2.jdbc.core;

import org.think2.jdbc.SqlPrepare;
import org.think2.jdbc.bean.SqlValues;

/**
 * Created by zhoubin on 15/11/28.
 */
public class BaseSqlPrepare implements SqlPrepare {
    @Override
    public SqlValues toInsert(Object instance) {
        return null;
    }

    @Override
    public SqlValues toUpdate(Object instance) {
        return null;
    }

    @Override
    public SqlValues toDelete() {
        return null;
    }

    @Override
    public SqlValues toSelect() {
        return null;
    }
}
