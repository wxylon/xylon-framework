package org.isharding.shard.strategy.access;

import java.util.Map;

import org.isharding.shard.Shard;

/**
 * sql����������Ĵ����Խ�������д����磺���򣬷�ҳ���ϲ���
 * @author <a href="mailto:kerrigan@alibaba-inc.com">Argan Wang</a>
 *
 */
public interface ExitOperationsCollector {

    Object apply(Map<Shard,?> result);
    
}
