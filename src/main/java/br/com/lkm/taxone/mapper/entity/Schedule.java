package br.com.lkm.taxone.mapper.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.lkm.taxone.mapper.enums.ScheduleStatus;
import lombok.Data;

@Data
@Entity
public class Schedule {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@Enumerated(EnumType.STRING)
	private ScheduleStatus status;

	private String days;

	private String hours;

	private LocalDateTime lastExecution;

	@JoinTable(name = "schedule_safxtable", joinColumns = @JoinColumn(name = "SCHEDULE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "SAFX_TABLE_ID", referencedColumnName = "ID"))
	@ManyToMany
	private List<SAFXTable> safxTables;

	@OneToMany(mappedBy = "schedule")
	private List<Criteria> criterias;

	@ManyToOne
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(ScheduleStatus status) {
		this.status = status;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public LocalDateTime getLastExecution() {
		return lastExecution;
	}

	public void setLastExecution(LocalDateTime lastExecution) {
		this.lastExecution = lastExecution;
	}

	public List<SAFXTable> getSafxTables() {
		return safxTables;
	}

	public void setSafxTables(List<SAFXTable> safxTables) {
		this.safxTables = safxTables;
	}

	public List<Criteria> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<Criteria> criterias) {
		this.criterias = criterias;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", name=" + name + "]";
	}
	
	

}
