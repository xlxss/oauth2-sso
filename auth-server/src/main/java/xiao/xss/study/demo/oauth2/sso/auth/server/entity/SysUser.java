package xiao.xss.study.demo.oauth2.sso.auth.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import xiao.xss.study.demo.oauth2.sso.auth.server.common.AuthServer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 用户
 *
 * @author xiaoliang
 * @since 2019-07-09 13:47
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = AuthServer.SERIAL_VERSION_UID;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String username;
    @JsonIgnore
    @Column(length = 64)
    private String password;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String email;
    @Column(length = 13)
    private String mobile;
    private boolean active;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_authority", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"),
            foreignKey = @ForeignKey(name = "fk_user_authority_1"), inverseForeignKey = @ForeignKey(name = "fk_user_authority_2"))
    private Set<SysAuthority> authorities;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createTime;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updateTime;
}
