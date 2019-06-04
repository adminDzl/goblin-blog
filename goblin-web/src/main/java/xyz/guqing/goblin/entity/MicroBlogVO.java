package xyz.guqing.goblin.entity;

import java.util.ArrayList;
import java.util.List;

public class MicroBlogVO {
    private Microblog microblog;
    private List<MicroblogComment> comment = new ArrayList<>();
    
    public Microblog getMicroblog() {
        return microblog;
    }
    
    public void setMicroblog(Microblog microblog) {
        this.microblog = microblog;
    }
    
    public List<MicroblogComment> getComment() {
        return comment;
    }
}
