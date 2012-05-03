package org.isharding.shard.strategy.selection;

import org.isharding.shard.ShardId;

/**
 * ѡ�����(Ŀǰ����ʹ��)
 * 
 * @author <a mailto="kerrigan@alibaba-inc.com">Argan Wang</a>
 */
public interface ShardSelectionStrategy {
    /**
     * ���¶���ѡ���ŵ�shard 
     * @param obj Ҫѡ��shard�Ķ���
     * @return ����Ҫ��ŵ���shard��id 
     */
    ShardId selectShardIdForNewObject(Object obj);
}
