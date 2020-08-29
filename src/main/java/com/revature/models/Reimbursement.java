package com.revature.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="reimbursement")
public class Reimbursement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reimb_id")
	private int reimbId;
	@Column(name="reimb_amount")
	private int reimbamount;
	@Column(name="reimb_submitted")
	private Timestamp reimbSubbed;
	@Column(name="reimb_resolved")
	private Timestamp reimbReslvd;
	@Column(name="reimb_description")
	private String reimbDesc;
	@Column(name="reimb_receipt")
	private byte[] reimbRecpt;
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_author",referencedColumnName="user_id")
	private Users author;
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_resolver",referencedColumnName="user_id")
	private Users resolver;
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_status_id")
	private ReimStatus status;
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_type_id")
	private ReimType type;
	
	public Reimbursement() {
		super();
	}
	
	public Reimbursement(int reimbamount, Timestamp reimbSubbed, String reimbDesc, byte[] reimbRecpt, Users author, ReimType type) {
		super();
		this.reimbamount = reimbamount;
		this.reimbSubbed = reimbSubbed;
		this.reimbDesc = reimbDesc;
		this.reimbRecpt = reimbRecpt;
		this.author = author;
		this.type = type;
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

	public Users getAuthor() {
		return author;
	}

	public Users getResolver() {
		return resolver;
	}

	public ReimStatus getStatus() {
		return status;
	}

	public ReimType getType() {
		return type;
	}

	public void setAuthor(Users author) {
		this.author = author;
	}

	public void setResolver(Users resolver) {
		this.resolver = resolver;
	}

	public void setStatus(ReimStatus status) {
		this.status = status;
	}

	public void setType(ReimType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((reimbDesc == null) ? 0 : reimbDesc.hashCode());
		result = prime * result + reimbId;
		result = prime * result + Arrays.hashCode(reimbRecpt);
		result = prime * result + ((reimbReslvd == null) ? 0 : reimbReslvd.hashCode());
		result = prime * result + ((reimbSubbed == null) ? 0 : reimbSubbed.hashCode());
		result = prime * result + reimbamount;
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
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
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbamount=" + reimbamount + ", reimbSubbed=" + reimbSubbed
				+ ", reimbReslvd=" + reimbReslvd + ", reimbDesc=" + reimbDesc + ", reimbRecpt="
				+ Arrays.toString(reimbRecpt) + ", author=" + author + ", resolver=" + resolver + ", status=" + status
				+ ", type=" + type + "]";
	}

	
}
