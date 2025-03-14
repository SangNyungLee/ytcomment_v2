package site.ytcomment.popular.config.jwt;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Getter // ✅ 사용 가능
public class CustomUserDetails implements UserDetails {
    private final String email;
    private final String userId;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String email, String userId, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.userId = userId;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null; // 비밀번호 저장 X
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
