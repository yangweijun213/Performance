-- For dim_transaction
CREATE SEQUENCE seq_dim_transaction
increment by 1  -- 每次递增1
start with 1  --从1开始
nomaxvalue  --没有最大值
minvalue 1  --最小值=1
nocycle;  --不循环

--For FCT_TEST_RESULT
CREATE SEQUENCE seq_fct_test_result
increment by 1  -- 每次递增1
start with 1  --从1开始
nomaxvalue  --没有最大值
minvalue 1  --最小值=1
nocycle;  --不循环

--For DIM_TEST_RUN
CREATE SEQUENCE seq_dim_test_run
increment by 1  -- 每次递增1
start with 1  --从1开始
nomaxvalue  --没有最大值
minvalue 1  --最小值=1
nocycle;  --不循环

