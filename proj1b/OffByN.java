public class OffByN implements CharacterComparator {

    public int number;
    public OffByN(int n) {
        this.number = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        if (diff == number) {
            return true;
        }
        else return false;
    }
}
