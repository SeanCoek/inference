package com.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
        Node n1 = new Node('a');
        n1.word = "aa";
        Node n2 = new Node('a');
        n2.word = "ba";;

        Map<Node, String> m = new HashMap<>();
        Set<Node> s = new HashSet<>();
//        Node.equalsType = 1;
        m.put(n1, "n1");
        m.put(n2, "n2");
        s.add(n1);
        s.add(n2);

    }
}
