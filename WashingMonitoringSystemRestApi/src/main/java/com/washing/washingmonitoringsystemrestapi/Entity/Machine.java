package com.washing.washingmonitoringsystemrestapi.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Table(name="machines")
public class Machine {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	

	@Column(name="heat")
	@NotEmpty(message="bos birakilamaz")
	@Setter
	public float heat;
	
	@Column(name="drum_speed")
	@NotEmpty(message="bos birakilamaz")
	@Setter
	public float drumSpeed;
	
	@Column(name="level")
	@NotEmpty(message="bos birakilamaz")
	@Setter
	public float level;
	@Setter
	@Column(name="is_open_the_door")
	public boolean isopenthedoor;
	public int getId() {
		return id;
	}
	public float getHeat() {
		return heat;
	}
	public void setHeat(float heat) {
		this.heat = heat;
	}
	public float getDrumSpeed() {
		return drumSpeed;
	}
	public void setDrumSpeed(float drumSpeed) {
		this.drumSpeed = drumSpeed;
	}
	public float getLevel() {
		return level;
	}
	public void setLevel(float level) {
		this.level = level;
	}
	public boolean isopenthedoor() {
		return isopenthedoor;
	}
	public void setIsopenthedoor(boolean isopenthedoor) {
		this.isopenthedoor = isopenthedoor;
	}

}
