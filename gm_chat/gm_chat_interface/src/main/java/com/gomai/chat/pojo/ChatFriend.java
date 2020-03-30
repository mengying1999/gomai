package com.gomai.chat.pojo;

import javax.persistence.*;

/**
 * 好友表
 */
@Entity
@Table(name = "chat_friend")
public class ChatFriend {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cf_id")
    private Integer cfId; //主键
    @Column(name = "cf_friend_id")
    private Integer cfFriendId; //好友的id
    @Column(name = "cf_own_id")
    private Integer cfOwnId; //自己的id
    @Column(name = "cf_delete")
    private String cfDelete; //删除的状态

    public ChatFriend() {
    }

    public ChatFriend(Integer cfId, Integer cfFriendId, Integer cfOwnId, String cfDelete) {
        this.cfId = cfId;
        this.cfFriendId = cfFriendId;
        this.cfOwnId = cfOwnId;
        this.cfDelete = cfDelete;
    }

    public Integer getCfId() {
        return cfId;
    }

    public void setCfId(Integer cfId) {
        this.cfId = cfId;
    }

    public Integer getCfFriendId() {
        return cfFriendId;
    }

    public void setCfFriendId(Integer cfFriendId) {
        this.cfFriendId = cfFriendId;
    }

    public Integer getCfOwnId() {
        return cfOwnId;
    }

    public void setCfOwnId(Integer cfOwnId) {
        this.cfOwnId = cfOwnId;
    }

    public String getCfDelete() {
        return cfDelete;
    }

    public void setCfDelete(String cfDelete) {
        this.cfDelete = cfDelete;
    }

    @Override
    public String toString() {
        return "ChatFriend{" +
                "cfId=" + cfId +
                ", cfFriendId=" + cfFriendId +
                ", cfOwnId=" + cfOwnId +
                ", cfDelete='" + cfDelete + '\'' +
                '}';
    }
}
