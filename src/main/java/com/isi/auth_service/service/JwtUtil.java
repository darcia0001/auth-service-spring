package com.isi.auth_service.service;

import java.util.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.isi.auth_service.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUtil {
    final  Algorithm algorithm = Algorithm.HMAC256("baeldung");


        public  boolean validate(String token){
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("ISI")
                    .build();

            try{
                DecodedJWT decodedJWT = verifier.verify(token);
                System.out.println("decodedJWT--->"+decodedJWT);
                if (decodedJWT!=null){
                    return  true;
                }else{
                    return  false;
                }

            }catch (Exception e){
                return  false;
            }




        }
        public Map<String, Claim> getClaims(String  token ){
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("ISI")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);

            Claim claim = decodedJWT.getClaim("userId");
            return decodedJWT.getClaims();


        }

        public  String getToken(User user){
            String jwtToken = JWT.create()
                    .withIssuer("ISI")
                    .withSubject("ISI Details")
                    .withClaim("email", user.getEmail())
                    .withClaim("id", user.getId())
                    .withClaim("role", user.getRole())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 120*1000L))
                    .withJWTId(UUID.randomUUID()
                            .toString())
                    .withNotBefore(new Date(System.currentTimeMillis()))
                    .sign(algorithm);
    return jwtToken;
        }

        public  String getRefreshToken(User user){
            String jwtToken = JWT.create()
                    .withIssuer("Baeldung")
                    .withSubject("Baeldung Details")
                    .withClaim("name", user.getEmail())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000L))
                    .withJWTId(UUID.randomUUID()
                            .toString())
                    .withNotBefore(new Date(System.currentTimeMillis() + 1000L))
                    .sign(algorithm);
            return jwtToken;
        }

    }

