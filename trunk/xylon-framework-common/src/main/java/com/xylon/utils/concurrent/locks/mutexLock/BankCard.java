/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.concurrent.locks.mutexLock;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-29
 */
public class BankCard {	
	private String cardid = "XZ456789";
	private int balance = 10000;
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
}

