package com.github.Duan.Consumer.shiro;

import com.github.Duankan.po.PermissionPo;
import com.github.Duankan.po.RolePo;
import com.github.Duankan.po.UsersPo;
import com.github.Duankan.service.IPermission;
import com.github.Duankan.service.IRole;
import com.github.Duankan.service.IUsers;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义的realm
 */
public class TestRealm extends AuthorizingRealm {
    @Autowired
    IUsers iUsers;
    @Autowired
    IRole iRole;
//    @Autowired
//    IPermission iPermission;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username= (String) principalCollection.getPrimaryPrincipal();
        UsersPo usersPo =iUsers.getUserByUsername(username);
        List<RolePo> rs =iRole.getRoleByUserid(usersPo.getId());
        Set<String> roles=new HashSet<>();
        for (RolePo r:rs){
            roles.add(r.getRolename());
        }
//        List<PermissionPo> permissionPos=iPermission.getPermissionByRoleid(usersPo.getId());
//        Set<String> ps =new HashSet<>();
//        for (PermissionPo po:permissionPos){
//            ps.add(po.getPermissionname());
//        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
//        simpleAuthorizationInfo.setStringPermissions(ps);
        return simpleAuthorizationInfo;
    }

    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName= (String) authenticationToken.getPrincipal();
        UsersPo usersPo = iUsers.getUserByUsername(userName);
        String password=null;
        if(usersPo!=null){
            password=usersPo.getPassword();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,password,"testRealm");
        return authenticationInfo;
    }
}
