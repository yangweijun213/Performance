package com.lrm.data;

import java.util.HashSet;
import java.util.Set;
import java.sql.Timestamp;

public class FctTestResult implements java.io.Serializable {
	
	//fields
	private static final long serialVersionUID = 1L;
	private int resultId;
	private Timestamp userActionTime;
	private Timestamp domReadyTime;
	private int transactionId;
	private int runId;
	
	/** default constructor */
    public FctTestResult() {
    }

    /** minimal constructor */
    public FctTestResult(int resultId) {
        this.resultId = resultId;
    }

	/**
	 * @param resultId
	 * @param userActionTime
	 * @param domReadyTime
	 */
	public FctTestResult(int resultId, Timestamp userActionTime, Timestamp domReadyTime) {
		this.resultId = resultId;
		this.userActionTime = userActionTime;
		this.domReadyTime = domReadyTime;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public Timestamp getUserActionTime() {
		return userActionTime;
	}

	public void setUserActionTime(Timestamp userActionTime) {
		this.userActionTime = userActionTime;
	}

	public Timestamp getDomReadyTime() {
		return domReadyTime;
	}

	public void setDomReadyTime(Timestamp domReadyTime) {
		this.domReadyTime = domReadyTime;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getRunId() {
		return runId;
	}

	public void setRunId(int runId) {
		this.runId = runId;
	}
	
	
}
