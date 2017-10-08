package network;

import java.util.Collection;

import javax.ejb.Local;

import com.network.Node;

@Local
public interface NetworkServiceLocal {
	
	public Long getNodeCount();
	
	public void populateTable();
	
	public Node getNodeById(int id);
	
	public Collection<Node> getAllNodes();
	
	public void addNode(Node node);
	
	public void removeNodeById(int id);
	
	public void updateNodeInDatabase(Node NodeToUpdate);

}
