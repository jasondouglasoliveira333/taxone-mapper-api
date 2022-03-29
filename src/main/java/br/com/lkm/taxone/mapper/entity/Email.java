package br.com.lkm.taxone.mapper.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.lkm.taxone.mapper.enums.EmailType;

@Entity
@Table(name = "email")
public class Email {

	@Id
	@GeneratedValue
	private Integer id;

	private String email;

	private EmailType type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmailType getType() {
		return type;
	}

	public void setType(EmailType type) {
		this.type = type;
	}

}
