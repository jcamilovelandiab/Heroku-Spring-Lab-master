package edu.eci.config;

import  org.apache.commons.dbcp.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class DataBases {


    public static BasicDataSource basicDataSource = null;

    @Bean
    public static  BasicDataSource dataSource() throws URISyntaxException{
        URI dbUri = new URI("postgres://iiuzkplmxawmnr:8492ca29d3c7af239cc2709b78c608c5f0a8c91ba638cfab1ef7c9a35d047edd@ec2-75-101-131-79.compute-1.amazonaws.com:5432/d9nm6i8i87gti0");
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMinIdle(3);
        return basicDataSource;
    }



}
