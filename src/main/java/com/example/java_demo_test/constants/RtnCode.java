package com.example.java_demo_test.constants;

//新增package為constants->new enum
//return簡寫Rtn, enum列舉
//建完專案後先至properties 更改設定為UTF8避免亂碼 ＊Http狀態碼
public enum RtnCode {
	// 寫括號內的兩個屬性在下方並getter and setter與有參數之建構方法
	// 常數名稱不可重複 裡面代碼可以重複 未來Ｍessage可一次修改
	SUCCESSFUL("200", "Successful"), 
	CANNOT_EMPTY("400", "Account or password is empty"),
	DATA_ERROR("400", "Account or password is error"),
	NOT_FOUND("404","Not found");

	private String code;

	private String message;

	private RtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
