/**
	参考：http://blog.csdn.net/wh62592855/article/details/4712555
	1.union 对两个结果集进行并集操作，不包括重复行，同时进行默认规则的排序；
	2.union all 对两个结果集进行并集操作，包括重复行，不进行排序；
	3.ntersect 对两个结果集进行交集操作，不包括重复行，同时进行默认规则的排序；
	4.minus 对两个结果集进行差操作，不包括重复行，同时进行默认规则的排序(SQL Server 不支持)。
	5.order by 子句必须写在最后一个结果集里，并且其排序规则将改变操作后的排序结果。对于union、union all、intersect、minus都有效。
	6.union 的几个表的数据量很大时，建议还是采用先导出文本，然后用脚本来执行，因为纯粹用sql，效率会比较低，而且它会写临时文件，如果你的磁盘空间不够大，就有可能会出错：Error writing file '/tmp/MYLsivgK' (Errcode: 28)

	效率：
		1.union 用排序空间进行排序删除重复的记录，最后返回结果集，如果表数据量大的话可能会导致用磁盘进行排序。
		2.UNION ALL只是简单的将两个结果合并后就返回。
		3.UNION ALL 要比UNION快很多。
*/