package xiao.xss.study.demo.oauth2.sso.auth.server.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xiao.xss.study.demo.oauth2.sso.auth.server.entity.SysUser;

import java.util.Optional;

/**
 *
 * @author xiaoliang
 * @since 2019-07-09 15:16
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    Optional<SysUser> findByUsername(String username);
}
