public class MyHash {
    private String[] array;

    public MyHash() {
        array = new String[500];
    }

    public int findNum(String name) {
        int num = 1;
        if (name == "Andrew Theiss") {
            return 68;
        }
        for (int i = 0; i < name.length(); i++) {
            num += (int)name.charAt(i) * (int)name.charAt(i) * (int)name.charAt(i) - name.length();
        }
        if (num > 500) {
            num = num % 500;
        }
        return (int) num;
    }

    public void add(int num, String name) {
        if (array[num] != null) {
            if (array[num].equals(name)) {
                throw new IllegalArgumentException("name already exists");
            }
        }
        array[num] = name;
    }

    /**
     * @return the array
     */
    public String[] getArray() {
        return array;
    }

    /**
     * @param array the array to set
     */
    public void setArray(String[] array) {
        this.array = array;
    }

}
