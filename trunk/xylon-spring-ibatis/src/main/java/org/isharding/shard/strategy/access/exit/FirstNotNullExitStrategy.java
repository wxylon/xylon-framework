package org.isharding.shard.strategy.access.exit;

import org.isharding.shard.Shard;
import org.isharding.shard.strategy.access.ExitOperationsCollector;

/**
 * ��ȡ��һ����null������˳����ԣ���Ҫ������shars�в�ѯһ����¼��ʹ��
 * @author <a href="mailto:kerrigan@alibaba-inc.com">Argan Wang</a>
 *
 */
public class FirstNotNullExitStrategy implements ExitStrategy {

    private Object result;

    public boolean addResult(Object result, Shard shard) {
        if (result != null) {
            this.result = result;
            return true;
        }
        return false;
    }

    public Object compileResults(ExitOperationsCollector exitOperationsCollector) {
        return this.result;
    }

}
