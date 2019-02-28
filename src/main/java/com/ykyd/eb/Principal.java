package com.ykyd.eb;

import java.io.Serializable;

public class Principal implements Serializable{

	/** serialVersionUID */
	private static final long serialVersionUID = -4995314050758441930L;
	/** ID */
    private Long id;

    /** 用户名 */
    private String username;

    public Principal(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 重写toString方法
     * 
     * @return 用户名
     */
    @Override
    public String toString() {
        return getUsername();
    }
}
