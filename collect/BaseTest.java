package collect;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author zyh
 *
 */
public class BaseTest {

	public static void main(String[] args) {
		Collection<String> c = new LinkedList<String>();
		c.add("one"); c.add("two"); c.add("three");
		System.out.println(c);
		Iterator<String> i = c.iterator();
		// 想要连续remove两个，要先 next 再 remove
		i.next(); i.remove();
		i.next(); i.remove();
		System.out.println(c);
		
		Map<Integer, String> m = new HashMap<Integer, String>();
		m.put(1, "haha");
		Set<Integer> set = m.keySet();
		System.out.println(set);
		
		List<String> staff = new LinkedList<String>();
		staff.add("zyh");
		staff.add("zhz");
		staff.add("zjh");
		System.out.println(staff);
		Collections.sort(staff, Collections.reverseOrder());
		System.out.println(staff);
	}

}
