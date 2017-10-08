package network;

import java.util.Collection;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.network.Cell;
import com.network.Node;

/**
 * Session Bean implementation class NetworkService
 */
@Stateless
public class NetworkService implements NetworkServiceLocal {

	@PersistenceContext(name="NetworkNodeConfigurationService")
    private EntityManager em;
	
//	@Interceptors(CacheInterceptor.class)
	public void addNode(Node node){
		em.persist(node);
	}
	
	public Long getNodeCount(){
		Query query = em.createQuery("SELECT count(*) FROM Node");
		Long nodeCount= (Long) query.getSingleResult();
		return nodeCount;
	}
	
	public Node getNodeById(int id){
		Query query = em.createQuery("SELECT distinct p from Node p join fetch p.cells r where p.id = :id");
		query.setParameter("id", id);
		Node nodeResult = (Node)query.getSingleResult();
		return nodeResult;
	}
	
	public Collection<Node> getAllNodes(){
		TypedQuery<Node> query = em.createQuery("SELECT distinct p from Node p join fetch p.cells r", Node.class);
		return query.getResultList();
	}

	public void updateNodeInDatabase(Node NodeToUpdate) {
		em.merge(NodeToUpdate);
	}
	
	public void removeNodeById(int id){
		Node node = getNodeById(id);
		em.remove(node);
	}
	
	public void populateTable(){
		Cell cell1 = new Cell("100", "B22");
		Cell cell2 = new Cell("22", "B33");
		Cell cell3 = new Cell("678", "B63");
		Cell cell4 = new Cell("321", "frf");
		Cell cell5 = new Cell("4", "sss");
		Cell cell6 = new Cell("90", "jjk");
		Node node1 = new Node(1001,"NodeX1", "Galway", 10.2, 32.8);
		Node node2 = new Node(1002,"NodeX2", "Dublin", 17.5, 38.1);
		Node node3 = new Node(1003,"NodeX143", "Tokyo", 45.2, 88.8);
		Node node4 = new Node(1004,"NodeXd32", "Seoul", 21.5, 69.1);
		node1.addCell(cell6);
		node1.addCell(cell5);
		node2.addCell(cell4);
		node3.addCell(cell3);
		node4.addCell(cell2);
		node4.addCell(cell1);
		addNode(node1);
		addNode(node2);
		addNode(node3);
		addNode(node4);
	}
}
