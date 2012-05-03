package org.isharding.shard.strategy.access;

import java.util.List;

import org.isharding.shard.Shard;
import org.isharding.shard.strategy.access.exit.ExitStrategy;

/**
 * ���ʲ��ԣ��������̵�ʵ�֣��磺���У����У������ǽ�������
 * @author <a href="mailto:kerrigan@alibaba-inc.com">Argan Wang</a>
 *
 */
public interface ShardAccessStrategy {
    Object apply(List<Shard> shards, ShardOperation operation, ExitStrategy exitStrategy, ExitOperationsCollector exitOperationsCollector);
}
