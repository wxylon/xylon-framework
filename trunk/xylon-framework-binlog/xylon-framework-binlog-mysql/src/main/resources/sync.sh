#!/bin/bash

#-------------------------------------------------------------------
#    SYNC Bootstrap Script 
#
#    ��Ҫ�������»���������
#
#      JAVA_HOME           - JDK�İ�װ·��
#
#-------------------------------------------------------------------

# �ж��Ƿ���cygwin������
cygwin=false;
case "`uname`" in
  CYGWIN*) cygwin=true ;;
esac

export JAVA_HOME=/home/huangwy/jdk1.6.0_14

# ȷ����װ��java����������JAVA_HOME��������.
noJavaHome=false
if [ -z "$JAVA_HOME" ] ; then
    noJavaHome=true
fi
if $cygwin ; then
    [ -n "$JAVA_HOME" ] &&
        JAVA_HOME=`cygpath -u "$JAVA_HOME"`
fi
if [ ! -e "$JAVA_HOME/bin/java" ] ; then
    noJavaHome=true
fi
if $noJavaHome ; then
    echo "Error: JAVA_HOME environment variable is not set."
    exit 1
fi

# �øýű���λ���ƶ�SYNC_HOME��·��.
CURR_DIR=`pwd`
cd `dirname "$0"`/..
SYNC_HOME=`pwd`
cd $CURR_DIR

if [ -z "$SYNC_HOME" ] ; then
    echo
    echo ����: �������û���������SYNC_HOME����ָ��SYNC�İ�װ·��
    echo
    exit 1
fi

echo $SYNC_HOME

CLASSPATH="$SYNC_HOME/lib/classworlds-1.0.jar"
MAIN_CLASS="org.codehaus.classworlds.Launcher"

if $cygwin ; then
    JAVA_HOME=`cygpath -w "$JAVA_HOME"`
    SYNC_HOME=`cygpath -w "$SYNC_HOME"`
    CLASSPATH=`cygpath -p -w "$CLASSPATH"`
fi


DEFAULT_OPTS="-server -Xms512m -Xmx512m -Xss1024k"
# DEFAULT_OPTS="$DEFAULT_OPTS -XX:+HeapDumpOnOutOfMemoryError -XX:+AggressiveOpts -XX:+UseParallelGC -XX:+UseBiasedLocking -XX:NewSize=64m"
DEFAULT_OPTS="$DEFAULT_OPTS -Dsync.home=\"$SYNC_HOME\""
DEFAULT_OPTS="$DEFAULT_OPTS -Dclassworlds.conf=\"$SYNC_HOME/bin/sync.classworlds\""

CMD="exec \"$JAVA_HOME/bin/java\" $DEFAULT_OPTS $OPTS -classpath \"$CLASSPATH\"  $MAIN_CLASS $@"
eval $CMD
