package org.think2.jdbc;

import org.think2.jdbc.bean.Model;
import org.think2.jdbc.bean.SqlValues;

/**
 * Created by zhoubin on 15/11/27.
 */
public interface SqlPrepare {

    SqlValues toInsert(Object instance, Model model);

    SqlValues toUpdate(Object instance);

    SqlValues toDelete();

    SqlValues toSelect();
}
