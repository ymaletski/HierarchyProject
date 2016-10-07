package com.roxoft.hierarchy.httpClientExample;

public class PostObject {
	
	private int id;
	private long modifiedAt;
	private long createdAt;
	private int userId;
	private String token;
	private String role;
	private String jid;
	private int status;
	private String locale;
	private boolean cardPaymentEnabled;
	private String paymentProvider;
	private String unit;
	private String companyId;
	
	public int getId() {
		return id;
	}
	public long getModifiedAt() {
		return modifiedAt;
	}
	public long getCreatedAt() {
		return createdAt;
	}
	public int getUserId() {
		return userId;
	}
	public String getToken() {
		return token;
	}
	public String getRole() {
		return role;
	}
	public String getJid() {
		return jid;
	}
	public int getStatus() {
		return status;
	}
	public String getLocale() {
		return locale;
	}
	public boolean isCardPaymentEnabled() {
		return cardPaymentEnabled;
	}
	public String getPaymentProvider() {
		return paymentProvider;
	}
	public String getUnit() {
		return unit;
	}
	public String getCompanyId() {
		return companyId;
	}
	
	public void print(){
		System.out.println("id : "+ id);
		System.out.println("modifiedAt : "+ modifiedAt);
		System.out.println("createdAt : "+ createdAt);
		System.out.println("userId : "+ userId);
		System.out.println("token : "+ token);
		System.out.println("role : "+ role);
		System.out.println("jid : "+ jid);
		System.out.println("status : "+ status);
		System.out.println("locale : "+ locale);
		System.out.println("cardPaymentEnabled : "+ cardPaymentEnabled);
		System.out.println("paymentProvider : "+ paymentProvider);
		System.out.println("unit : "+ unit);
		System.out.println("companyId : "+ companyId);
	}
	
}
