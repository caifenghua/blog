package com.lrm.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName AccessIp
 * @Description:
 * @Author: bughua
 * @CreateDate: 2019/10/22 9:38
 */
@Entity
@Table(name = "t_access_ip")
public class AccessIp {
    @Id
    @GeneratedValue
    private Long id;
    private String ip;
    private Date create_at;

    public void setId(Long id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public Date getCreate_at() {
        return create_at;
    }

    @Override
    public String toString() {
        return "AccessIp{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", create_at=" + create_at +
                '}';
    }
}
