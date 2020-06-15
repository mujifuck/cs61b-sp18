public class Palindrome {
    /**  Gives a string and returns a Deque where the characters appear in the same order as in the String. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordToDeque = new ArrayDeque<>();
        for (int i = 0;i < word.length();i ++) {
            wordToDeque.addLast(word.charAt(i));
        }
        return wordToDeque;
    }

    /** Returns true if the given word is a palindrome, and false otherwise.
     * tip:Don’t use the get method of Deque. That will just make things unnecessarily complicated.
     * tip:Consider recursion. There’s a really beautiful solution that uses recursion.
     * You’ll need to create a private helper method for this to work.
     * */
    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d);
    }
    private boolean isPalindromeHelper(Deque d) {
        if (d.size() <= 1) {
            return true;
        }
        else if (d.removeFirst() != d.removeLast()) {
            return false;
        }
        else {
            return isPalindromeHelper(d);
        }
    }

    /** The method will return true if the word is a palindrome according to the character comparison test provided by
     * the CharacterComparator passed in as argument cc.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        int len = word.length();
        int count = 0;
        char[] ss = word.toCharArray();
        if (len % 2 == 0) {
            count = len / 2;
        }
        else {
            count = (len - 1) / 2;
        }
        if (len <= 1) {
            return false;
        }
        else {
            for (int i = 0;i < count;i ++) {
                if (cc.equalChars(ss[i],ss[len - i - 1]) == false) {
                    return false;
                }
            }
        }
        return true;
    }
}