package com.i2i.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.i2i.model.User;

@Table(name="Payment")
@Entity
public class Payment {

	private int id;
	private String paymentType;
	private Long  cardNumber;
	private String nameOnCard;
	private int expiryMonth;
	private int expiryYear;
	private int cvvNumber;
	private User user;
	
	public Payment() {
	}
	
	public Payment(User user, String paymentType, Long cardNumber, String nameOnCard, int expiryMonth,
			int expiryYear, int cvvNumber) {
		
		this.paymentType = paymentType;
		this.cardNumber = cardNumber;
		this.nameOnCard = nameOnCard;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.cvvNumber = cvvNumber;
		this.user = user;
	}

	@Id
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="paymentType")
	public String getPaymentType() {
		return paymentType;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	@Column(name="cardNumber")
	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Column(name="nameOnCard")
	public String getNameOnCard() {
		return nameOnCard;
	}
	
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	@Column(name="expiryMonth")
	public int getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	@Column(name="expiryYear")
	public int getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}

	@Column(name="cvvNumber")
	public int getCvvNumber() {
		return cvvNumber;
	}

	public void setCvvNumber(int cvvNumber) {
		this.cvvNumber = cvvNumber;
	}
	
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Payment [paymentType=" + paymentType + ", cardNumber=" + cardNumber + ", nameOnCard=" + nameOnCard
				+ ", expiryMonth=" + expiryMonth + ", expiryYear=" + expiryYear + ", cvvNumber=" + cvvNumber + "]";
	}
	
	
}
