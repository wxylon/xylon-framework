package org.isharding.shard.strategy.access;

import org.isharding.shard.Shard;

/**
 * ��shards��ִ�е�һ�β���
 * 
 * @author <a href="mailto:kerrigan@alibaba-inc.com">Argan Wang</a>
 *
 */
public interface ShardOperation {
    Object execute(Shard shard);
    
    String getOperationName();
}
