package xiao.xss.study.demo.oauth2.sso.auth.server.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xiao.xss.study.demo.oauth2.sso.auth.server.entity.SysAuthority;

/**
 *
 * @author xiaoliang
 * @since 2019-07-09 16:19
 */
@Repository
public interface SysAuthorityRepository extends JpaRepository<SysAuthority, Long> {
}
