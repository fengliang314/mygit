package com.ssm.system.user.model;

public class User {
    private Long userId;

    private String userName;

    private String password;

    private Integer age;

    private String email;
    
    

    public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public User(Long userId, String userName, String password, Integer age, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.age = age;
		this.email = email;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", age=" + age
				+ ", email=" + email + "]";
	}
    
    
}