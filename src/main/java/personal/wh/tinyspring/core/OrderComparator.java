package personal.wh.tinyspring.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderComparator implements Comparator<Object> {
	
	private static final Comparator<Object> INSTANCE = new OrderComparator();

	@Override
	public int compare(Object o1, Object o2) {
		int order1 = getOrder(o1);
		int order2 = getOrder(o2);
		return (order1 < order2) ? -1 : (order1 > order2) ? 1 : 0;
	}
	
	public int getOrder(Object o) {
		return (o instanceof Ordered) ? ((Ordered) o).getOrder() : Ordered.LOWEST_PRECEDENCE;
	}
	
	public static void sort(List<?> list) {
		if (list != null && !list.isEmpty()) {
			Collections.sort(list, INSTANCE);
		}
	}

}
