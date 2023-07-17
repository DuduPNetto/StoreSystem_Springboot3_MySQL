package com.eduardonetto.main.controllers.query;

import java.util.Objects;

public class Token {

	private String content;

	public Token() {
	}

	public Token(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		return Objects.equals(content, other.content);
	}

	@Override
	public String toString() {
		return "Token [content=" + content + "]";
	}

}
