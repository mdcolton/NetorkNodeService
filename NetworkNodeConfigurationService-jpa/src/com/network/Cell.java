package com.network;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="cell")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="CELLS", schema="MYSCHEMA")
public class Cell {
	
	private int id;
	private String range;
	private String name;
	
	public Cell() {}
	
	public Cell(String range, String name) {
		this.range = range;
		this.name = name;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRange() {
		return range;
	}
	
	public void setRange(String range) {
		this.range = range;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Cell [id=" + id + ", range=" + range + ", name=" + name + "]";
	}
}
