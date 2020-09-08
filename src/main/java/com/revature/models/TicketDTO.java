package com.revature.models;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketDTO {
	public int typeId;
	public int reimbId;
	public int reimbAmnt;
	public String reimbSubbed;
	public String reimbResolved;
	public String reimbDesc;
	public byte[] reimbRecpt;
	public String type;
	public String author;
	public String resolver;
	public String status;
	
	@Override
	public String toString() {
		return "TicketDTO [typeId=" + typeId + ", reimbId=" + reimbId + ", reimbAmnt=" + reimbAmnt + ", reimbSubbed="
				+ reimbSubbed + ", reimbResolved=" + reimbResolved + ", reimbDesc=" + reimbDesc + ", reimbRecpt="
				+ Arrays.toString(reimbRecpt) + ", type=" + type + ", author=" + author + ", resolver=" + resolver
				+ ", status=" + status + "]";
	}
	
	
}
