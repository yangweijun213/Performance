package com.lrm.data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class DimTestRun {

	private int runId;
	private Timestamp startTime;
	private Timestamp endTime;
	private Set<FctTestResult> fctTestResults = new HashSet<FctTestResult>();

	// Constructors

	/** default constructor */
	public DimTestRun() {
	}

	/** minimal constructor */
	public DimTestRun(int runId) {
		this.runId = runId;
	}

	/**
	 * @param runId
	 * @param startTime
	 * @param endTime
	 */
	public DimTestRun(int runId, Timestamp startTime, Timestamp endTime) {

		this.runId = runId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getRunId() {
		return runId;
	}

	public void setRunId(int runId) {
		this.runId = runId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Set<FctTestResult> getFctTestResults() {
		return fctTestResults;
	}

	public void setFctTestResults(Set<FctTestResult> fctTestResults) {
		this.fctTestResults = fctTestResults;
	}
	
	

}
