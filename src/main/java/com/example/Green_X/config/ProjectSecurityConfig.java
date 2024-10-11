package com.example.Green_X.config;




//@Configuration
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true,
//        securedEnabled = true
//        )
public class ProjectSecurityConfig  {

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)throws Exception{
//        http.csrf().disable()   //disable by spring security is given becuse that enable block the post,put , delete request
//                .authorizeRequests()
//                .antMatchers("/api/v1/account/my-account").authenticated()             //.hasRole("ADMIN") //meka standed kramarak .database ekh role column ekh ROLE_ADMIN kiyala thiyenna one //.hasAuthority("admin")     //.authenticated() // this method is acessed only admin
//                .antMatchers("/api/v1/loan/my-loan").authenticated()                   //.hasRole("USER")//database ekh role column ekh ROLE_USER kiyala thiyenna one  //.hasAuthority("user") // this method is acessed only user
//                .antMatchers("/api/v1/user/user-register","/api/v1/user/notice").permitAll()
//                .and().formLogin().and().httpBasic();
//
//        return http.build();
//    }
//
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//
//        return new BCryptPasswordEncoder();
//    }


}
