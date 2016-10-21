package com.lrm.data;

import java.util.HashSet;
import java.util.Set;

public class DimTransaction implements java.io.Serializable {

	// Field
	private static final long serialVersionUID = 1L;
	private int transactionId;
	private String transactionName;
	private Set<FctTestResult> fctTestResults = new HashSet<FctTestResult>();

	// Constructors

	/** default constructor */
	public DimTransaction() {
	}

	/** minimal constructor */
	public DimTransaction(int transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @param transactionId
	 * @param transactionName
	 * @param fctTestResults
	 */
	public DimTransaction(int transactionId, String transactionName, Set<FctTestResult> fctTestResults) {
		this.transactionId = transactionId;
		this.transactionName = transactionName;
		this.fctTestResults = fctTestResults;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public Set<FctTestResult> getFctTestResults() {
		return fctTestResults;
	}

	public void setFctTestResults(Set<FctTestResult> fctTestResults) {
		this.fctTestResults = fctTestResults;
	}
	

}
