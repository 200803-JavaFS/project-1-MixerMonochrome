package com.revature.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class Reimbursement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int reimbId;
	private int reimbamount;
	private Timestamp reimbSubbed;
	private Timestamp reimbReslvd;
	private String reimbDesc;
	private byte[] reimbRecpt;
	private int author;
	private int resolver;
	private int statusId;
	private int typeId;
	
	public Reimbursement() {
		super();
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public int getReimbamount() {
		return reimbamount;
	}

	public void setReimbamount(int reimbamount) {
		this.reimbamount = reimbamount;
	}

	public Timestamp getReimbSubbed() {
		return reimbSubbed;
	}

	public void setReimbSubbed(Timestamp reimbSubbed) {
		this.reimbSubbed = reimbSubbed;
	}

	public Timestamp getReimbReslvd() {
		return reimbReslvd;
	}

	public void setReimbReslvd(Timestamp reimbReslvd) {
		this.reimbReslvd = reimbReslvd;
	}

	public String getReimbDesc() {
		return reimbDesc;
	}

	public void setReimbDesc(String reimbDesc) {
		this.reimbDesc = reimbDesc;
	}

	public byte[] getReimbRecpt() {
		return reimbRecpt;
	}

	public void setReimbRecpt(byte[] reimbRecpt) {
		this.reimbRecpt = reimbRecpt;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + author;
		result = prime * result + ((reimbDesc == null) ? 0 : reimbDesc.hashCode());
		result = prime * result + reimbId;
		result = prime * result + Arrays.hashCode(reimbRecpt);
		result = prime * result + ((reimbReslvd == null) ? 0 : reimbReslvd.hashCode());
		result = prime * result + ((reimbSubbed == null) ? 0 : reimbSubbed.hashCode());
		result = prime * result + reimbamount;
		result = prime * result + resolver;
		result = prime * result + statusId;
		result = prime * result + typeId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (author != other.author)
			return false;
		if (reimbDesc == null) {
			if (other.reimbDesc != null)
				return false;
		} else if (!reimbDesc.equals(other.reimbDesc))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (!Arrays.equals(reimbRecpt, other.reimbRecpt))
			return false;
		if (reimbReslvd == null) {
			if (other.reimbReslvd != null)
				return false;
		} else if (!reimbReslvd.equals(other.reimbReslvd))
			return false;
		if (reimbSubbed == null) {
			if (other.reimbSubbed != null)
				return false;
		} else if (!reimbSubbed.equals(other.reimbSubbed))
			return false;
		if (reimbamount != other.reimbamount)
			return false;
		if (resolver != other.resolver)
			return false;
		if (statusId != other.statusId)
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbamount=" + reimbamount + ", reimbSubbed=" + reimbSubbed
				+ ", reimbReslvd=" + reimbReslvd + ", reimbDesc=" + reimbDesc + ", reimbRecpt="
				+ Arrays.toString(reimbRecpt) + ", author=" + author + ", resolver=" + resolver + ", statusId="
				+ statusId + ", typeId=" + typeId + "]";
	}

	
	
	
}
