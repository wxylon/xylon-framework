/**
  CONVERT ����
  ������÷�
    CONVERT() �����ǰ�����ת��Ϊ���������͵�ͨ�ú�����
    CONVERT() ���������ò�ͬ�ĸ�ʽ��ʾ����/ʱ�����ݡ�
  �﷨
    CONVERT(data_type(length),data_to_be_converted,style)
    data_type(length) �涨Ŀ���������ͣ����п�ѡ�ĳ��ȣ���data_to_be_converted ������Ҫת����ֵ��style �涨����/ʱ��������ʽ��
  ref:http://www.w3school.com.cn/sql/func_convert.asp
*/
select CONVERT(VARCHAR(10),GETDATE(), 120)
