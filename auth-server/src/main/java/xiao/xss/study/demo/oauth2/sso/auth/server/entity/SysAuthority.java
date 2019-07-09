package xiao.xss.study.demo.oauth2.sso.auth.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import xiao.xss.study.demo.oauth2.sso.auth.server.common.AuthServer;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限
 *
 * @author xiaoliang
 * @since 2019-07-09 15:21
 */
@Data
@Entity
@Table(name = "sys_authority")
public class SysAuthority implements Serializable {
    private static final long serialVersionUID = AuthServer.SERIAL_VERSION_UID;

    @JsonIgnore
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15)
    private String code;
    @Column(length = 100)
    private String description;
}
