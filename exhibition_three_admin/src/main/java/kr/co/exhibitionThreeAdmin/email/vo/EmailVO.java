package kr.co.exhibitionThreeAdmin.email.vo;

public class EmailVO {
	private String toAddress,fromAddress,subject,message;

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "EmailVO [toAddress=" + toAddress + ", fromAddress=" + fromAddress + ", subject=" + subject
				+ ", message=" + message + "]";
	}
	
	
}
