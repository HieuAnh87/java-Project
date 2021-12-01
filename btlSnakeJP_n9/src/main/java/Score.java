public class Score implements Comparable<Score> {
    private String name;
    private int score;

    public Score(String name, int score) {
        chuanHoa(name);
        this.score = score;
    }
    private void chuanHoa(String s) {
        name = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1);
    }
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    @Override
    public String toString() {
        return name + " " + score;
    }
    @Override
    public int compareTo(Score o) {
        if (this.score > o.score) {
            return -1;
        } else if (this.score < o.score) {
            return 1;
        } else {
            return this.name.compareTo(o.name);
        }
    }
}
