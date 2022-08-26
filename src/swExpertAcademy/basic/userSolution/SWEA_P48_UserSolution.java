package swExpertAcademy.basic.userSolution;

import swExpertAcademy.basic.SWEA_P48;

import java.util.HashMap;

public class SWEA_P48_UserSolution implements SWEA_P48.UserSolution {

    static class Trie {

        static class Node {
            int count = 0;
            HashMap<Character, Node> children = new HashMap<>();
        }

        private final Node root = new Node();

        public int add(char[] text) {
            Node curr = root;

            for (int i = 0; text[i] != '\0'; i++) {
                Node next = curr.children.get(text[i]);

                if (next == null) {
                    next = new Node();
                    curr.children.put(text[i], next);
                }

                curr = next;
            }

            return ++curr.count;
        }

        public int remove(char[] text) {
            return traverse(text, 0, root, true);
        }

        public int search(char[] text) {
            return traverse(text, 0, root, false);
        }

        private int traverse(char[] text, int idx, Node parent, boolean isRemove) {
            if (text[idx] == '\0') {
                int result = parent.count;
                if (isRemove) parent.count = 0;
                return result;
            }
            if (text[idx] == '?') {
                int sum = 0;
                for (Node child : parent.children.values()) {
                    sum += traverse(text, idx + 1, child, isRemove);
                }
                return sum;
            } else {
                Node child = parent.children.get(text[idx]);
                if (child != null) {
                    return traverse(text, idx + 1, child, isRemove);
                } else {
                    return 0;
                }
            }
        }
    }

    private Trie trie;

    @Override
    public void init() {
        trie = new Trie();
    }

    @Override
    public int add(char[] str) {
        return trie.add(str);
    }

    @Override
    public int remove(char[] str) {
        return trie.remove(str);
    }

    @Override
    public int search(char[] str) {
        return trie.search(str);
    }
}
