package org.isharding.shard;

import java.util.Set;

import javax.sql.DataSource;


/**
 * 
 * ����һ��Shard
 * 
 * @author <a href="mailto:kerrigan@alibaba-inc.com">Argan Wang</a>
 */
public interface Shard {
    DataSource getDataSource();
    Set<ShardId> getShardIds();
}
