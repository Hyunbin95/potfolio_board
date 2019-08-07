package kr.hb.board.domain;

public class Reply {
	private int rno;
	//Date 타입은 연산을 할 때는 편리하지만 출력을 할 때는 불편해서 String으로 
	private String regdate;
	//출력할 날짜 포맷을 저장할 프로퍼티
	private String datedisp;
	private String email;
	private String nickname;
	private String replytext;
	private int bno;
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getDatedisp() {
		return datedisp;
	}
	public void setDatedisp(String datedisp) {
		this.datedisp = datedisp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	
	
	public String getReplyText() {
		return replytext;
	}
	public void setReplyText(String replyText) {
		this.replytext = replyText;
	}
	@Override
	public String toString() {
		return "Reply [rno=" + rno + ", regdate=" + regdate + ", datedisp=" + datedisp + ", email=" + email
				+ ", nickname=" + nickname + ", replyText=" + replytext + ", bno=" + bno + "]";
	}

	
	
}
