package com.network;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="node")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="NODES", schema="MYSCHEMA")
public class Node implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private int id = -1;
	private String name;
	private String location;
	private double latitude;
	private double longitude;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="NodeCellJoin")
	private Collection<Cell> cells = new HashSet<>();
	
	public Node() {}
	
	public Node(int id,String name, String location, double latitude, double longitude) {
		this.id=id;
		this.name = name;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public void addCell(Cell cell){
		cells.add(cell);
	}
	
	public Collection<Cell> getCells(){
		return cells;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", name=" + name + ", location=" + location + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", cells=" + cells + "]";
	}
}
