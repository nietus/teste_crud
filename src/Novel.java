public class Novel {
    private String ch_content;
    private int ch_id;
    private static int next_id = 1;

    public Novel() {
        this.ch_content = "";
        this.ch_id = next_id;
        next_id++;
    }

    public Novel(String ch_content) {
        this.ch_content = ch_content;
        this.ch_id = next_id;
        next_id++;
    }

    public void setContent(String ch_content) {
        this.ch_content = ch_content;
    }

    public String getContent() {
        return ch_content;
    }

    public int getId() {
        return ch_id;
    }
    
    public void setId(int newId) {
    	this.ch_id = newId;
    }

    public int getNext_id() {
        return next_id;
    }

    @Override
    public String toString() {
        return "ch_content=" + ch_content + ", ch_id=" + (ch_id - 1);
    }
}