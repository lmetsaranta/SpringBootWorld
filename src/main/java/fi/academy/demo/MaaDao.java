package fi.academy.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaaDao {

    @Autowired
    JdbcTemplate jdbc;

    public List<Maa> haeKaikkiMaat(String hakusana) {
        NamedParameterJdbcTemplate jdbcp = new NamedParameterJdbcTemplate(jdbc);
        Map<String, String> hakusanat = new HashMap<>();
        hakusanat.put("name", hakusana + "%");
        String sql = "SELECT country.code, country.name, country.population, city.name AS paa FROM country JOIN city ON country.capital = city.id WHERE country.name LIKE :name ORDER BY country.population";
        List<Maa> palautettavat = jdbcp.query(sql, hakusanat, (resultSet, i) -> {
            Maa maa = new Maa(
                    resultSet.getString("code"),
                    resultSet.getString("name"),
                    resultSet.getInt("population"),
                    resultSet.getString("paa"));
            return maa;
        });
        return palautettavat;
    }

    public List<Kaupunki> haeKaikkiKaupungit() {
        String sql = "SELECT id, name, country_code, population FROM city ORDER BY population";
        List<Kaupunki> palautettavat = jdbc.query(sql, (resultset, i) -> {
            Kaupunki kaupunki = new Kaupunki(
                    resultset.getInt("id"),
                    resultset.getString("name"),
                    resultset.getString("country_code"),
                    resultset.getInt("population"));
            return kaupunki;
        });
        return palautettavat;
    }

    public boolean poistaMaa(String koodi) {
        String sql = "DELETE FROM country WHERE code=?";
        return jdbc.update(sql, new Object[]{koodi}) == 1;
    }

    public int lisaaMaa(Maa maa) {
        KeyHolder kh = new GeneratedKeyHolder();

        PreparedStatementCreator prc = connection -> {
            PreparedStatement pr = connection
                    .prepareStatement("INSERT INTO country (code, name, population) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pr.setString(1, maa.getMaakoodi());
            pr.setString(2, maa.getNimi());
            pr.setInt(3, maa.getAsukasluku());
            return pr;
        };

        jdbc.update(prc, kh);
        int id = kh.getKey().intValue();
        maa.setId(id);
        return id;
    }

    public int lisaaKaupunki(Kaupunki k) {
        KeyHolder kh = new GeneratedKeyHolder();

        PreparedStatementCreator prc = connection -> {
            PreparedStatement pr = connection
                    .prepareStatement("INSERT INTO city (name, country_code, population) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pr.setString(1, k.getNimi());
            pr.setString(2, k.getMaakoodi());
            pr.setInt(3, k.getAsukasluku());
            return pr;
        };

        jdbc.update(prc, kh);
        int id = (int)kh.getKeys().get("id");
        System.out.println("Uusi id on: " + id);
        k.setId(id);
        return id;
    }
}
