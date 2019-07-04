package com.lrm.po;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName FriendLink
 * @Description: TODO
 * @Author: bughua
 * @CreateDate: 2019/7/4 16:26
 */
@Entity
@Table(name = "t_friend_link")
public class FriendLink {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "链接名称不能为空")
    private String name;
    @NotBlank(message = "链接不能为空")
    private String link;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "FriendLink{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
