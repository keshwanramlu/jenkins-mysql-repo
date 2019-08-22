package com.example.jenkinsmysqldemo.dao;

import com.example.jenkinsmysqldemo.domain.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GreetingDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GreetingDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public static final String INSERT_GREETING_SQL = "insert into greeting " +
            "(greeting)  values (?)";
    public static final String SELECT_GREETING_BY_ID_SQL = "select * from greeting " +
            "where id = ?";


    @Transactional
    public Greeting createGreeting(Greeting greeting){
        jdbcTemplate.update(INSERT_GREETING_SQL,greeting.getGreeting());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        greeting.setId(id);
        return greeting;

    }

    public Greeting getGreetingById(int id){
        try{
            return jdbcTemplate.queryForObject(SELECT_GREETING_BY_ID_SQL, this::mapRowToGreeting, id);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Greeting> getAll(){
        return jdbcTemplate.query("select * from greeting", this::mapRowToGreeting);
    }

    public void deleteGreetingById(int id){
        jdbcTemplate.update("delete from greeting where id =?",id);
    }

    private Greeting mapRowToGreeting(ResultSet rs, int rowNum) throws SQLException {
        Greeting greeting = new Greeting();
        greeting.setId(rs.getInt(1));
        greeting.setGreeting(rs.getString(2));

        return greeting;
    }
}
