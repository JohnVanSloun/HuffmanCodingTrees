public class HuffmanNode {
    private HuffmanNode zero;
    private HuffmanNode one;
    private Character data;

    /**
     * Initializes a non-leaf HuffmanNode.
     * @param zero the HuffmanNode which corresponds with the zero binary value (HuffmanNode).
     * @param one the HuffmanNode which corresponds with the one binary value (HuffmanNode).
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.zero = zero;
        this.one = one;
        data = null;
    }

    /**
     * Initializes a leaf HuffmanNode.
     * @param data the character being strored in the node (char)
     */
    public HuffmanNode(char data) {
        zero = null;
        one = null;
        this.data = data;
    }

    public HuffmanNode getZero() {
        return zero;
    }

    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    public HuffmanNode getOne() {
        return one;
    }

    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    public Character getData() {
        return data;
    }

    public void setData(Character data) {
        this.data = data;
    }

    /**
     * Checks if the node is a leaf or not.
     * @return true if the HuffmanNode is a leaf node (boolean)
     */
    public boolean isLeaf() {
        if (data != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the HuffmanNode is a valid node.
     * @return true if the HuffmanNode is a node and false if it is not (boolean).
     */
    public boolean isValidNode() {
        if (isLeaf() && zero == null && one == null) {
            return true;
        } else if (!isLeaf() && zero != null && one != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the current node and all descendant nodes are valid.
     * @return true if current and all descendant nodes are valid and false if not (boolean).
     */
    public boolean isValidTree() {
        if (isLeaf() && isValidNode()) {
            return true;
        } else if (isValidNode() && getZero().isValidNode() && getOne().isValidNode()) {
            return true;
        } else {
            return false;
        }
    }


}
