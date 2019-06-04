package xyz.guqing.goblin.entity;

import java.io.Serializable;
import java.util.Date;

public class BlogArticleVO implements Serializable {
    private Integer id;
    
    private String title;
    
    private String categoryId;
    
    private String sourceUrl;
    
    private String summary;
    
    private String coverUrl;
    
    private String keyword;
    
    private String articleIdArray;
    
    private Integer clickCount;
    
    private Integer sortIndex;
    
    private Integer commendFlag;
    
    private Integer commentFlag;
    
    private String tag;
    
    private Integer commentCount;
    
    private Integer shareCount;
    
    private Integer lastCommentTime;
    
    private String ip;
    
    private Integer source;
    
    private Integer visible;
    
    private Integer status;
    
    private String extraCid;
    
    private Date createTime;
    
    private Date modifyTime;
    
    private String content;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getSourceUrl() {
        return sourceUrl;
    }
    
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public String getCoverUrl() {
        return coverUrl;
    }
    
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
    
    public String getKeyword() {
        return keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public String getArticleIdArray() {
        return articleIdArray;
    }
    
    public void setArticleIdArray(String articleIdArray) {
        this.articleIdArray = articleIdArray;
    }
    
    public Integer getClickCount() {
        return clickCount;
    }
    
    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }
    
    public Integer getSortIndex() {
        return sortIndex;
    }
    
    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }
    
    public Integer getCommendFlag() {
        return commendFlag;
    }
    
    public void setCommendFlag(Integer commendFlag) {
        this.commendFlag = commendFlag;
    }
    
    public Integer getCommentFlag() {
        return commentFlag;
    }
    
    public void setCommentFlag(Integer commentFlag) {
        this.commentFlag = commentFlag;
    }
    
    public String getTag() {
        return tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    public Integer getCommentCount() {
        return commentCount;
    }
    
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
    
    public Integer getShareCount() {
        return shareCount;
    }
    
    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }
    
    public Integer getLastCommentTime() {
        return lastCommentTime;
    }
    
    public void setLastCommentTime(Integer lastCommentTime) {
        this.lastCommentTime = lastCommentTime;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public Integer getSource() {
        return source;
    }
    
    public void setSource(Integer source) {
        this.source = source;
    }
    
    public Integer getVisible() {
        return visible;
    }
    
    public void setVisible(Integer visible) {
        this.visible = visible;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getExtraCid() {
        return extraCid;
    }
    
    public void setExtraCid(String extraCid) {
        this.extraCid = extraCid;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getModifyTime() {
        return modifyTime;
    }
    
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Override
    public String toString() {
        return "BlogArticleVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", summary='" + summary + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", keyword='" + keyword + '\'' +
                ", articleIdArray='" + articleIdArray + '\'' +
                ", clickCount=" + clickCount +
                ", sortIndex=" + sortIndex +
                ", commendFlag=" + commendFlag +
                ", commentFlag=" + commentFlag +
                ", tag='" + tag + '\'' +
                ", commentCount=" + commentCount +
                ", shareCount=" + shareCount +
                ", lastCommentTime=" + lastCommentTime +
                ", ip='" + ip + '\'' +
                ", source=" + source +
                ", visible=" + visible +
                ", status=" + status +
                ", extraCid='" + extraCid + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", content='" + content + '\'' +
                '}';
    }
}
