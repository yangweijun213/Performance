-- For dim_transaction
CREATE SEQUENCE seq_dim_transaction
increment by 1  -- ÿ�ε���1
start with 1  --��1��ʼ
nomaxvalue  --û�����ֵ
minvalue 1  --��Сֵ=1
nocycle;  --��ѭ��

--For FCT_TEST_RESULT
CREATE SEQUENCE seq_fct_test_result
increment by 1  -- ÿ�ε���1
start with 1  --��1��ʼ
nomaxvalue  --û�����ֵ
minvalue 1  --��Сֵ=1
nocycle;  --��ѭ��

--For DIM_TEST_RUN
CREATE SEQUENCE seq_dim_test_run
increment by 1  -- ÿ�ε���1
start with 1  --��1��ʼ
nomaxvalue  --û�����ֵ
minvalue 1  --��Сֵ=1
nocycle;  --��ѭ��

