package org.isharding.shard.strategy.access.exit;

import org.isharding.shard.Shard;
import org.isharding.shard.strategy.access.ExitOperationsCollector;

/**
 * �˳����ԣ�����ڶ��shards��ִ�У�������Ҫ��;�˳�����
 * @author <a href="mailto:kerrigan@alibaba-inc.com">Argan Wang</a>
 *
 */
public interface ExitStrategy {

    boolean addResult(Object result, Shard shard);

    Object compileResults(ExitOperationsCollector exitOperationsCollector);
}
