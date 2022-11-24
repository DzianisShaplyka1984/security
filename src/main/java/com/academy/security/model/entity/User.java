package com.academy.security.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column
  private String username;
  @Column
  private String password;
  @Column
  private boolean accountNonExpired;
  @Column
  private boolean accountNonLocked;
  @Column
  private boolean credentialsNonExpired;
  @Column
  private boolean enabled;
  @Enumerated(EnumType.STRING)
  @ElementCollection(fetch = FetchType.EAGER)
  private List<Role> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    var authorities = new ArrayList<GrantedAuthority>();
    roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
    return authorities;
  }
}
