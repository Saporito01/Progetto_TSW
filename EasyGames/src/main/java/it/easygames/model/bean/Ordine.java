package it.easygames.model.bean;

public class Ordine {
	
	
	
	public Ordine() {
		this.codice = 0;
		this.data = "";
		this.ora = "";
		this.account = "";
	}
	
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getOra() {
		return ora;
	}
	public void setOra(String ora) {
		this.ora = ora;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}



	private int codice;
	private String data;
	private String ora;
	private String account;
}
