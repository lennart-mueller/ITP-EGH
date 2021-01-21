package Beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.LdapIdentityStoreDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/pages/public1/loginPage.xhtml",
                errorPage = "/pages/public1/loginError.xhtml"
        )
)
@LdapIdentityStoreDefinition(
        url = "ldap://131.173.88.192:389",
        callerBaseDn = "ou=users,dc=egh,dc=com",
        bindDn = "cn=admin,dc=egh,dc=com",
        bindDnPassword = "password",
        groupSearchBase = "ou=Roles,dc=egh,dc=com",
        callerNameAttribute ="cn"
        //groupSearchFilter = "(&(member=%s)(objectClass=groupOfNames))"
)

//@DatabaseIdentityStoreDefinition(
//        dataSourceLookup = "java:/SWP_EGH-2",
////		dataSourceLookup = "java:/MySQL_VS",
//        callerQuery = "select PASSWORD from egh_user where EMAIL=?",
//        groupsQuery = "USER",
//      //  groupsQuery = "select ROLENAME as GROUPNAME from t_user_roles where USERNAME=?",
// //       hashAlgorithm = PlainTextPasswordHash.class
//        hashAlgorithm = PlainSHA512PasswordHash.class
////        , useFor = IdentityStore.ValidationType.PROVIDE_GROUPS
//)

@ApplicationScoped
@Named
public class ApplicationSecurityConfig {
}
