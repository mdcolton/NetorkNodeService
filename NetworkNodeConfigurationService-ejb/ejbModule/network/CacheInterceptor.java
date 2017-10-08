package network;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.network.Node;
import com.network.NodeCache;

// Commented out to fix arquillian tests
//@Interceptor
public class CacheInterceptor {
	
	private static NodeCache nodeCache = new NodeCache();
	
	// Commented out to fix arquillian tests
	//@AroundInvoke
	public Object cache(InvocationContext ctx) throws Exception{
		System.out.println("Intercept Called");
		Object[] params = ctx.getParameters();
		Node node = (Node) params[0];
		nodeCache.addNodeToCache(node);
		Object result = ctx.proceed();
		return result;
	}

}
