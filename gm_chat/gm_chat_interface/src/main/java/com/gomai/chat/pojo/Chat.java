package com.gomai.chat.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * 聊天表
 */
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ch_id")
    private Integer chId; //主键
    @Column(name = "ch_sent_id")
    private Integer chSentId; //发送人的id
    @Column(name = "ch_receive_id")
    private Integer chReceiveId; //接收人的id
    @Column(name = "ch_create_time")
    private Date chCreateTime;  //发送的时间
    @Column(name = "ch_type")
    private String chType;  // 消息的类型
    @Column(name = "ch_content")
    private String chContent;  // 消息的内容
    @Column(name = "g_id")
    private Integer gId; // 消息链接的gid
    @Column(name = "ch_recall")
    private String chRecall;  // 消息的撤回
    @Column(name = "ch_send_delete")
    private String chSendDelete;  // 发送方消息的删除
    @Column(name = "ch_receive_delete")
    private String chReceiveDelete;  // 接收方消息的删除

    public Chat() {
    }

    public Chat(Integer chId, Integer chSentId, Integer chReceiveId, Date chCreateTime, String chType, String chContent, Integer gId, String chRecall, String chSendDelete, String chReceiveDelete) {
        this.chId = chId;
        this.chSentId = chSentId;
        this.chReceiveId = chReceiveId;
        this.chCreateTime = chCreateTime;
        this.chType = chType;
        this.chContent = chContent;
        this.gId = gId;
        this.chRecall = chRecall;
        this.chSendDelete = chSendDelete;
        this.chReceiveDelete = chReceiveDelete;
    }

    public Integer getChId() {
        return chId;
    }

    public void setChId(Integer chId) {
        this.chId = chId;
    }

    public Integer getChSentId() {
        return chSentId;
    }

    public void setChSentId(Integer chSentId) {
        this.chSentId = chSentId;
    }

    public Integer getChReceiveId() {
        return chReceiveId;
    }

    public void setChReceiveId(Integer chReceiveId) {
        this.chReceiveId = chReceiveId;
    }

    public Date getChCreateTime() {
        return chCreateTime;
    }

    public void setChCreateTime(Date chCreateTime) {
        this.chCreateTime = chCreateTime;
    }

    public String getChType() {
        return chType;
    }

    public void setChType(String chType) {
        this.chType = chType;
    }

    public String getChContent() {
        return chContent;
    }

    public void setChContent(String chContent) {
        this.chContent = chContent;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getChRecall() {
        return chRecall;
    }

    public void setChRecall(String chRecall) {
        this.chRecall = chRecall;
    }

    public String getChSendDelete() {
        return chSendDelete;
    }

    public void setChSendDelete(String chSendDelete) {
        this.chSendDelete = chSendDelete;
    }

    public String getChReceiveDelete() {
        return chReceiveDelete;
    }

    public void setChReceiveDelete(String chReceiveDelete) {
        this.chReceiveDelete = chReceiveDelete;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chId=" + chId +
                ", chSentId=" + chSentId +
                ", chReceiveId=" + chReceiveId +
                ", chCreateTime=" + chCreateTime +
                ", chType='" + chType + '\'' +
                ", chContent='" + chContent + '\'' +
                ", gId=" + gId +
                ", chRecall='" + chRecall + '\'' +
                ", chSendDelete='" + chSendDelete + '\'' +
                ", chReceiveDelete='" + chReceiveDelete + '\'' +
                '}';
    }
}
