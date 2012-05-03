package org.isharding.shard.strategy.resolution;

import java.util.List;

import org.isharding.shard.ShardId;

/**
 * 
 * @author <a href="mailto:kerrigan@alibaba-inc.com">Argan Wang</a>
 *
 */
public interface ShardResolutionStrategy {
    /**
     * ��������ܴ��ڵ�shard
     *
     * @param shardResolutionStrategyData �����ж�����shard��һЩ����
     * @return ���ݴ�ŵ�shard ids
     */
    List<ShardId> selectShardIdsFromShardResolutionStrategyData(
        ShardResolutionStrategyData shardResolutionStrategyData);
  }