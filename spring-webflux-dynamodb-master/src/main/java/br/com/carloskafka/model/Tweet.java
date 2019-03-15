package br.com.carloskafka.model;

import java.time.LocalDateTime;
import java.util.UUID;

public final class Tweet {
	public static final String TEXT = "Text";
	public static final String CREATED_AT = "CreatedAt";

	private String id;
	private String createdAt;
	private String teste;
	private String text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null && !id.isEmpty()) {
			this.id = id;
		} else {
			this.id = UUID.randomUUID().toString();
		}
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		if (createdAt != null && !createdAt.isEmpty()) {
			this.createdAt = createdAt;
		} else {
			this.createdAt = LocalDateTime.now().toString();
		}
	}

	public String getTeste() {
		return teste;
	}

	public void setTeste(String teste) {
		this.teste = teste;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", createdAt=" + createdAt + ", teste=" + teste + "]";
	}

}
