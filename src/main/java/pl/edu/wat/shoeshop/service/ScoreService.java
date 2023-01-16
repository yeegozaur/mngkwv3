package pl.edu.wat.shoeshop.service;

import org.springframework.stereotype.Service;
import pl.edu.wat.shoeshop.entity.Shoe;

import java.util.Random;

@Service
public class ScoreService {
    public Integer getScore(Shoe shoe) {
        return new Random().nextInt(10); //TODO
    }
}