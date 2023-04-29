public class HuffmanCodeBook {
    private char[] characters;
    private BinarySequence[] sequences;
    private int size;

    /**
     * initializes a new HuffmanCodeBook.
     */
    public HuffmanCodeBook() {
        characters = new char[10];
        sequences = new BinarySequence[10];
        size = 0;
    }

    /**
     * Adds a new character and its binary encoding to the HuffmanCodeBook.
     * @param c the character being entered for the encoding (char).
     * @param seq the BinarySequence being entered for the encoding to the code book (BinarySequence).
     */
    public void addSequence(char c, BinarySequence seq) {
        if (size == characters.length) {
            char[] newChars = new char[size * 2];
            BinarySequence[] newSeq = new BinarySequence[size * 2];
            for (int i = 0; i < characters.length; i++) {
                newChars[i] = characters[i];
                newSeq[i] = sequences[i];
            }

            characters = newChars;
            sequences = newSeq;
        }

        characters[size] = c;
        sequences[size] = seq;
        size++;

        sortData();
    }

    /**
     * sorts the data in the codebook upon new data being added
     */
    private void sortData() {
        char tempChar;
        BinarySequence tempSeq;
        for (int i = size-1; i > 0; i--) {
            if (characters[i] < characters[i-1]) {
                tempChar = characters[i-1];
                characters[i-1] = characters[i];
                characters[i] = tempChar;

                tempSeq = sequences[i-1];
                sequences[i-1] = sequences[i];
                sequences[i] = tempSeq;
            }
        }
    }

    /**
     * Searches for the index of a character contained in the codebook
     * @param c the character being searched for (char).
     * @return the index of the character if it is found or -1 if not found (int).
     */
    private int search(char c) {
        int left = 0;
        int right = size-1;
        int middle = (right + left) / 2;

        while (left <= right) {
            if (c == characters[middle]) {
                return middle;
            } else if (c < characters[middle]) {
                right = middle - 1;
                middle = (right + left) / 2;
            } else {
                left = middle + 1;
                middle = (right + left) / 2;
            }
        }

        return -1;
    }

    /**
     * Checks if the HuffmanCodeBook contains a certain letter and its encoding.
     * @param letter the letter that is being checked for in the code book (char).
     * @return true if the letter is contained in the code book and false if not.
     */
    public boolean contains(char letter) {
        return search(letter) > -1;
    }

    /**
     * Checks if the HuffmanCodeBook contains all letters in a given string and their encodings.
     * @param letters the series of letters that are being checked for in the code book (String).
     * @return true if all charcters in the string are contained in the code book and false if not.
     */
    public boolean containsAll(String letters) {
        for (int i = 0; i < letters.length(); i++) {
            if (!contains(letters.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @return the size of the HuffmanCodebook (int)
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param index the index at which a characted should be located (int)
     * @return A character from the HuffmanCodebook at a certain index (char).
     */
    public char getChar(int index) {
        return characters[index];
    }


    /**
     * Gets the BinarySequence encoding for a given letter from the codebook.
     * @param c the letter being used to find its encoding sequence (char).
     * @return the BinarySequence encoding of the letter if it is in the code book or null if it is not in the code book.
     */
    public BinarySequence getSequence(char c) {
        int index = search(c);

        if (index > -1) {
            return sequences[index];
        } else {
            return null;
        }
    }

    /**
     * Encodes a string using the characters and their encoded sequences stored in the code book.
     * @param s the string being encoded (String).
     * @return the BinarySequence of the encoded string.
     */
    public BinarySequence encode(String s) {
        BinarySequence seq = new BinarySequence();

        for (int i = 0; i < s.length(); i++) {
            seq.append(getSequence(s.charAt(i)));
        }

        return seq;
    }


}
