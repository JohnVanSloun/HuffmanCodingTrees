public class HuffmanCodeTree {
    private HuffmanNode root;

    /**
     * Initializes a new HuffmanCodeTree.
     * @param root the HuffmanNode being set as the root node of the tree (HuffmanNode).
     */
    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }

    /**
     * Initializes a new HuffmanCodeTree.
     * @param codebook the HuffmanCodeBook being used to
     */
    public HuffmanCodeTree(HuffmanCodeBook codebook) {
        root = new HuffmanNode(null, null);
        char character;

        for (int i = 0; i < codebook.getSize(); i++) {
            character = codebook.getChar(i);
            put(codebook.getSequence(character), character);
        }
    }

    /**
     * Checks if the HuffmanCodeTree is valid.
     * @return true if the code tree is valid and false if it is not (boolean).
     */
    public boolean isValid() {
        return root.isValidTree();
    }

    /**
     * Adds a new letter to the HuffmanCodeTree.
     * @param seq the BinarySequence used to navigate the tree and add the letter to the appropriate spot (BinarySequence).
     * @param letter the letter to be added to the HuffmanCodeTree (char).
     */
    public void put(BinarySequence seq, char letter) {
        HuffmanNode node = root;
        for (int i = 0; i < seq.size(); i++) {
            if (i == (seq.size() - 1)) {
                if (seq.get(i)) {
                    node.setOne(new HuffmanNode(letter));
                } else {
                    node.setZero(new HuffmanNode(letter));
                }
            } else if (seq.get(i)) {
                if (node.getOne() == null) {
                    node.setOne(new HuffmanNode(null, null));
                }
                node = node.getOne();
            } else {
                if (node.getZero() == null) {
                    node.setZero(new HuffmanNode(null, null));
                }
                node = node.getZero();
            }
        }
    }

    /**
     * Used to decode a binary sequence into a String
     * @param s the BinarySequence to be decoded (BinarySequence).
     * @return The String resulting from the decoded BinarySequence (String).
     */
    public String decode(BinarySequence s) {
        HuffmanNode node = root;
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < s.size(); i++) {
            if (node.isLeaf()) {
                output.append(node.getData());
                node = root;
                i--;
            } else if (s.get(i)){
                node = node.getOne();
            } else {
                node = node.getZero();
            }
        }

        return output.toString();
    }


}
