package com.gomai.chat.pojo;

import javax.persistence.*;

/**
 * 聊天列表
 */
@Entity
@Table(name = "chat_list")
public class ChatList {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "chl_id")
    private Integer chlId; //主键
    @Column(name = "chl_own_id")
    private Integer chlOwnId; //自己的id
    @Column(name = "chl_other_id")
    private Integer chlOtherId; //对方的id
    @Column(name = "chl_delete")
    private String chlDelete;  // 是否删除的状态

    public ChatList() {
    }

    public ChatList(Integer chlId, Integer chlOwnId, Integer chlOtherId, String chlDelete) {
        this.chlId = chlId;
        this.chlOwnId = chlOwnId;
        this.chlOtherId = chlOtherId;
        this.chlDelete = chlDelete;
    }

    public Integer getChlId() {
        return chlId;
    }

    public void setChlId(Integer chlId) {
        this.chlId = chlId;
    }

    public Integer getChlOwnId() {
        return chlOwnId;
    }

    public void setChlOwnId(Integer chlOwnId) {
        this.chlOwnId = chlOwnId;
    }

    public Integer getChlOtherId() {
        return chlOtherId;
    }

    public void setChlOtherId(Integer chlOtherId) {
        this.chlOtherId = chlOtherId;
    }

    public String getChlDelete() {
        return chlDelete;
    }

    public void setChlDelete(String chlDelete) {
        this.chlDelete = chlDelete;
    }

    @Override
    public String toString() {
        return "ChatList{" +
                "chlId=" + chlId +
                ", chlOwnId=" + chlOwnId +
                ", chlOtherId=" + chlOtherId +
                ", chlDelete='" + chlDelete + '\'' +
                '}';
    }
}
