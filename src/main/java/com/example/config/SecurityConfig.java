package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Spring Securityの設定を有効にします
@EnableWebSecurity
// WebSecurityConfigurerAdapterを必ず継承しましょう
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 認可の設定
    http.authorizeRequests()
        .antMatchers("/loginForm", "/css/**").permitAll()
        .anyRequest().authenticated();
    http.formLogin()
        .loginProcessingUrl("/login") // ログイン処理のパス
        .loginPage("/loginForm") // ログインページの指定
        .usernameParameter("mailAddress") // ログインページのメールアドレス
        .passwordParameter("password") // ログインページのパスワード
        .defaultSuccessUrl("/menu", true) // ログイン成功後のパス
        .failureUrl("/loginForm?error"); // ログイン失敗時のパス

    http.logout()
        .logoutUrl("/logout") // ログアウト処理のパス
        .logoutSuccessUrl("/loginForm"); // ログアウト成功後のパス
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}