import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
 
public class AutocompleteSystem {
	
	class Node {
		int cnt;
		String content;
		private Node(int cnt, String content) {
			this.cnt = cnt;
			this.content = content;
		}
	}
	
	StringBuilder sb = new StringBuilder();
	
	TreeMap<Node, String> map = new TreeMap<Node, String>(new Comparator<Node>(){
		@Override
		public int compare(Node o1, Node o2) {
			if(o1.cnt == o2.cnt)	return o1.content.compareTo(o2.content);
			return o2.cnt - o1.cnt;
		}
	});
	Map<String, Node> m = new HashMap<String, Node>();
 
    public AutocompleteSystem(String[] sentences, int[] times) {
        for(int i=0; i<sentences.length; i++) {
        	Node t = new Node(times[i], sentences[i]);
        	map.put(t, sentences[i]);
        	m.put(sentences[i], t);
        }
    }
    
    public List<String> input(char c) {
    	if(c == '#') {
        	String s = sb.toString();
        	if(m.containsKey(s)) {
        		Node t = m.get(s);
        		map.remove(t);
        		t.cnt  = t.cnt + 1;
        		map.put(t, t.content);
        	} else {
        		Node t = new Node(1, s);
        		m.put(s, t);
        		map.put(t, s);
        	}
        	
        	sb = new StringBuilder();
        	return new ArrayList<String>();
        } 
        
    	if(c != '#')	sb.append(c);
        List<String> ret = new ArrayList<String>();
        String prefix = sb.toString();
        for(Node k : map.keySet()) {
        	if(k.content.startsWith(prefix)) {
        		ret.add(k.content);
        		if(ret.size() == 3)	break;
        	}
        }
        
        return ret;
    }
}
 
