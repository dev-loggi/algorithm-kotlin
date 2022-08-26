package swExpertAcademy.professional;

public class SegmentTree {
    private int size = 0;
    private int[] tree = new int[500_000];

    public void build(int[] arr, int size) {
        this.size = size;

        buildRec(arr, 1, 0, size - 1);
    }

    private int buildRec(int[] arr, int node, int nodeLeft, int nodeRight) {
        if (nodeLeft == nodeRight)
            return tree[node] = arr[nodeLeft];

        int mid = (nodeLeft + nodeRight) / 2;
        int leftVal = buildRec(arr, node * 2, nodeLeft, mid);
        int rightVal = buildRec(arr, node * 2 + 1, mid + 1, nodeRight);

        return tree[node] = leftVal + rightVal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1, e = 2; i < 32; i++) {
            if (i == e) { sb.append("\n"); e <<= 1; }
            sb.append(tree[i]).append(' ');
        }
        return sb.toString();
    }
}