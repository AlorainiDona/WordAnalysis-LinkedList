public class WordOccurrence {
        private int lineNo;
        private int position;

        public WordOccurrence() {
            lineNo = 0;
            position = 0;
        }

        public WordOccurrence(int lineNo, int position) {
            this.lineNo = lineNo;
            this.position = position;
        }

        @Override
        public String toString() {
            return "(Line: " + lineNo + ", Position: " + position + ")";
        }

    public int getLineNo() {
        return lineNo;
    }

    public int getPosition() {
        return position;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
